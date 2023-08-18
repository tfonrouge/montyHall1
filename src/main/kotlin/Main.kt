import kotlin.random.Random

const val LIMIT = 100000
const val BUCKET_SIZE = 3

fun main() {
    val list = mutableListOf<BooleanArray>()
    for (i in 0..<LIMIT) {
        val winner = Random.nextInt(BUCKET_SIZE)
        val item = BooleanArray(BUCKET_SIZE) { false }
        item[winner] = true
        list.add(item)
    }
    val resultChoiceSuccess = IntArray(2) { 0 }
    list.forEach { item ->
        val playerFirstGuess = Random.nextInt(BUCKET_SIZE)
        var j = 0
        val leftChoices = IntArray(BUCKET_SIZE) { j++ }.toMutableSet()
        val winnerChoice = item.indexOf(true)
        if (playerFirstGuess != winnerChoice) {
            leftChoices.remove(winnerChoice)
            leftChoices.remove(playerFirstGuess)
            val dummyChoice = leftChoices.toList()[Random.nextInt(leftChoices.size)]
            leftChoices.add(winnerChoice)
            leftChoices.remove(dummyChoice)
            if (item[leftChoices.toList()[Random.nextInt(leftChoices.size)]]) ++resultChoiceSuccess[1]
        } else ++resultChoiceSuccess[0]
    }
    println("resultChoiceSuccess 1 rate = ${resultChoiceSuccess[0] / LIMIT.toDouble() * 100}")
    println("resultChoiceSuccess 2 rate = ${resultChoiceSuccess[1] / LIMIT.toDouble() * 100}")
}