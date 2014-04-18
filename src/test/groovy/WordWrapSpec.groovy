import spock.lang.Specification

class WordWrapSpec extends Specification {

    def "a blank returns '', regardless of column size"(){
        when:
        def result = wordWrap('', 2)

        then:
        result == ''
    }

    def "'a' with a columnSize of 1 returns an 'a'"(){
        when:
        def result = wordWrap("a", 1)

        then:
        result == "a"
    }

    def "'a' with a columnSize of 2 returns an 'a'"(){
        when:
        def result = wordWrap("a", 2)

        then:
        result == "a"
    }

    def "'aa' with a column size of 1 throws an exception"(){
        when:
        wordWrap("tagesschau", 2)

        then:
        thrown(IllegalStateException)
    }

    def "'hello world' with a column size of 5 results in 'hello\\nworld'"(){
        when:
        def result = wordWrap("hello world", 5)

        then:
        result == "hello\nworld"
    }

    def "'hello hello hello' with a column size of 5 results in 'hello\\nhello\\nhello'"(){
        when:
        def result = wordWrap("hello hello hello", 5)

        then:
        result == "hello\nhello\nhello"
    }

    def "'hello worlds!' with a column size of 7 results in 'hello\\nworlds!'"(){
        when:
        def result = wordWrap("h llo worlds!", 7)

        then:
        result == "h llo\nworlds!"
    }

    def "test a long word"(){
        when:
        def result = wordWrap("h llo worlds! Lorum ipsum ca  t et narumeb caret.", 7)

        then:
        result == "h llo\nworlds!\nLorum\nipsum\nca  t\net\nnarumeb\ncaret."
    }

    def wordWrap(String word, int columnSize){
        return wrapify(word, columnSize)
    }

    private static String wrapify(String word, int columnSize) {
        if (word.length() <= columnSize) {
            return word
        }

        if (word == "") {
            return ""
        }

        if (word.length() > 0 && word.indexOf(" ") == -1) {
            throw new IllegalStateException("word is to long")
        }

        def blankIndex = word.indexOf(" ")

        while(blankIndex < columnSize && blankIndex != -1){
            def nextIndex = word.indexOf(" ", blankIndex + 1)
            if(nextIndex == -1 || nextIndex > columnSize){
                break
            }
            blankIndex = word.indexOf(" ", blankIndex + 1)
        }

        word.substring(0, blankIndex) + "\n" + wrapify((word.substring(blankIndex+1, word.length())), columnSize)
    }
}
