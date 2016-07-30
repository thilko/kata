package codechef

class Day {

    String lines

    def Day(String line){
        this.lines = line
    }

    public Integer maximumTemperature() {
        lines.split()[1] as Integer
    }

    public Integer minimumTemperature() {
        lines.split()[2] as Integer
    }

    public Integer number(){
        lines.split()[0] as Integer
    }

    public static List<Day> readFile() {
        new File("weather.dat").readLines().collect{ dayString ->
            new Day(dayString)
        }
    }

    Integer tempSpread() {
        maximumTemperature() - minimumTemperature()
    }
}
