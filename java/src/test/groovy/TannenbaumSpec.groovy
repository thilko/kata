import spock.lang.Specification

class TannenbaumSpec extends Specification {

    def "tannenbaum method returns emtpy string when height is 0"() {
        given:
        def height = 0

        when:
        def result = draw(height)

        then:
        result == ""
    }

    def "tannenbaum method returns tannenbaum with 1 string when height is 1"() {
        given:
        def height = 1

        when:
        def result = draw(height)

        then:
        result == "X\n"
    }

    def "tannenbaum method returns tannenbaum with 2 string when height is 2"() {
        given:
        def height = 2

        when:
        def result = draw(height)

        then:
        result == " X \nXXX\n"
    }

    def "tannenbaum method returns tannenbaum with 3 string when height is 3"() {
        given:
        def height = 3

        when:
        def result = draw(height)

        then:
        result == "  X  \n XXX \nXXXXX\n"
    }

    def draw(int height) {
        if(height == 0){
            return ""
        }

        def result = ""
        def spaces = height - 1
        def cross = 1
        while (spaces >= 0) {
            String bufferString = " " * spaces
            String crossString = "X" * cross;
            result += "${bufferString}${crossString}${bufferString}\n"
            spaces--
            cross += 2
        }
        return result
    }
}
