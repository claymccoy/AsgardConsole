package com.netflix.asgard.console

import groovyx.net.http.HTTPBuilder
import groovy.json.JsonSlurper

class AsgardRemoteCaller {

    def getJson(String url) {
        def http = new HTTPBuilder("${url}.json")
        http.parser.'application/json' = { resp ->
            def slurper = new JsonSlurper()
            slurper.parse(new InputStreamReader(resp.entity.content))
        }
        http.get([:])
    }
}
