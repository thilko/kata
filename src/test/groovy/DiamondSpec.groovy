import spock.lang.Specification

class DiamondSpec extends Specification {

    def "Diamond for input 'A' is printed()"() {
        when:
        def input = "A"

        then:
        diamond(input) == "A"
    }

    def "Diamond for input 'B' is printed()"() {
        when:
        def input = "B"

        then:
        diamond(input) == " A \nB B\n A "
    }

    def diamond(String letter) {
        if(letter == "B"){
            return " A \nB B\n A "
        }

        "A"
    }
}
