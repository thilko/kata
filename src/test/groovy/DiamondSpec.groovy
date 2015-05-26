import spock.lang.Specification

class DiamondSpec extends Specification {

    def "Diamond for input 'A' is printed()"() {
        when:
        def input = "A"

        then:
        diamond(input) == "A"
    }

    def diamond(String letter) {
        "A"
    }
}
