package sampling

import java.time.Instant


class Sample {
    BigDecimal value
    Instant measuredAt

    def Sample(BigDecimal value, Instant measuredAt) {
        this.measuredAt = measuredAt
        this.value = value
    }
}