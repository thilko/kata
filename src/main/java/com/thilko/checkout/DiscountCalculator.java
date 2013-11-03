package com.thilko.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public class DiscountCalculator {
    private HashSet<Function<List<WalMartProduct>, BigDecimal>> discounts = new HashSet<>();


    public BigDecimal calculateDiscount(ArrayList<WalMartProduct> products) {
        BigDecimal amount = BigDecimal.ZERO;
        for(Function<List<WalMartProduct>, BigDecimal> discount : discounts){
            amount = amount.add(discount.apply(products));
        }

        return amount;
    }

    public void addBargain(Function<List<WalMartProduct>, BigDecimal> noodles3For2) {
        discounts.add(noodles3For2);
    }
}
