package sampling

import spock.lang.Specification

import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalUnit

class SampleSpec extends Specification {

    def 'a measurement can be created'() {
        given:
        def measuredAt = Instant.parse('2007-12-03T10:15:30.00Z')
        def value = 45.3d

        when:
        def measurement = new Measurement(measuredAt: measuredAt, value: value)

        then:
        measurement.measuredAt == measuredAt
        measurement.value == value
    }

    def 'a single measurement is sampled to the end of sampling range'() {
        given:
        def sampler = new Sampler(start: Instant.parse('2007-12-03T10:15:30.00Z'))

        def measuredAt = Instant.parse('2007-12-03T10:15:34.00Z')
        def measurement = new Measurement(measuredAt: measuredAt, value: 45.3)

        when:
        def sampled = sampler.sample(measurement)

        then:
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:20:30.00Z')
        sampled[0].value == 45.3d
    }

    def 'the last of two measurement is sampled to the end of sampling range'() {
        given:
        def sampler = new Sampler(start: Instant.parse('2007-12-03T10:15:30.00Z'))

        def measuredAt1 = Instant.parse('2007-12-03T10:15:34.00Z')
        def measurement1 = new Measurement(measuredAt: measuredAt1, value: 21.5)

        def measuredAt2 = Instant.parse('2007-12-03T10:15:35.00Z')
        def measurement2 = new Measurement(measuredAt: measuredAt2, value: 99.7)

        when:
        def sampled = sampler.sample(measurement1, measurement2)

        then:
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:20:30.00Z')
        sampled[0].value == 99.7d
    }

    def 'the order of the given measurements does not matter'() {
        given:
        def sampler = new Sampler(start: Instant.parse('2007-12-03T10:15:30.00Z'))

        def measuredAt1 = Instant.parse('2007-12-03T10:15:34.00Z')
        def measurement1 = new Measurement(measuredAt: measuredAt1, value: 21.5)

        def measuredAt2 = Instant.parse('2007-12-03T10:15:35.00Z')
        def measurement2 = new Measurement(measuredAt: measuredAt2, value: 99.7)

        when:
        def sampled = sampler.sample(measurement2, measurement1)

        then:
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:20:30.00Z')
        sampled[0].value == 99.7d
    }

    def 'two measurements in different ranges can be sampled'() {
        given:
        def sampler = new Sampler(start: Instant.parse('2007-12-03T10:15:30.00Z'))

        def measuredAt1 = Instant.parse('2007-12-03T10:15:34.00Z')
        def measurement1 = new Measurement(measuredAt: measuredAt1, value: 21.5)

        def measuredAt2 = Instant.parse('2007-12-03T10:20:36.00Z')
        def measurement2 = new Measurement(measuredAt: measuredAt2, value: 99.7)

        when:
        def sampled = sampler.sample(measurement1, measurement2)

        then:
        sampled.size() == 2
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:20:30.00Z')
        sampled[0].value == 21.5d
        sampled[1].measuredAt == Instant.parse('2007-12-03T10:25:30.00Z')
        sampled[1].value == 99.7d
    }

    def 'the order in the second range does not matter'() {
        given:
        def sampler = new Sampler(start: Instant.parse('2007-12-03T10:15:30.00Z'))

        def measuredAt1 = Instant.parse('2007-12-03T10:15:34.00Z')
        def measurement1 = new Measurement(measuredAt: measuredAt1, value: 21.5)

        def measuredAt2 = Instant.parse('2007-12-03T10:20:36.00Z')
        def measurement2 = new Measurement(measuredAt: measuredAt2, value: 99.7)

        def measuredAt3 = Instant.parse('2007-12-03T10:20:35.00Z')
        def measurement3 = new Measurement(measuredAt: measuredAt3, value: 102.7)

        when:
        def sampled = sampler.sample(measurement1, measurement2, measurement3)

        then:
        sampled.size() == 2
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:20:30.00Z')
        sampled[0].value == 21.5d
        sampled[1].measuredAt == Instant.parse('2007-12-03T10:25:30.00Z')
        sampled[1].value == 99.7d
    }

    def 'bla'(){
        expect:
        LocalDate.of(2000, 1, 30).plusMonths(2).minusMonths(2) == LocalDate.of(2000, 2, 28)
    }

}
