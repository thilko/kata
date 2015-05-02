import spock.lang.Specification

class RomanNumeralsSpec extends Specification {

    def "For 0 '' is returned"() {
        given:
        def latin = 0

        when:
        def roman = toRoman(latin)

        then:
        roman == ""
    }

    def "For 1 'I' is returned"() {
        given:
        def latin = 1

        when:
        def roman = toRoman(latin)

        then:
        roman == "I"
    }

    def "For 2 'II' is returned"() {
        given:
        def latin = 2

        when:
        def roman = toRoman(latin)

        then:
        roman == "II"
    }

    def "For 4 'IV' is returned"() {
        given:
        def latin = 4

        when:
        def roman = toRoman(latin)

        then:
        roman == "IV"
    }

    def "For 5 'V' is returned"() {
        given:
        def latin = 5

        when:
        def roman = toRoman(latin)

        then:
        roman == "V"
    }

    def "For 6 'VI' is returned"() {
        given:
        def latin = 6

        when:
        def roman = toRoman(latin)

        then:
        roman == "VI"
    }

    def "For 7 'VII' is returned"() {
        given:
        def latin = 7

        when:
        def roman = toRoman(latin)

        then:
        roman == "VII"
    }

    def "For 8 'VIII' is returned"() {
        given:
        def latin = 8

        when:
        def roman = toRoman(latin)

        then:
        roman == "VIII"
    }

    def "For 9 'IX' is returned"() {
        given:
        def latin = 9

        when:
        def roman = toRoman(latin)

        then:
        roman == "IX"
    }

    def "For 10 'X' is returned"() {
        given:
        def latin = 10

        when:
        def roman = toRoman(latin)

        then:
        roman == "X"
    }

    def "For 11 'XI' is returned"() {
        given:
        def latin = 11

        when:
        def roman = toRoman(latin)

        then:
        roman == "XI"
    }

    def "For 12 'XI' is returned"() {
        given:
        def latin = 11

        when:
        def roman = toRoman(latin)

        then:
        roman == "XI"
    }

    def "For 14 'XIV' is returned"() {
        given:
        def latin = 14

        when:
        def roman = toRoman(latin)

        then:
        roman == "XIV"
    }

    def "For 15 'XV' is returned"() {
        given:
        def latin = 15

        when:
        def roman = toRoman(latin)

        then:
        roman == "XV"
    }

    def "For 16 'XVI' is returned"() {
        given:
        def latin = 16

        when:
        def roman = toRoman(latin)

        then:
        roman == "XVI"
    }

    def "For 19 'XIX' is returned"() {
        given:
        def latin = 19

        when:
        def roman = toRoman(latin)

        then:
        roman == "XIX"
    }

    def numbers = [10: "X", 9: "IX", 5: "V", 4: "IV"]

    def toRoman(int latinNumber) {
        if (latinNumber == 0) {
            return ""
        }

        String result = ""
        def rest = latinNumber
        numbers.each { numericValue, romanNumber ->
            if (numericValue <= rest) {
                result += romanNumber
                rest -= numericValue
            }
        }

        return result + ("I" * rest)
    }
}
