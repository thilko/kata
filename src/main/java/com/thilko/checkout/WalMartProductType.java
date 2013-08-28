package com.thilko.checkout;

import java.math.BigDecimal;

public enum WalMartProductType {
    POTATOES("3.50", false),
    NOODLES("2.00", false),
    BEER("0.60", false),
    MILK("1.00", false),
    WASHING_POWDER("5.00", false),
    CLEANING_AGENT("3.00", false),
    APPLES("3.50", true),
    TOMATOES("2.70", true),
    MELON("1.00", true);

    private final BigDecimal price;
    private final boolean withWeight;

    WalMartProductType(String price, boolean withWeight) {
        this.price = new BigDecimal(price).setScale(2);
        this.withWeight = withWeight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean hasWeight() {
        return withWeight;
    }
}
