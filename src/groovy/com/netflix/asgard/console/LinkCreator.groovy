package com.netflix.asgard.console

@Category(Asgard)
class LinkCreator {

    def action(String name, String id) {
        "<a href=\"${queryUrl(name, id)}\">${id}</a>"
    }
}
