package y2022

import utils.Utils

private fun main() {
    Y2022Day02().part2()
}

class Y2022Day02 {

    private val list = Utils.loadFile<String>("2022", "02")

    fun part1() {

        var totalScore = 0;

        for(round in list.filter { it.isNotEmpty() }) {
            val enemyMove = Move.enemyMove(round.first().toString())
            val myMove = Move.myMove(round.last().toString())
            val didWin = enemyMove?.let { myMove?.outcomeAgainst(it) }

            totalScore += checkRoundScore(myMove!!, didWin!! )
        }

        println("${totalScore} is the total score be if everything goes exactly according to my strategy guide")
    }

    fun part2() {

        var totalScore = 0;

        for(round in list.filter { it.isNotEmpty() }) {
            val enemyMove = Move.enemyMove(round.first().toString())
            val myMove = Move.myMove(round.last().toString())
            val myChangedMove = enemyMove?.let { myMove?.myMoveWhenEnemyMoveIs(it) }

            val didWin = enemyMove?.let { myChangedMove?.outcomeAgainst(it) }

            totalScore += checkRoundScore(myChangedMove!!, didWin!! )
        }

        println("${totalScore} is the total score be if everything goes exactly according to my strategy guide")
    }

    enum class Move(val myMoveValue: String, val enemyMoveValue: String, val point: Int) {
        ROCK("X", "A", 1),
        PAPER("Y", "B",2),
        SCISSOR("Z", "C",3);

        companion object {
            fun myMove(value: String): Move? {
                return values().find { it.myMoveValue == value }
            }
            fun enemyMove(value: String): Move? {
                return values().find { it.enemyMoveValue == value }
            }
        }

        fun outcomeAgainst(other: Move): Outcome {
            return when (this) {
                ROCK -> when(other) {
                    ROCK -> Outcome.TIE
                    PAPER -> Outcome.LOSS
                    SCISSOR -> Outcome.WIN
                }
                PAPER -> when(other) {
                    ROCK -> Outcome.WIN
                    PAPER -> Outcome.TIE
                    SCISSOR -> Outcome.LOSS
                }
                SCISSOR -> when(other) {
                    ROCK -> Outcome.LOSS
                    PAPER -> Outcome.WIN
                    SCISSOR -> Outcome.TIE
                }
            }
        }

        fun myMoveWhenEnemyMoveIs (other: Move): Move {

            val conditionNeeded: Outcome = when (this) {
                ROCK -> Outcome.LOSS
                PAPER -> Outcome.TIE
                SCISSOR -> Outcome.WIN
            }

            return when (conditionNeeded) {
                Outcome.LOSS -> when(other) {
                    ROCK -> SCISSOR
                    PAPER -> ROCK
                    SCISSOR -> PAPER
                }
                Outcome.TIE -> when(other) {
                    ROCK -> ROCK
                    PAPER -> PAPER
                    SCISSOR -> SCISSOR
                }
                Outcome.WIN -> when(other) {
                    ROCK -> PAPER
                    PAPER -> SCISSOR
                    SCISSOR -> ROCK
                }
            }
        }
    }

    private fun checkRoundScore(myMove: Move, outcome: Outcome): Int {
        return myMove.point + outcome.points
    }

    enum class Outcome(val points: Int) {
        WIN(6), TIE(3), LOSS(0)
    }

}