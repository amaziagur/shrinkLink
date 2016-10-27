package test.com.crazyLabz.shortener.ct.specs

import test.com.crazyLabz.shortener.ct.clients.ShortenerClient
import spock.lang.Shared
import spock.lang.Specification

class UrlShortenerSpec extends Specification {

    public static final String FULL_URL = "http://www.google.com"
    public static final String PREFIX = "http://crazyLabz/"

    @Shared
    ShortenerClient shortenerClient = new ShortenerClient()

    def setupSpec() {
        shortenerClient.start()
    }

    def "should short when given full url"(){

        when:
        def shortUrl = shortenerClient.shortMe(FULL_URL, PREFIX)

        then:
        assert shortUrl.shortUrl != null
        assert new String(shortUrl.shortUrl).contains(PREFIX)
    }

}
