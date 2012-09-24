package com.netflix.asgard.console

import grails.converters.JSON

class ConsoleController {

    def index = { [jsController: 'ConsoleCtrl'] }

    def execute() {
        String code = request.JSON.code
        def result
        def error
        def asgard = 'Asgard'
        try {
            def binding = new Binding([ asgard: asgard ])
            result = new GroovyShell(binding).evaluate(code)
        } catch(Exception e) {
            error = e
        }

        render([
                resultEval: result,
                error: error
        ] as JSON)
    }

}
