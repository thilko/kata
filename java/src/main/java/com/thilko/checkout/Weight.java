package com.thilko.checkout;

import java.math.BigDecimal;

public class Weight {
    private final String weight;

    private Weight(String weight) {
        this.weight = weight;
    }

    public static Weight asKg(String weight) {
        return new Weight(weight);
    }

    public BigDecimal asBigDecimal(){
        return new BigDecimal(weight).setScale(2);
    }

    public static Weight withoutWeight() {
        return new Weight("1");
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weight='" + weight + '\'' +
                '}';
    }
}
