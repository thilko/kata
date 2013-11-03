import spock.lang.Specification

class AnagramSpec extends Specification {

    def "returns all anagrams"(){
        given:
        def word = "ab";

        when:
        def anagrams = anagramify word

        then:
        anagrams == []
    }
}
