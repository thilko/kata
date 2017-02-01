package checkout

import com.thilko.checkout.*
import org.junit.Before
import org.junit.Test
import spock.lang.Specification
import spock.lang.Unroll

import static com.thilko.checkout.WalMartProductType.*
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

class CheckoutTest extends Specification {

    def checkout
    def printer

    @Before
    void setup() throws Exception {
        printer = { message -> println "You added a new Product: ${message}" }

        checkout = new Checkout(printer)
    }

    def "if I have no products in my basket the sum at checkout is zero"() {
        expect:
        checkout.checkout(printer).getSum() == 0.0
    }


    @Unroll
    def "scan returns #price for product #product"(product, price){
        expect:
        checkout.scan(product) == price

        where:
        product|price
        POTATOES| 3.50
        NOODLES|2.00
    }


    def "if I buy a product which costs 3.50 the subtotal is 3.50"() {
        given:
        def boughtProdut = POTATOES

        when:
        BigDecimal subtotal = checkout.scan(boughtProdut)

        then:
        subtotal == 3.50
    }

    @Test
    void addProduct_twoProducts_priceIsAddedCorrectly() throws Exception {
        checkout.scan(POTATOES)
        BigDecimal subtotal = checkout.scan(NOODLES)

        assertThat(subtotal, is(new BigDecimal("5.50")))
    }

    def "the price with two products is calculated corretly"() {
        given:
        def boughtProduct = POTATOES

        when:
        def subtotal = checkout.scan(boughtProduct)

        then:
        subtotal == 3.50

        and:
        checkout.scan(NOODLES) == 5.50
    }

    def "checkout_withProducts_totalSumIsPrintedAndReturned"(){
        given:
        def products = [POTATOES, NOODLES, BEER, MILK, MILK]

        when:
        products.each { checkout.scan(it) }

        then:
        checkout.checkout(printer).getSum() == 8.10
    }

    @Test
    void scan_withCount2_isScannedTwice() throws Exception {
        checkout.scan(BEER, 2)

        assertThat(checkout.checkout(printer).getSum(), is(new BigDecimal("1.20")))
    }

    @Test
    void scan_productWithWeight_priceIsCalculatedCorrectly() throws Exception {
        BigDecimal subtotal = checkout.scan(WalMartProductType.APPLES, Weight.asKg("3.45"))

        assertThat(subtotal, is(new BigDecimal("12.08")))
    }

    @Test(expected = IllegalStateException.class)
    void scan_notAbleToScanBeerWithWeight_exception() throws Exception {
        checkout.scan(WalMartProductType.BEER, Weight.asKg("3.45"))
    }

    @Test
    void discountsCanBeApplied_2EuroForMoreThan5milks() {
        checkout.scan(WalMartProductType.MILK, 6)

        def discounter = { List<WalMartProduct> products ->
            def milks = products.groupBy { it.type }.find { type, pr -> type == WalMartProductType.MILK }
            milks.value.size() > 5 ? BigDecimal.valueOf(2) : 0;
        }

        checkout.useDiscount(discounter)

        Receipt receipt = checkout.checkout(printer)
        assertThat(receipt.getSum().intValue(), is(4))
    }
}


