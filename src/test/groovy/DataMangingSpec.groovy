import spock.lang.Specification

class DataMangingSpec extends Specification {

    def "content of an existing file could be read"() {
        when:
        def file = Day.readFile()

        then:
        !file.isEmpty()
    }

    def "the day number can be extracted"() {
        when:
        def day = firstDay()

        then:
        day.number() == 1
    }

    def "the maximum temperature can be extracted"() {
        when:
        def day = firstDay()

        then:
        day.maximumTemperature() == 88
    }


    def "the minimum temperature can be extracted"() {
        when:
        def day = firstDay()

        then:
        day.minimumTemperature() == 59
    }

    def "the temperatur spread can be calculated"() {
        when:
        def day = firstDay()
        def tempSpread = day.tempSpread()

        then:
        tempSpread == 29
    }

    def "calculate the smallest temperature spread"() {
        when:
        List<Day> days = Day.readFile()
//        def spread = days[(2..-1)].min { it.minimumTemperature() }

        then:
        bla()

    }

    def bla() {
    }

    private Day firstDay() {
        Day.readFile()[2]
    }
}
