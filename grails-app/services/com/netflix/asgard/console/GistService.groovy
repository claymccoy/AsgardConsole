package com.netflix.asgard.console

import groovyx.net.http.HTTPBuilder
import groovy.json.JsonSlurper

class GistService {

    def http = new HTTPBuilder('https://api.github.com/gists/').with {
        it.parser.'application/json' = { resp ->
            def slurper = new JsonSlurper()
            slurper.parse(new InputStreamReader(resp.entity.content))
        }
        it
    }

    String getRawCode(String gistId, String fileName) {
        def gist = http.get([path: gistId])
        gist.files[fileName].content
    }
}
