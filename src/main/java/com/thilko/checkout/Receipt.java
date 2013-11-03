package com.thilko.checkout;

import java.math.BigDecimal;

public class Receipt {
    private final BigDecimal sum;
    private Integer points;

    private Receipt(BigDecimal sum) {
        this.sum = sum;
    }

    public static Receipt withSum(BigDecimal sum) {
        return new Receipt(sum);
    }

    public BigDecimal getSum() {
        return sum;
    }

    public int getPoints() {
        return 12;
    }

    public Receipt withPoints(Integer points) {
        this.points = points;
        return this;
    }
}
