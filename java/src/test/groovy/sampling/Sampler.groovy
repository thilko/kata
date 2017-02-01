package sampling

import java.time.Duration
import java.time.Instant

class Sampler {
    Instant start

    List<Measurement> sample(Measurement... measurement) {
        measurement.sort { lhs, rhs -> (lhs.measuredAt <=> rhs.measuredAt) }.groupBy {
            def sampleTime = start
            while (it.measuredAt > sampleTime) {
                sampleTime = sampleTime.plus(Duration.ofMinutes(5))
            }
            sampleTime
        }.collect {
            it.value.last().measuredAt = it.key
            it.value.last()
        }
    }
}
