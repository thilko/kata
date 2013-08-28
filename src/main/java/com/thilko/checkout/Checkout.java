package com.thilko.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Checkout {

    private final Consumer<WalMartProduct> printer;
    private final ArrayList<WalMartProduct> products = new ArrayList<>();

    public Checkout(Consumer<WalMartProduct> p0) {
        this.printer = p0;
    }

    public BigDecimal scan(WalMartProductType product) {
        WalMartProduct productToAdd = WalMartProduct.fromType(product);
        products.add(productToAdd);
        printer.accept(productToAdd);

        return sumUp();
    }

    private BigDecimal sumUp() {
        if(products.isEmpty()){
            return BigDecimal.ZERO;
        }

        return products.parallelStream().map(WalMartProduct::calculatePrice)
                .collect(Collectors.reducing(BigDecimal::add));
    }

    public BigDecimal checkout(Consumer<BigDecimal> printer) {
        BigDecimal sum = sumUp();
        printer.accept(sum);

        return sum;
    }

    public void scan(WalMartProductType product, Integer count) {
        for (int i = 0; i < count; i++) {
            scan(product);
        }
    }

    public BigDecimal scan(WalMartProductType product, Weight weight) {
        WalMartProduct productToAdd = WalMartProduct.withWeight(product, weight);
        products.add(productToAdd);
        printer.accept(productToAdd);

        return sumUp();
    }
}
