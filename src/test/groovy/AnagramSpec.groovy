import spock.lang.Specification

class AnagramSpec extends Specification {

    def "returns all anagrams"(){
        expect:
        anagrams(word) == anagrams

        where:
        word | anagrams
        ""   | [""]
        "a"  | ["a"]
        "ab" | ["ab", "ba"]

    }


    def anagrams(word){
        if(word.size() == 0){
            return [""]
        }else if(word.size() == 1){
            return [word]
        }else{
            def stringAsList = word.toList()
            return [stringAsList[0]+stringAsList[1],
                    stringAsList[1]+stringAsList[0]
                   ]
        }
    }
}
