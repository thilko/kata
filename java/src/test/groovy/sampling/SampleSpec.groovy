package sampling

import spock.lang.Specification

import java.time.Instant

class SampleSpec extends Specification {

    def measuredAt = Instant.parse('2007-12-03T10:15:30.00Z')

    def 'a sample can be created'() {
        when:
        def sample = new Sample(45.7, measuredAt)

        then:
        sample.value == 45.7
        sample.measuredAt == measuredAt
    }

    def 'the start time for the first sample range can be set'() {
        given:
        def startSampleTime = Instant.parse('2007-12-03T10:15:25.00Z')

        when:
        def sampled = new Sampler(startSampleTime: startSampleTime).sample(new Sample(45.7, measuredAt))

        then:
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:15:40.00Z')
    }

    def 'the latest sample inside a sample range is sampled'() {
        given:
        def startSampleTime = Instant.parse('2007-12-03T10:15:25.00Z')
        def firstSample = new Sample(45.7, Instant.parse('2007-12-03T10:15:26.00Z'))
        def secondSample = new Sample(98.5, Instant.parse('2007-12-03T10:15:27.00Z'))

        when:
        def sampled = new Sampler(startSampleTime: startSampleTime).sample(firstSample, secondSample)

        then:
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:15:40.00Z')
        sampled[0].value == 98.5
    }

    def 'for each sample range one sample is returned'() {
        given:
        def startSampleTime = Instant.parse('2007-12-03T10:15:25.00Z')
        def firstSample = new Sample(45.7, Instant.parse('2007-12-03T10:15:26.00Z'))
        def secondSample = new Sample(98.5, Instant.parse('2007-12-03T10:15:41.00Z'))

        when:
        def sampled = new Sampler(startSampleTime: startSampleTime).sample(firstSample, secondSample)

        then:
        sampled.size() == 2
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:15:40.00Z')
        sampled[0].value == 45.7
        sampled[1].measuredAt == Instant.parse('2007-12-03T10:15:55.00Z')
        sampled[1].value == 98.5
    }

    def 'for each sample range only the latest sample is returned'() {
        given:
        def startSampleTime = Instant.parse('2007-12-03T10:15:25.00Z')
        def firstSample = new Sample(123.5, Instant.parse('2007-12-03T10:15:26.00Z'))
        def secondSample = new Sample(45.7, Instant.parse('2007-12-03T10:15:27.00Z'))
        def thirdSample = new Sample(99.7, Instant.parse('2007-12-03T10:15:41.00Z'))
        def forthSample = new Sample(108.5, Instant.parse('2007-12-03T10:15:42.00Z'))

        when:
        def sampled = new Sampler(startSampleTime: startSampleTime).sample(firstSample,
                secondSample,
                thirdSample, forthSample)

        then:
        sampled.size() == 2
        sampled[0].measuredAt == Instant.parse('2007-12-03T10:15:40.00Z')
        sampled[0].value == 45.7
        sampled[1].measuredAt == Instant.parse('2007-12-03T10:15:55.00Z')
        sampled[1].value == 108.5
    }


}
