import kotlin.random.Random

const val LIMIT = 100000
const val BUCKET_SIZE = 3

fun main() {
    val list = Array(LIMIT) {
        BooleanArray(BUCKET_SIZE) { false }.also {
            it[Random.nextInt(BUCKET_SIZE)] = true
        }
    }
    val resultChoiceSuccess = IntArray(2) { 0 }
    list.forEach { item ->
        val playerFirstGuess = Random.nextInt(BUCKET_SIZE)
        val winnerChoice = item.indexOf(true)
        if (playerFirstGuess != winnerChoice) {
            val leftChoices = IntArray(BUCKET_SIZE) { it }.toMutableSet()
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