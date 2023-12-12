package y2023
import utils.Utils

private fun main() {
    Y2023Day05().part1() // SUCCESS
    // Y2023Day05().part2() // FAILED
}

class Y2023Day05 {

    private val list = Utils.loadFile("2023", "05")

    fun part1() {

        val lowestLocationValueOfSeeds = list[0].split(" ").drop(1).minOfOrNull { locationOfSeed(it.toLong()) }

        println("Result is $lowestLocationValueOfSeeds")

    }

    fun part2() {

        //val lowestLocationValueOfSeeds = list[0].split(" ").drop(1).map { locationOfSeed(it.toLong()) }.minOrNull()
        /*
        val baseValues = list[0].split(" ").drop(1).map { it.toLong() }.windowed(size = 2, step = 2, partialWindows = true)
        val test = baseValues
            .map { it[0] until (it[0] + it[1]) }
            .flatMap { ranges -> ranges.toList() }.minOfOrNull { value -> locationOfSeed(value) }
        Working in small heap space needs /small test string
        */

        /* Large heap test FAILED */
        val seeds = list[0].split(" ").drop(1).map { it.toLong() }.windowed(size = 2, step = 2, partialWindows = true)
        //val minValOfRange = seeds.flatMap { (start, length) -> (start until start + length) }.minOfOrNull { locationOfSeed(it) }

        val lowestNrRange: LongRange = seeds.minByOrNull { it.first() }?.let { pair ->
            val start = pair.first()
            val end = start + pair.last()

            (start until end)
        } ?: LongRange.EMPTY

        val lowestValue = lowestNrRange.minOfOrNull { seed -> locationOfSeed(seed) }

        // val minValue = (0 until (seeds[0][0] + seeds[0][1]) ).minOfOrNull { locationOfSeed(it) }

        println("Value is " +  lowestValue ) //minOfOrNull { locationOfSeed(it) })


        //println("Result is ${minValOfRange}")
    }

    enum class ConversionType(val typeName: String) {
        SeedToSoil("seed-to-soil"),
        SoilToFertilizer("soil-to-fertilizer"),
        FertilizerToWater("fertilizer-to-water"),
        WaterToLight("water-to-light"),
        LightToTemperature("light-to-temperature"),
        TemperatureToHumidity("temperature-to-humidity"),
        HumidityToLocation("humidity-to-location");
    }

    private fun getDestination (chosenType: ConversionType, source: Long): Long {

        fun getList (chosenType: ConversionType) : List<String> {

            var resultBack = listOf<String>()
            var isFetching = false

            // destination first, then source, then length of both
            list.forEach { line ->
                if(line.isEmpty() && isFetching) { isFetching = false
                    return@forEach
                }

                if (isFetching) resultBack += line
                if(line.contains(chosenType.typeName)) isFetching = true
            }

            return resultBack
        }

        val getDestination = getList(chosenType)
            .map { it.split(" ") }
            .filter { (it[1].toLong() until it[1].toLong() + it.last().toLong()).contains(source) }
            .map { (it[0].toLong() - it[1].toLong()) + source }
            .firstOrNull()

        println("Destination: ${ getDestination ?: source }")

        return getDestination ?: source
    }

    private fun locationOfSeed (seedSource: Long): Long {
        val soil = getDestination(ConversionType.SeedToSoil, seedSource)
        val fertilizer = getDestination(ConversionType.SoilToFertilizer, soil)
        val water = getDestination(ConversionType.FertilizerToWater, fertilizer)
        val light = getDestination(ConversionType.WaterToLight, water)
        val temp = getDestination(ConversionType.LightToTemperature, light)
        val humidity = getDestination(ConversionType.TemperatureToHumidity, temp)

        return getDestination(ConversionType.HumidityToLocation, humidity)
    }

}