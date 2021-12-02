import java.io.File

class `2020Day03` {

    private val list = mutableListOf<String>()
    private val mainPath = "src/main/kotlin/y2020/files/"
    private val year = "2020"
    private val day = "03"

    fun run() {
        loadFile()
        part1()
        part2();
    }

    private fun loadFile() {
        File(mainPath + year + "day"+ day +".txt").forEachLine {
            list.add(it)
        }
    }

    private fun part1() {
        var counter = 0;
        var whereAmIxLed = 0;

        list.forEach {
            if (it[whereAmIxLed] == '#') counter++
            whereAmIxLed = (whereAmIxLed + 3) % it.length
        }

        println("$year-$day part #1 result => encountered $counter trees")
    }

    private fun part2() {

        val allCounters = ArrayList<Long>()

        allCounters.add(getTreeCount(list, 1, 1))
        allCounters.add(getTreeCount(list, 3, 1))
        allCounters.add(getTreeCount(list, 5, 1))
        allCounters.add(getTreeCount(list, 7, 1))
        allCounters.add(getTreeCount(list, 1, 2))

        /* count them together */
        var resultScore: Long = 1 // is multiplied, so needs to start as 1
        for (nr in allCounters) resultScore *= nr

        println("$year-$day part #2 result => encountered $resultScore trees")
    }

    private fun getTreeCount(list: MutableList<String>, movesToRight: Int, movesDownward: Int): Long {
        var counter: Long = 0
        var whereAmIxLed = 0

        list.forEachStep(step = movesDownward) {
            if (it[whereAmIxLed] == '#') counter++
            whereAmIxLed = (whereAmIxLed + movesToRight) % it.length
        }

        return counter
    }

    private inline fun <T> List<T>.forEachStep(step: Int = 1, action: (T) -> Unit) {
        /* https://stackoverflow.com/questions/68374213/a-foreach-function-that-supports-stepping */
        for (i in indices step step) {
            action(get(i))
        }
    }
}

