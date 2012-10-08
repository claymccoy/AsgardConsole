package com.netflix.asgard.console

import groovy.transform.Canonical

@Canonical
class ConsoleConfig {
    final String title
    final List<ConsoleParameter> parameters
}
