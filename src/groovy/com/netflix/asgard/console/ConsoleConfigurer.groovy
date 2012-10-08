package com.netflix.asgard.console

class ConsoleConfigurer {

    private final configureConsoleParams = new ConfigureConsoleParams()
    private String title

    def config(Closure configuration, Closure work) {
        configuration()
    }

    def params(Closure paramDefinitions) {
        configureConsoleParams.with paramDefinitions
    }

    def title(String title) {
        this.title = title
    }

    def getConfig() {
        new ConsoleConfig(title, configureConsoleParams.parameters)
    }

    def methodMissing(String name, args) {
        return this
    }

    def propertyMissing(String name) {
        return this
    }

}
