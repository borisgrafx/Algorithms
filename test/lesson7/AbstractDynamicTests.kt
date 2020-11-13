package lesson7

import kotlin.test.assertEquals

abstract class AbstractDynamicTests {
    fun longestCommonSubSequence(longestCommonSubSequence: (String, String) -> String) {
        assertEquals(
            "flpqrvwxz",
            longestCommonSubSequence("abcdefghijklmnopqrstuvwxyz", "f,dult`;pbqrkvyjghcnea[wxio]sm'.z")
        ) //Английский алфавит и русский алфавит английской раскладкой
        assertEquals("мм мм", longestCommonSubSequence("мем мим", "мама математика"))
        assertEquals("мм мама", longestCommonSubSequence("мем мама", "мим математика"))
        assertEquals("", longestCommonSubSequence("", ""))
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("1", longestCommonSubSequence("1", "1"))
        assertEquals("13", longestCommonSubSequence("123", "13"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
        val expectedLength = "e kerwelkkd r".length
        assertEquals(
            expectedLength, longestCommonSubSequence(
                "oiweijgw kejrhwejelkrw kjhdkfjs hrk",
                "perhkhk lerkerorwetp lkjklvvd durltr"
            ).length, "Answer must have length of $expectedLength, e.g. 'e kerwelkkd r' or 'erhlkrw kjk r'"
        )
        val expectedLength2 = """ дд саы чтых,
евшнео ваа се сви дн.
        """.trimIndent().length
        assertEquals(
            expectedLength2, longestCommonSubSequence(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength2"
        )
    }

    fun longestIncreasingSubSequence(longestIncreasingSubSequence: (List<Int>) -> List<Int>) {
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2), longestIncreasingSubSequence(listOf(2, 1)))
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
        assertEquals(
            listOf(23, 34, 56, 87, 91, 98, 140, 349), longestIncreasingSubSequence(
                listOf(
                    23, 76, 34, 93, 123, 21, 56, 87, 91, 12, 45, 98, 140, 12, 5, 38, 349, 65, 94,
                    45, 76, 15, 99, 100, 88, 84, 35, 88
                )
            )
        )
        assertEquals(listOf(0), longestIncreasingSubSequence(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0)))
        assertEquals(
            listOf(-15, -13, -8, -7, -1, 10),
            longestIncreasingSubSequence(listOf(-3, -5, -10, -15, -2, 0, -13, -8, -9, -7, -1, 10))
        )
        assertEquals(
            listOf(10, 20, 35, 85, 90, 105, 110, 200, 300, 400, 450, 500, 550, 670, 700, 750, 760, 1000, 10000),
            longestIncreasingSubSequence(
                listOf(
                    10, 20, 5, 35, 30, 90, 85, 90, 130, 105, 15, 25, 440, 405, 435, 440, 430, 305, 50, 60, 100, 90, 45,
                    110, 85, 95, 90, 90, 90, 1000, 450, 500, 10000, 5000, 1500, 500, 200, 300, 1000, 400, 5000, 10000,
                    145, 500, 600, 1010, 1200, 110, 1110, 10000, 450, 15, 100, 500, 400, 800, 950, 1000, 10000, 9000,
                    8000, 9500, 150, 1000, 420, 550, 670, 100, 60, 700, 800, 750, 760, 1000, 800, 10000, 600, 800, 240,
                    260, 375
                )
            )
        )
    }

    fun shortestPathOnField(shortestPathOnField: (String) -> Int) {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
        assertEquals(43, shortestPathOnField("input/field_in3.txt"))
        assertEquals(28, shortestPathOnField("input/field_in4.txt"))
        assertEquals(222, shortestPathOnField("input/field_in5.txt"))
        assertEquals(15, shortestPathOnField("input/field_in6.txt"))
    }

}