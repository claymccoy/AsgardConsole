package com.netflix.asgard.console

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.tools.shell.util.NoExitSecurityManager

class ConsoleController {

    def gistService

    def index = {
        String code = ''
        if (params.gistId && params.gistFile) {
            code = gistService.getRawCode(params.gistId, params.gistFile)
        }
        [jsController: 'ConsoleCtrl', code: code]
    }

    def execute() {
        Map consoleParams = [:]
        def consoleParamTypes = request.JSON.consoleParamTypes
        if (consoleParamTypes != JSONObject.NULL) {
          consoleParamTypes.each {
            consoleParams[it.name] = it.defaultValue
          }
        }

        request.JSON.consoleParams.each { k, v ->
            if (v) {
                consoleParams[k] = v
            }
        }

        String code = """
          def html = html
          def param = configProcessor.param
          configProcessor.with {
            ${request.JSON.code}
          }
        """
        def result
        def error
        def configProcessor = new ConsoleExecuter(consoleParams)
        def asgardLocator = new AsgardLocator(new AsgardRemoteCaller())
        def html = new HtmlOutput()
        try {
            def binding = new Binding([asgardLocator: asgardLocator, html: html, configProcessor: configProcessor])
            result = new GroovyShell(binding).evaluate(code)
        } catch(Exception e) {
            e.printStackTrace()
            error = e
        }

        render([
                resultEval: result?.toString(),
                html: html.toString(),
                error: error?.getMessage()
        ] as JSON)
    }

    def configure() {
        String code = """
          configProcessor.with {
            ${request.JSON.code}
          }
          configProcessor.getConfig()
        """
        def consoleParams
        def error
        def configProcessor = new ConsoleConfigurer()
        System.setSecurityManager(new NoExitSecurityManager());
        ConsoleConfig config
        try {
            def binding = new Binding([configProcessor: configProcessor])
            config = new GroovyShell(binding).evaluate(code)
            config.parameters.each {
                it.value = request.JSON.consoleParams[it.name]
            }
        } catch(Exception e) {
            e.printStackTrace()
            error = e
        }
        render([
                error: error?.getMessage(),
                title: config?.title,
                consoleParams: config?.parameters,
        ] as JSON)
    }

}
