import spock.lang.Specification

public class DiamondSpec extends Specification {

    def "A will return A"() {
        given:
        def input = "A"

        when:
        String diamondString = toDiamond(input)

        then:
        diamondString == "A"
    }

    def "B will return ' A \nB B\n A '"() {
        given:
        def input = "B"

        when:
        String diamondString = toDiamond(input)

        then:
        diamondString == " A \nB B\n A "
    }

    def "top of diamond for 'B' is ' A '"() {
        expect:
        " A " == top("B")

    }

    def "top of diamond for 'C' is '  A  '"() {
        expect:
        "  A  " == top("C")

    }

    def "side of 'B' diamond for 'B' is 'B B'"() {
        expect:
        "B B" == side("B", "B")
    }

    def "side of 'C' diamond for 'C' is 'C  C'"() {
        expect:
        "C   C" == side("C", "C")
    }

    def "side of 'D' diamond for 'D' is 'D  D'"() {
        expect:
        "D     D" == side("D", "D")
    }

    def "side of 'E' diamond for 'E' is 'E       E'"() {
        expect:
        "E       E" == side("E", "E")
    }

    def "side of 'C' diamond for 'B' is ' B B '"() {
        expect:
        " B B " == side("B", "C")
    }

    def "side of 'D' diamond for 'C' is ' C   C '"() {
        expect:
        " C   C " == side("C", "D")
    }

    def letters = ["A", "B", "C", "D", "E"]

    // A, B, C, D
    // 0, 1, 2, 3
    //       3, 5

    def side(String currentLetter, String diamondToPrint) {
        def indexOfLetter = letters.indexOf(diamondToPrint)
        def number = indexOfLetter == 1 ? 1 : indexOfLetter + (indexOfLetter - 1)
        def whitespaces = " " * number

        def padding = " " * (letters.indexOf(diamondToPrint) - letters.indexOf(currentLetter))

        "${padding}${currentLetter}${whitespaces - (padding * 2)}${currentLetter}${padding}"
    }

    def top(String letter) {
        def whitespaces = " " * letters.indexOf(letter)
        "${whitespaces}A${whitespaces}"
    }

    String toDiamond(String letter) {
        if (letter == "A") return top(letter)

        def list = [top(letter), side(letter[letter.indexOf(letter) - 1], letter), top(letter)]

        return list.join("\n")
    }
}

/*

 A
B B
 A

4 (pos2)
  A
 B B
C   C
 B B
  A

6 (pos3)
   A
  B B
 C   C
D     D
 C   C
  B B
   A

 */
