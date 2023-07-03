package racingcar.controller

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import racingcar.domain.ConditionGenerator

class RacingGameTest : FunSpec({
    context("모든 차가 전진하면 항상 모든 차가 우승한다. 우승자는 한 명 이상일 수 있다.") {
        val carNames = "pobi,crong,honux".split(",")
        val tryNum = 5

        val racingGame = RacingGame(
            carNames,
            object : ConditionGenerator {
                override fun generate(): Int {
                    return 4
                }
            },
        )
        racingGame.start(tryNum)
        val countWinners = racingGame.countWinners
        countWinners shouldBe carNames.size
    }

    context("특정 차를 우승자로 지정할 수 있다.") {
        val carNames = "pobi,crong,honux".split(",")
        val tryNum = 5

        val racingGame = RacingGame(carNames)
        racingGame.makeWinner("pobi")
        racingGame.start(tryNum)
        racingGame.countWinners shouldBeGreaterThanOrEqual 1
        racingGame.winnerNames shouldContain "pobi"
    }
})
