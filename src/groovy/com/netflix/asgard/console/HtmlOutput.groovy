package com.netflix.asgard.console

import groovy.json.JsonOutput
import name.fraser.neil.plaintext.diff_match_patch

class HtmlOutput {

    final stringBuilder = new StringBuilder()
    def output = this

    def diff(a, b) {
        def differ = new diff_match_patch()
        def diffs = differ.diff_main(prettyPrint(a), prettyPrint(b))
        differ.diff_cleanupSemantic(diffs)
        "<pre>${differ.diff_prettyHtml(diffs)}</pre>"
    }

    def link = { linkContent ->
        use(LinkCreator) {
            return linkContent().toString()
        }
    }

    def leftShift(content) {
        if (content instanceof GString || content instanceof String) {
          // removes quotes in html output formatting
          stringBuilder << content.toString()
        } else {
          stringBuilder << prettyPrint(content)
        }
    }

    String toString() {
        String text = stringBuilder.toString()
        if (text) {
            "<pre>${stringBuilder.toString()}</pre>"
        }
        text
    }

    private String prettyPrint(content) {
        JsonOutput.prettyPrint(JsonOutput.toJson(content))
    }

    String sparkline(List<Integer> values, Map<String, String> options = [:]) {
        String optionsString = options.collect { String name, String value ->
            "spark${name}=\"${value}\""
        }.join(' ')
        "<span class=\"sparkline\" values=\"${values.join(',')}\" ${optionsString}></span>"
    }
}
