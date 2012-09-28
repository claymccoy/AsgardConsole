package com.netflix.asgard.console

class ConsoleParameter {

    final String name
    final String description
    final String defaultValue
    String value

    ConsoleParameter(String name, String description, String defaultValue) {
        this.name = name
        this.description = description
        this.defaultValue = defaultValue
    }
}
