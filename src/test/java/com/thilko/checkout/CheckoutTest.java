package com.thilko.checkout;

import static com.thilko.checkout.WalMartProductType.*;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CheckoutTest {

    private Checkout checkout;

    @Before
    public void setUp() throws Exception {
        checkout = new Checkout((p)-> System.out.println(format("You added a new Product: %s", p)));
    }

    @Test
    public void addProduct_noProducts_0euro() throws Exception {
        assertThat(checkout.checkout(System.out::println), is(BigDecimal.ZERO));
    }

    @Test
    public void addProduct_3euro50cent_subtotalIs3Euro50cent() throws Exception {
        BigDecimal subtotal = checkout.scan(POTATOES);

        assertThat(subtotal, is(new BigDecimal("3.50")));
    }

    @Test
    public void addProduct_twoProducts_priceIsAddedCorrectly() throws Exception {
        checkout.scan(POTATOES);
        BigDecimal subtotal = checkout.scan(NOODLES);

        assertThat(subtotal, is(new BigDecimal("5.50")));
    }

    @Test
    public void addProduct_threeProducts_priceIsAddedCorrectly() throws Exception {
        checkout.scan(POTATOES);
        checkout.scan(NOODLES);
        BigDecimal subtotal = checkout.scan(BEER);

        assertThat(subtotal, is(new BigDecimal("6.10")));
    }

    @Test
    public void checkout_withProducts_totalSumIsPrintedAndReturned() throws Exception {
        checkout.scan(POTATOES);
        checkout.scan(NOODLES);
        checkout.scan(BEER);
        checkout.scan(MILK);
        checkout.scan(MILK);

        assertThat(checkout.checkout(System.out::println), is(new BigDecimal("8.10")));
    }

    @Test
    public void scan_withCount2_isScannedTwice() throws Exception {
        checkout.scan(BEER, 2);

        assertThat(checkout.checkout(System.out::println), is(new BigDecimal("1.20")));
    }

    @Test
    public void scan_productWithWeight_priceIsCalculatedCorrectly() throws Exception {
        BigDecimal subtotal = checkout.scan(WalMartProductType.APPLES, Weight.asKg("3.45"));

        assertThat(subtotal, is(new BigDecimal("12.08")));
    }

    @Test(expected = IllegalStateException.class)
    public void scan_notAbleToScanBeerWithWeight_exception() throws Exception {
        checkout.scan(WalMartProductType.BEER, Weight.asKg("3.45"));
    }
}
