package checkout

import com.thilko.checkout.*
import org.junit.Before
import org.junit.Test
import spock.lang.Specification

import static com.thilko.checkout.WalMartProductType.*
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

public class _backup extends Specification {

    private Checkout checkout
    def printer

    @Before
    public void setup() throws Exception {
        printer = { textToPrint -> println(textToPrint) }
        checkout = new Checkout(printer)
    }

    def "addProduct_noProducts_0euro()"(){
        expect:
        checkout.checkout(printer).getSum() == 0.0
    }

    def "addProduct_3euro50cent_subtotalIs3Euro50cent"(){
        given:
        def product = POTATOES

        when:
        def subtotal = checkout.scan(product)

        then:
        subtotal == 3.51
    }

    @Test
    public void addProduct_twoProducts_priceIsAddedCorrectly() throws Exception {
        checkout.scan(POTATOES)
        BigDecimal subtotal = checkout.scan(NOODLES)

        assertThat(subtotal, is(new BigDecimal("5.50")))
    }

    @Test
    public void addProduct_threeProducts_priceIsAddedCorrectly() throws Exception {
        checkout.scan(POTATOES)
        checkout.scan(NOODLES)
        BigDecimal subtotal = checkout.scan(BEER)

        assertThat(subtotal, is(new BigDecimal("6.10")))
    }

    @Test
    public void checkout_withProducts_totalSumIsPrintedAndReturned() throws Exception {
        checkout.scan(POTATOES)
        checkout.scan(NOODLES)
        checkout.scan(BEER)
        checkout.scan(MILK)
        checkout.scan(MILK)

        assertThat(checkout.checkout(printer).getSum(), is(new BigDecimal("8.10")))
    }

    @Test
    public void scan_withCount2_isScannedTwice() throws Exception {
        checkout.scan(BEER, 2)

        assertThat(checkout.checkout(printer).getSum(), is(new BigDecimal("1.20")))
    }

    @Test
    public void scan_productWithWeight_priceIsCalculatedCorrectly() throws Exception {
        BigDecimal subtotal = checkout.scan(WalMartProductType.APPLES, Weight.asKg("3.45"))

        assertThat(subtotal, is(new BigDecimal("12.08")))
    }

    @Test(expected = IllegalStateException.class)
    public void scan_notAbleToScanBeerWithWeight_exception() throws Exception {
        checkout.scan(WalMartProductType.BEER, Weight.asKg("3.45"))
    }

    @Test
    public void discountsCanBeApplied_2EuroForMoreThan5milks() {
        checkout.scan(WalMartProductType.MILK, 6)

        def discounter = { List<WalMartProduct> products ->
            def milks = products.groupBy { it.type }.find({ type, pr -> type == WalMartProductType.MILK })
            milks.value.size() > 5 ? BigDecimal.valueOf(2) : 0;
        }

        /*
        def discounter = { def products ->
            Map<WalMartProductType, List<WalMartProduct>> groupedByTypes = products.stream()
                    .collect(Collectors.groupingBy(WalMartProduct::getType))

            if(groupedByTypes.get(WalMartProductType.MILK).size() > 5){
                return BigDecimal.valueOf(2)
            }

            return BigDecimal.ZERO
        }                      */

        checkout.useDiscount(discounter)

        Receipt receipt = checkout.checkout(printer)
        assertThat(receipt.getSum().intValue(), is(4))
    }
}

