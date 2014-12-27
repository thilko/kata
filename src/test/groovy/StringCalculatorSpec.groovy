import spock.lang.Specification

class StringCalculatorSpec extends Specification {

    private static final String SELF_DEFINED_DELIM_REGEXP = /^\/\/(.+)\n/

    def "for an empty string 0 is returned"() {
        when:
        def output = add("")

        then:
        output == 0
    }

    def "for '1' 1 is returned"() {
        when:
        def output = add("1")

        then:
        output == 1
    }

    def "for '2' 2 is returned"() {
        when:
        def output = add("2")

        then:
        output == 2
    }

    def "for '1,2' 3 is returned"() {
        when:
        def output = add("1,2")

        then:
        output == 3
    }

    def "for '1,2,3' 6 is returned"() {
        when:
        def output = add("1,2,3")

        then:
        output == 6
    }

    def "for '1\\n2' 6 is returned"() {
        when:
        def output = add("1\n2")

        then:
        output == 3
    }

    def "for '//;\\n1;2;3;4' 10 is returned"() {
        when:
        def output = add("//;\n1;2;3;4")

        then:
        output == 10
    }

    def "for -1,2,3, will thrown an exception with text 'Negatives not allowed: -1'"() {
        when:
        add("-1,2,3")

        then:
        def e = thrown(IllegalStateException)
        e.message == "Negatives not allowed: -1"
    }

    def "for -1,2,3,-6,9, will thrown an exception with text 'Negatives not allowed: -1'"() {
        when:
        add("-1,2,3,-6,9")

        then:
        def e = thrown(IllegalStateException)
        e.message == "Negatives not allowed: -1 -6"
    }

    def "for 600,333,1002,2,5,6 ignore values over 1000 and return 1012"() {
        when:
        def output = add("600,333,1002,2,5,6")

        then:
        output == 946
    }

    def "for //***\\n600***333***7 returns 940"() {
        when:
        def output = add("//***\n600***333***7")

        then:
        output == 940
    }

    Integer add(String input) {
        if (!input) return 0


        if (input =~ SELF_DEFINED_DELIM_REGEXP) {
            input = input.replace((input =~ SELF_DEFINED_DELIM_REGEXP)[0][1], " ")
            input = input.replaceFirst("//.+\n", "")
        } else {
            input = input.replace(",", " ").replace("\n", " ")
        }

        checkForNegatives(input)

        return input.split(" ")
                .collect { it as Integer }
                .collect { it > 1000 ? 0 : it }
                .inject(0) { sumValue, currValue -> sumValue + currValue }
    }

    private static void checkForNegatives(String input) {
        List<Integer> negatives = input.split(" ").collect { it as Integer }.findAll { it < 0 }
        if (!negatives.isEmpty()) {
            throw new IllegalStateException("Negatives not allowed: " + negatives.join(" "))
        }
    }
}
