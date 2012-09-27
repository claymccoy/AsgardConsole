package com.netflix.asgard.console

class AsgardLocator {

    final AsgardRemoteCaller asgardRemoteCaller

    AsgardLocator(AsgardRemoteCaller asgardRemoteCaller) {
        this.asgardRemoteCaller = asgardRemoteCaller
    }

    Asgard getInstance(String baseUrl, String region = null) {
        new Asgard(baseUrl, region, asgardRemoteCaller)
    }
}
