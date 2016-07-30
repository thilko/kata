import spock.lang.Specification
import spock.lang.Unroll

class PotterSpec extends Specification {

    def "0EUR if basket is empty"() {
        given:
        def books = []

        when:
        def price = calculatePrice(new ShoppingCart(books: books))

        then:
        price == 0
    }

    @Unroll
    def "price is #price EUR if basket has only book number #bookNumber"() {
        given:
        def books = [0]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == 8

        where:
        bookNumber | price
        0          | 8
        1          | 8
        2          | 8
        3          | 8
        4          | 8
    }

    def "for [0,1] books the 5% discount is applied and the total price is 15.20"() {
        given:
        def books = [0, 1]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == 2 * 8 * 0.95
    }

    def "for [1,2] books the 5% discount is applied and the total price is 15.20"() {
        given:
        def books = [1, 2]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == 2 * 8 * 0.95
    }

    def "for [2,3] books the 5% discount is applied and the total price is 15.20"() {
        given:
        def books = [2, 3]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == 2 * 8 * 0.95
    }

    def "for [2,3,4] books the 10% discount is applied and the total price is 21.60"() {
        given:
        def books = [2, 3, 4]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == 3 * 8 * 0.9
    }

    def "for [2,3,4,5] books the 20% discount is applied and the total price is 25.60"() {
        given:
        def books = [2, 3, 4, 5]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == 4 * 8 * 0.8
    }

    def "for one 5% discount and one equal books the total price is 23.20"() {
        given:
        def books = [0, 1, 1]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == (2 * 8 * 0.95) + 8
    }

    def "for one 5% discount and two equal books the total price is 31.20"() {
        given:
        def books = [0, 1, 1, 1]

        when:
        def basketPrice = calculatePrice(new ShoppingCart(books: books))

        then:
        basketPrice == (2 * 8 * 0.95) + 8 + 8
    }


    def calculatePrice(ShoppingCart shoppingCart) {
        shoppingCart.calculateTotalPrice()
    }

    class ShoppingCart {
        def books = []
        def discounts = [new Discount(discount: 0.95, applyRate: 2),
                         new Discount(discount: 0.9, applyRate: 3),
                         new Discount(discount: 0.8, applyRate: 4)]

        Object calculateTotalPrice() {
            BigDecimal totalPrice = 0
            def uniqueBooks = books.unique(false)

            def discount = discounts.find { it.applicable(uniqueBooks) }
            if (discount) {
                totalPrice = discount.applyDiscount(books, uniqueBooks)
            }

            totalPrice + books.size() * 8
        }
    }

    class Discount {
        def discount, applyRate

        def applicable(def uniqueBooks) {
            uniqueBooks.size() == applyRate
        }

        BigDecimal applyDiscount(books, uniqueBooks) {
            uniqueBooks.each { books.remove((Object) it) }
            applyRate * 8 * discount
        }

    }
}
