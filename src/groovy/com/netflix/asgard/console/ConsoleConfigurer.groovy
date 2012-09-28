package com.netflix.asgard.console

class ConsoleConfigurer {

    def config(Closure configuration, Closure work) {
        configuration()
    }

    def params(Closure paramDefinitions) {
        new ConfigureConsoleParams().with paramDefinitions
    }


    def methodMissing(String name, args) {
        return this
    }

    def propertyMissing(String name) {
        return this
    }

}
