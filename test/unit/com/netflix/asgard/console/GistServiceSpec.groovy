package com.netflix.asgard.console

import spock.lang.Specification

class GistServiceSpec extends Specification {

    final gistService = new GistService()

    def 'should get raw code'() {
        expect:
        gistService.getRawCode('3803279', 'BasicHtml.groovy') == """// Display String with HTML and Groovy
html.with{
  output << \"Test: <br /> \${2+8}\"
}
"""
    }
}
