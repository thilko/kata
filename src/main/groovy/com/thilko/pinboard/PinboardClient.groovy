package com.thilko.pinboard

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpURLClient

class PinboardClient {

    public static void main(String[] args) {
        def http = new HTTPBuilder('https://api.pinboard.in')


        StringReader response = http.get(path: "/v1/posts/all", query: [format: "json", auth_token: "thilko:425E83AFCED58E5593B1"])
        def jsonResponse = new JsonSlurper().parse(response)
        println jsonResponse.collect {
            println "check ${it.href}"

            def bla = new HTTPBuilder(it.href)
            bla.ignoreSSLIssues()
            bla.setHeaders("User-Agent": 'Mozilla/5.0')
            bla.get(path: "")
        }.removeAll {it.statusCode == 200}
    }
}
