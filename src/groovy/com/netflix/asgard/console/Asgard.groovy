package com.netflix.asgard.console

class Asgard {

    final String baseUrl
    final String inRegion
    final AsgardRemoteCaller asgardRemoteCaller

    protected Asgard(String baseUrl, String region, AsgardRemoteCaller asgardRemoteCaller) {
        this.baseUrl = baseUrl
        this.inRegion = region
        this.asgardRemoteCaller = asgardRemoteCaller
    }

    def methodMissing(String name, args) {
        String id = args ? args[0] : null
        action(name, id)
    }

    def propertyMissing(String name) { methodMissing(name, null) }

    def propertyMissing(String name, value) { methodMissing(name, [value]) }

    def action(String name, String id) {
        query(queryUrl(name, id))
    }

    def query(String url) {
        asgardRemoteCaller.getJson(url)
    }

    String queryUrl(String name, String id) {
        String region = inRegion ?: ''
        String object = id ? "show/${id}" : 'list'
        "http://${baseUrl}${region ? "/${region}" : ''}/${name}/${object}"
    }

    Map<Asgard, Object> acrossRegions(List<String> regions = this.region.code, Closure doIt) {
        Map<Asgard, Object> resultByRegion = [:]
        regions.each {
            def result = inRegion(it, doIt)
            if (result) {
                resultByRegion[inRegion(it)] = result
            }
        }
        resultByRegion
    }

    Object inRegion(String region, Closure doIt) {
        inRegion(region).with doIt
    }

    Asgard inRegion(String region) {
        new Asgard(baseUrl, region, asgardRemoteCaller)
    }

    String toString() {
        "${baseUrl}/${inRegion}"
    }

}
