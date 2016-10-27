package test.com.crazyLabz.shortener.ct.clients

import test.com.crazyLabz.shortener.ct.requests.ShortenRequest
import test.com.crazyLabz.shortener.ct.responses.ShortenResponse
import groovyx.net.http.RESTClient
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import com.crazyLabz.shortener.Application

class ShortenerClient {

    static def CONTEXT_PATH = "shortener"
    static def SHORT = "$CONTEXT_PATH/v1/short"

    RESTClient client
    ConfigurableApplicationContext service

    def start(String... args) {
        service = SpringApplication.run(Application.class, args)
        client = new RESTClient("http://localhost:8080/")
    }

    def String getServiceProperty(String propName) {
        return service.environment.getProperty(propName)
    }

    def stop() {
        service.close()
    }

    def shortMe(String fullUrl, String prefix) {
        def res = client.post([
                path: SHORT,
                contentType: "application/json",
                body: new ShortenRequest(url: fullUrl, prefix: prefix)
        ])

        assert res.status == 200
        return res.responseData as ShortenResponse
    }
}
