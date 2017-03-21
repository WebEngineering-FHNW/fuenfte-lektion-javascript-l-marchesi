package mvc

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import org.openqa.selenium.Keys

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class JSTempConverterSpec extends GebSpec {

    void "From celsius to fahrenheit with JavaScript"() {
        when:
            browser.driver.javascriptEnabled = true
            go '/static/Temperatures.html'
        then:
        	title == "Temperature Converter with JavaScript"

        when: "set celsius without clicking"
            $("form").celsius = "42"
            $("form").fahrenheit().click()

        then: "the other field is updated immediately"
            $("form").fahrenheit  == "107.60000000000001" // should be 107.6 ... according to Google
    }

    // TODO: make a new test method for fahrenheit to celsius conversion
    void "From fahrenheit to celsius with JavaScript"() {
        when:
        browser.driver.javascriptEnabled = true
        go '/static/Temperatures.html'
        then:
        title == "Temperature Converter with JavaScript"

        when: "set fahrenheit without clicking"
        $("form").fahrenheit = "32"
        $("form").celsius().click()

        then: "the other field is updated immediately"
        $("form").celsius  == "0"
    }

}
