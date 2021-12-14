package y2019

import java.io.File
import kotlin.math.floor

fun main() {
    val year = "2019"; val day = "01"
    val mainPath = "src/main/resources/y$year/"

    val list = File(mainPath + year + "day" + day + ".txt").useLines { it.toMutableList() }

    println(part1(list))
    println(part2(list))
}

fun part1(input: List<String>): Int {
    var totalFuel = 0.0

    for (massOfObject in input) {
        totalFuel += floor(massOfObject.toDouble() / 3) - 2
    }
    return totalFuel.toInt()
}

fun part2(input: List<String>): Double {

    var myFuel = 0.0

    input.forEach {
        myFuel += (factorial(it.toDouble()))
    }

    return myFuel
}
/* Recursion */
private fun factorial(n: Double ): Double {

    val fuel = floor(n / 3.0) - 2
    if (fuel > 0) return fuel + factorial(fuel)

    return 0.0
}


