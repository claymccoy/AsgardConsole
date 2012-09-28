package com.netflix.asgard.console

class ConfigureConsoleParams {

    List<ConsoleParameter> parameters = []

    def methodMissing(String name, args) {
        String description = args?.size() > 0 ? args[0] : ''
        String defaultValue = args?.size() > 1 ? args[1] : null
        parameters << new ConsoleParameter(name, description, defaultValue)
        parameters
    }
}
