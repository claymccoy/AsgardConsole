package com.netflix.asgard.console

class ConsoleExecuter {

    final Map param

    ConsoleExecuter(Map consoleParams) {
        param = consoleParams
    }

    def config(Closure configuration, Closure work) {
        configuration()
        work()
    }

    def params(Closure paramDefinitions) {
        new ConfigureConsoleParams().with paramDefinitions
    }

}
