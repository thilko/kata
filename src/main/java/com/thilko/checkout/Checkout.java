package de.iteratec.draeger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Checkout {

    private final Consumer<WalMartProduct> printer;
    private final ArrayList<WalMartProduct> products = new ArrayList<>();

    private DiscountCalculator discountCalculator = new DiscountCalculator();
    private Function<List<WalMartProduct>, Integer> bonusPointManager = (products)-> null;

    public Checkout(Consumer<WalMartProduct> p0) {
        this.printer = p0;
    }

    private BigDecimal sumUp() {
        if(products.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal sum = products.stream().map(WalMartProduct::calculatePrice)
                .collect(Collectors.reducing(BigDecimal::add));

        return sum.subtract(discountCalculator.calculateDiscount(products));
    }

    public BigDecimal scan(WalMartProductType product) {
        WalMartProduct productToAdd = WalMartProduct.fromType(product);
        products.add(productToAdd);
        printer.accept(productToAdd);

        return sumUp();
    }

    public Receipt checkout(Consumer<BigDecimal> printer) {
        BigDecimal sum = sumUp();
        printer.accept(sum);
        Integer points = bonusPointManager.apply(products);

        return Receipt.withSum(sum).withPoints(points);
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

    public void useDiscount(Function<List<WalMartProduct>, BigDecimal> noodles3For2) {
        discountCalculator.addBargain(noodles3For2);
    }

    public void setBonusPointManager(Function<List<WalMartProduct>, Integer> manager) {
        this.bonusPointManager = manager;
    }
}
