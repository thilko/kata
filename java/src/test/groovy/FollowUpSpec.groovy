import org.joda.time.DateTime
import spock.lang.Specification

class FollowUpSpec extends Specification {

    def "7days@followup.com returns 7 days from now"() {
        given:
        def mail = "7days@followup.com"

        when:
        def result = followUp(mail, DateTime.parse("2014-07-07T13:34:00"))

        then:
        result == DateTime.parse("2014-07-14T13:34:00")
    }

    def "10days@followup.com returns 7 days from now"() {
        given:
        def target = "world"
        def g = "hello ${target}"
        println g
        def mail = "10days@followup.com"

        when:
        def result = followUp(mail, DateTime.parse("2014-07-07T13:34:00"))

        then:
        result == DateTime.parse("2014-07-17T13:34:00")
    }

    def "10minutes@followup.com returns 10 minutes from now"() {
        given:
        def mail = "10minutes@followup.com"

        when:
        def result = followUp(mail, DateTime.parse("2014-07-07T13:34:00"))

        then:
        result == DateTime.parse("2014-07-07T13:44:00")
    }

    def "2Hours10minutes@followup.com returns 2 hours and 10 minutes from now"() {
        given:
        def mail = "2hours10minutes@followup.com"

        when:
        def result = followUp(mail, DateTime.parse("2014-07-07T13:34:00"))

        then:
        result == DateTime.parse("2014-07-07T15:44:00")
    }


    DateTime followUp(String followupMail, DateTime dateTime) {
        def timePart = cutTimePart(followupMail)
        def result = timePart.findAll(~/(\d+[a-z]*)/)
        return calculateTargetTime(result, dateTime)
    }

    private static calculateTargetTime(List<String> remainingParts, DateTime dateTime) {
        if (remainingParts.isEmpty()) {
            return dateTime
        }

        def pattern = ~/(.*)(days|minutes|hours)/
        def timePart = remainingParts.remove(0)
        if (timePart ==~ pattern) {
            def timeToAdd = (timePart =~ pattern)[0][1] as Integer
            String scale = (timePart =~ pattern)[0][2]
            return calculateTargetTime(remainingParts, dateTime.("plus" + scale.capitalize())(timeToAdd))
        }

        return null
    }

    private String cutTimePart(String followupMail) {
        followupMail.substring(0, followupMail.indexOf("@"))
    }
}
