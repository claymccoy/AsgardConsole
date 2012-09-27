package com.netflix.asgard.console

class Asgard {

    final String baseUrl
    final String regionFilter
    final AsgardRemoteCaller asgardRemoteCaller

    Asgard regionFilter(String region) {
        new Asgard(baseUrl, region, asgardRemoteCaller)
    }

    protected Asgard(String baseUrl, String region, AsgardRemoteCaller asgardRemoteCaller) {
        this.baseUrl = baseUrl
        this.regionFilter = region
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
        String region = regionFilter ? "${regionFilter}" : ''
        String object = id ? "show/${id}" : 'list'
        "http://${baseUrl}${region ? "/${region}" : ''}/${name}/${object}"
    }

}
