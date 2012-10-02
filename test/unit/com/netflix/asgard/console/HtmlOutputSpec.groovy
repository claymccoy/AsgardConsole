package com.netflix.asgard.console

import spock.lang.Specification

class HtmlOutputSpec extends Specification {

    final htmlOutput = new HtmlOutput()

    def 'should construct sparkline html'() {
        expect:
        htmlOutput.sparkline([1,2,3,4,5,4,3,2,1]) ==
                """<span class="sparkline" values="1,2,3,4,5,4,3,2,1" ></span>"""
    }

    def 'should construct sparkline html with options'() {
        expect:
        htmlOutput.sparkline([1,2,3], [Type: 'bar', BarColor: 'red']) ==
                """<span class="sparkline" values="1,2,3" sparkType="bar" sparkBarColor="red"></span>"""
    }

}
