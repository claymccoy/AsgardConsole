package com.netflix.asgard.console

import grails.converters.JSON

class ConsoleController {

    def index = { [jsController: 'ConsoleCtrl'] }

    def execute() {
        String code = """
          def html = html
          ${request.JSON.code}
        """
        def result
        def error
        def asgardLocator = new AsgardLocator(new AsgardRemoteCaller())
        def html = new HtmlOutput()
        try {
            def binding = new Binding([asgardLocator: asgardLocator, html: html])
            result = new GroovyShell(binding).evaluate(code)
        } catch(Exception e) {
            error = e
        }

        render([
                resultEval: result?.toString(),
                html: html.toString(),
                error: error?.getMessage()
        ] as JSON)
    }

}
