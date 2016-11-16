package sampling

import java.time.Instant


class Sampler {

    Instant startSampleTime

    List<Sample> sample(Sample... sample) {
        List<Sample> sortedSamples = sample.sort { o1, o2 -> o1.measuredAt.compareTo(o2.measuredAt) }

        sortedSamples.groupBy {}

        List<Sample> firstSampleRange = []
        def currentRangeEnd = startSampleTime
        while (!sortedSamples.isEmpty()) {
            currentRangeEnd = currentRangeEnd.plusSeconds(15)
            List<Sample> currentRangeSamples = []
            while (!sortedSamples.isEmpty() && sortedSamples.first().measuredAt.isBefore(currentRangeEnd)) {
                currentRangeSamples << sortedSamples.head()
                sortedSamples = sortedSamples.drop(1)
            }
            firstSampleRange << new Sample(currentRangeSamples.last().value, currentRangeEnd)
        }

        return firstSampleRange
    }
}
