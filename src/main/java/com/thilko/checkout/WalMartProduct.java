package com.thilko.checkout;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WalMartProduct {
    private final WalMartProductType type;
    private Weight weight = Weight.withoutWeight();

    private WalMartProduct(WalMartProductType type) {
        this.type = type;
    }

    public WalMartProduct(WalMartProductType type, Weight weight) {
        this.type = type;
        this.weight = weight;
    }

    public static WalMartProduct fromType(WalMartProductType product) {
        return new WalMartProduct(product);
    }

    public static WalMartProduct withWeight(WalMartProductType type, Weight weight) {
        if(!type.hasWeight()){
            throw new IllegalStateException(format("You can´t scan %s with weight!", type));
        }

        return new WalMartProduct(type, weight);
    }

    public BigDecimal calculatePrice() {
        return type.getPrice().multiply(weight.asBigDecimal()).setScale(2, RoundingMode.HALF_UP);
    }

    public WalMartProductType getType() {
        return type;
    }

    @Override
    public String toString() {
        String firstPart = format("%s",type.name());
        if(type.hasWeight()){
            return format("%s with weight %s", firstPart, weight.asBigDecimal());
        }
        return firstPart;
    }

    public boolean isMilk(){
        return WalMartProductType.MILK.equals(type);
    }

}
