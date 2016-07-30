import spock.lang.Specification

class TrainsSpec extends Specification{

    def "if input is 'AB5' the route from 'A' to 'B' costs 5"(){
        when:
        def cost = calculate("AB5")

        then:
        cost == 5
    }

    def calculate(String inputGraph){
        String from = inputGraph[0]
        String to = inputGraph[1]
        int weight = inputGraph[2] as int

        def route = new Route(from: from, to: to, weight: weight)

        route.weight
    }

    class Route {
        String from
        String to
        int weight
    }

}
