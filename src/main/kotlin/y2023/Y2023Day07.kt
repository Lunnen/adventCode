package y2023
import utils.Utils

private fun main() {
    Y2023Day07().part2()
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
            val cardType = whichHand(card.first())
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

        println("order " + score )

    }

    private fun whichHand(card: String): CardType {
        val counts = card.groupingBy { it }.eachCount()
        val distinctCards = card.toSet().size

        return when {
            distinctCards == 1 -> CardType.FiveOfAKind
            counts.any { it.value == 4 } -> CardType.FourOfAKind
            counts.containsValue(3) && counts.containsValue(2) -> CardType.FullHouse
            counts.any { it.value == 3 } -> CardType.ThreeOfAKind
            counts.values.count { it == 2 } == 2 -> CardType.TwoPair
            counts.containsValue(2) -> CardType.OnePair
            distinctCards == 5 -> CardType.HighCard
            else -> CardType.Error
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

        return when (card) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> if (isPart2) 1 else 11
            'T' -> 10
            in '2'..'9' -> card.toString().toInt()
            else -> -1
        }
    }

}

