package com.netflix.asgard.console

import spock.lang.Specification

class AsgardSpec extends Specification {

    final asgardRemoteCaller = Mock(AsgardRemoteCaller)
    final asgard = new Asgard('asgardstaging', 'us-south-1', asgardRemoteCaller)

    def 'should create url for objects'() {
        when: asgard.autoScaling
        then: 1 * asgardRemoteCaller.getJson('http://asgardstaging/us-south-1/autoScaling/list')
        0 * _
    }

    def 'should create url for specific object by id'() {
        when: asgard.autoScaling 'hello123'
        then: 1 * asgardRemoteCaller.getJson('http://asgardstaging/us-south-1/autoScaling/show/hello123')
        0 * _
    }

    def 'should create url for specific object by id as an int'() {
        when: asgard.autoScaling 123
        then: 1 * asgardRemoteCaller.getJson('http://asgardstaging/us-south-1/autoScaling/show/123')
        0 * _
    }

    def 'should create url for specific object by id with parens'() {
        when: asgard.autoScaling(456)
        then: 1 * asgardRemoteCaller.getJson('http://asgardstaging/us-south-1/autoScaling/show/456')
        0 * _
    }

    def 'should create links only'() {
        when:
        def actualUrl = null
        use(LinkCreator) {
            actualUrl = asgard.autoScaling(456)
        }

        then:
            actualUrl == '<a href="http://asgardstaging/us-south-1/autoScaling/show/456">456</a>'
            0 * _
    }

}
