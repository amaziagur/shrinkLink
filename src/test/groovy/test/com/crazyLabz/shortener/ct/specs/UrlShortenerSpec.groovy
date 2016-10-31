package test.com.crazyLabz.shortener.ct.specs

import org.junit.Ignore
import test.com.crazyLabz.shortener.ct.clients.ShortenerClient
import spock.lang.Shared
import spock.lang.Specification

class UrlShortenerSpec extends Specification {

    public static final String FULL_URL = "http://www.google.com"
    public static final String PREFIX = "http://localhost:8080/shortener/"

    @Shared
    ShortenerClient shortenerClient = new ShortenerClient()

    def setupSpec() {
        shortenerClient.start()
    }

    def "should short when given full url"(){

        when:
        def response = shortenerClient.shortMe(FULL_URL, PREFIX)

        then:
        assert response.shortUrl != null
        assert new String(response.shortUrl).contains(PREFIX)
    }

    def "should redirect shortUrl to orig"(){

        when:
        def response = shortenerClient.shortMe(FULL_URL, PREFIX)

        then:
        def redirectedPage = shortenerClient.redirect(response.shortUrl)

        assert redirectedPage.contains("Google")
    }

    def "number of hits should show in stats"(){
        when:
        def response = shortenerClient.shortMe(FULL_URL, PREFIX)

        and:
        shortenerClient.redirect(response.shortUrl)

        then:
        assert shortenerClient.urlStats(response.shortUrl).asset.hits == 1
    }

    def "number of hits for revisit should show in stats"(){
        when:
        def response = shortenerClient.shortMe(FULL_URL, PREFIX)

        and:
        shortenerClient.redirect(response.shortUrl)

        then:
        shortenerClient.redirect(response.shortUrl)
        assert shortenerClient.urlStats(response.shortUrl).asset.hits == 2
    }

}
