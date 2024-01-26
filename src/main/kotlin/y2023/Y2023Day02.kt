package y2023

import utils.Utils

private fun main() {
    //Y2023Day02().part1()
    Y2023Day02().part2()
}

class Y2023Day02 {

    private val list = Utils.loadFile("2023", "02")

    fun gameNeeds (gameSets:  List<String>): List<HashMap<String, Int>> {
        var gameSetNeeds = listOf( hashMapOf<String, Int>())

        gameSets.forEachIndexed() { _, set ->

            val setNeeds = hashMapOf<String, Int>()

            set.split(",").forEachIndexed() { itemIndex, item ->
                val amount = item.trim().split(" ").first()
                val type = item.trim().split(" ").last()

                val current = setNeeds[type] ?: 0
                setNeeds[type] = current + amount.toInt()
            }

            gameSetNeeds += setNeeds
        }
        return gameSetNeeds.filter { it.isNotEmpty() }
    }

    fun part1() {

        fun gameIsPossible (available: MutableMap<String, Int>, needs: List<HashMap<String, Int>>): Boolean {

            var allSetsOk = true

            needs.forEach { eachSet ->
                var localSet = available.toMutableMap()

                eachSet.forEach { (key, value) ->
                    val current = localSet[key] ?: 0
                    localSet[key] = current - value
                }

                if (localSet.any { it.value < 0 } ) allSetsOk = false
            }

            return  allSetsOk // All values have to have items left
        }

        fun possibleGamesIds (list: List<String>, available: MutableMap<String, Int>): List<Int> {
            var gameIdsPossible = mutableListOf<Int>()

            list.forEach { game ->
                val gameAndContentDivided = game.split(':')
                val gameId = gameAndContentDivided.first().replace("Game", "").replace("\\s".toRegex(), "")
                val gameSets = gameAndContentDivided.last().split(";")

                if (gameIsPossible(available.toMutableMap(), gameNeeds(gameSets)) ) gameIdsPossible += gameId.toInt()
            }

            return gameIdsPossible
        }

        // loaded with 12 red cubes, 13 green cubes, and 14 blue cubes.
        val available = hashMapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        val possibleGames = possibleGamesIds(list, available.toMutableMap())

        println(possibleGames.sum())
    }

    fun part2() {

        fun leastNeededAvailabilityOfGameSet (needs: List<HashMap<String, Int>>): HashMap<String, Int> {

            val minimumNeeds = hashMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )

            needs.forEach { eachSet ->
                eachSet.forEach { (key, value) ->
                    if (minimumNeeds[key]!! <= value) {
                        minimumNeeds[key] = value
                    }

                }
            }

            return  minimumNeeds
        }

        val totalScore = list.sumOf { game ->
            val gameSets = game.substringAfterLast(':').split(";")
            val least = leastNeededAvailabilityOfGameSet(gameNeeds(gameSets))
            least.values.reduce { sum, element -> sum * element }
        }

        println("Total score is $totalScore")
    }

}