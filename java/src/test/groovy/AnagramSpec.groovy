import spock.lang.Specification

class AnagramSpec extends Specification {

    def "returns all anagrams"(){
        expect:
        anagrams(word) == anagrams

        where:
        word   | anagrams
        ""     | [""]
        "a"    | ["a"]
        "ab"   | ["ab", "ba"]
        "abc"  | ["abc", "acb", "bac", "bca", "cab", "cba"]
        "abcd" | ["abcd", "abdc", "acbd", "acdb", "adbc", "adcb",
                  "bacd", "badc", "bcad", "bcda", "bdac", "bdca",
                  "cabd", "cadb", "cbad", "cbda", "cdab", "cdba",
                  "dabc", "dacb", "dbac", "dbca", "dcab", "dcba"]
    }


    def anagrams(word){
        if(word.size() == 0){
            return [""]
        }else{
            def result = []
            word.toList().eachPermutation{result << it}
            return result.collect{it.join("")}
        }
    }
}
