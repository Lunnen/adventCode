package y2023
import utils.Utils

private fun main() {
    Y2023Day07().part1() // SUCCESS > 251058093
    Y2023Day07().part2() // SUCCESS > 249781879
}

class Y2023Day07 {

    private val camelCards = Utils.loadFile("2023", "07").map { it.split(" ") }

    data class CardWithType(val card: List<String>, val cardType: CardType)

    fun part1() {

        camelCards.forEach { cardAndBid ->
            val card = cardAndBid.first()

            println(whichHand(card))
        }

        val cardsWithTypes = camelCards.map { card ->
            val cardType = whichHand(card.first())
            CardWithType(card, cardType)
        }

        val sortedCardsWithTypes = cardsWithTypes.sortedWith(
            compareByDescending<CardWithType> { it.cardType.valueOrder }
                .thenByDescending {cardValue(it.card.first()[0])}
                .thenByDescending {cardValue(it.card.first()[1])}
                .thenByDescending {cardValue(it.card.first()[2])}
                .thenByDescending {cardValue(it.card.first()[3])}
                .thenByDescending {cardValue(it.card.first()[4])}
        )

        val score = sortedCardsWithTypes.reversed()
            .mapIndexed { index, cardWithType ->
            cardWithType.card.last().toDouble() * (index + 1).toDouble()
        }
            .reduce { acc, value -> acc + value }.toInt()

        println("order " + score )

    }

    fun part2() {

        val cardsWithTypes = camelCards.map { card ->
            val cardType = whichHand(card.first(), true)
            CardWithType(card, cardType)
        }

        val sortedCardsWithTypes = cardsWithTypes.sortedWith(
            compareByDescending<CardWithType> { it.cardType.valueOrder }
                .thenByDescending {cardValue(it.card.first()[0], true)}
                .thenByDescending {cardValue(it.card.first()[1], true)}
                .thenByDescending {cardValue(it.card.first()[2], true)}
                .thenByDescending {cardValue(it.card.first()[3], true)}
                .thenByDescending {cardValue(it.card.first()[4], true)}
        )

        val score = sortedCardsWithTypes.reversed()
            .mapIndexed { index, cardWithType ->
                cardWithType.card.last().toDouble() * (index + 1).toDouble()
            }
            .reduce { acc, value -> acc + value }.toInt()

        println("Score " + score )

    }

    private fun whichHand(card: String, isPart2: Boolean = false): CardType {

        // Get unique cards from the hand
        val unique = card.toSet()

        // Count occurrences of each card
        val counts = card.groupingBy { it }.eachCount()

        // Get the count of Jokers in the hand
        val jokerCount = counts['J']

        return if (isPart2) when (unique.size) {
            1 -> CardType.FiveOfAKind
            2 -> if (counts.any { it.value == 4 }) if ('J' in unique) CardType.FiveOfAKind else CardType.FourOfAKind
            else when (jokerCount) {
                in 2..3 -> CardType.FiveOfAKind
                else -> CardType.FullHouse
            }
            3 -> if (counts.count { it.value == 2 } == 2) when (jokerCount) {
                1 -> CardType.FullHouse
                2 -> CardType.FourOfAKind
                else -> CardType.TwoPair
            }
            else when (jokerCount) {
                1, 3 -> CardType.FourOfAKind
                else -> CardType.ThreeOfAKind
            }
            4 -> when (jokerCount) {
                1, 2 -> CardType.ThreeOfAKind
                else -> CardType.OnePair
            }
            else -> if (jokerCount == 1) CardType.OnePair else CardType.HighCard
        }
        else when {
            unique.size == 1 -> CardType.FiveOfAKind
            card.groupingBy { it }.eachCount().any { it.value == 4 } -> CardType.FourOfAKind
            card.groupingBy { it }.eachCount().containsValue(3) && card.groupingBy { it }.eachCount().containsValue(2) -> CardType.FullHouse
            card.groupingBy { it }.eachCount().any { it.value == 3 } -> CardType.ThreeOfAKind
            card.groupingBy { it }.eachCount().values.count { it == 2 } == 2 -> CardType.TwoPair
            card.groupingBy { it }.eachCount().containsValue(2) -> CardType.OnePair
            unique.size == 5 -> CardType.HighCard
            else -> {
                println("Error! This should NEVER happen")
                CardType.Error
            }
        }
    }

    enum class CardType(val valueOrder: Int) {
        FiveOfAKind(7),
        FourOfAKind(6),
        FullHouse(5),
        ThreeOfAKind(4),
        TwoPair(3),
        OnePair(2),
        HighCard(1),
        Error(0)
    }

    private fun cardValue(card: Char, isPart2: Boolean = false): Int {

        val result =  when (card) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> if (isPart2) 1 else 11
            'T' -> 10
            in '2'..'9' -> card.toString().toInt()
            else -> -1
        }

        return result
    }

}

