package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    // Трудоёмкость ~O(N)
    // Ресурсоёмкость ~O(N)
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws FileNotFoundException {
        File input = new File(inputName);
        Scanner scanner = new Scanner(input);
        List<Integer> prices = new ArrayList<>();
        while (scanner.hasNextLine()) {
            int tmp = Integer.parseInt(scanner.nextLine());
            if (tmp < 0)
                throw new IllegalArgumentException();
            prices.add(tmp);
        }
        if (prices.size() < 2)
            throw new IllegalArgumentException();
        int max = 0;
        int minPrice = 0;
        Pair<Integer, Integer> maxPair = new Pair<>(0, 0);
        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i) < prices.get(minPrice))
                minPrice = i;
            if(prices.get(i) - prices.get(minPrice) > prices.get(maxPair.getSecond()) - prices.get(maxPair.getFirst()))
                maxPair = new Pair<>(minPrice, i);
        }
        return new Pair<>(maxPair.getFirst() + 1, maxPair.getSecond() + 1);
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    // Трудоёмкость ~O(N)
    // Ресурсоёмкость ~O(1)
    static public int josephTask(int menNumber, int choiceInterval) {
        if (menNumber < 1 || choiceInterval < 1)
            throw new IllegalArgumentException();
        int s = 0;
        for (int i = 1; i <= menNumber; i++)
            s = (s + choiceInterval) % i;
        return s + 1;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    //Сложность алгоритма ~O(MN), M и N - длина первой и второй строки
    //Доработанный алгоритм с http://wp.wiki-wiki.ru/wp/index.php/Наибольшая_общая_подстрока
    static public String longestCommonSubstring(String firs, String second) {
        if (firs.equals(second))
            return firs;
        else if ((firs.length() == 0 || second.length() == 0) || (firs.length() == 1 && second.length() == 1))
            return "";
        int[][] square = new int[firs.length()][second.length()];
        int max = 0;
        int end = 0;
        for (int i = 0; i < firs.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (firs.charAt(i) == second.charAt(j)) {
                    if (i != 0 && j != 0) {
                        square[i][j] = square[i - 1][j - 1] + 1;
                    } else {
                        square[i][j] = 1;
                    }
                    if (square[i][j] > max) {
                        max = square[i][j];
                        end = i + 1;
                    }
                }
            }
        }
        return firs.substring(end - max, end);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    //Трудоёмкость O(N * sqrt(N))
    //Ресурсоёмкость O(N)
    static public int calcPrimesNumber(int limit) {
        if (limit <= 1)
            return 0;
        int k = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i))
                k++;
        }
        return k;
    }

    static boolean isPrime(int j) {
        if (j == 2) return true;
        if (j % 2 == 0) return false;
        for (int m = 3; m <= Math.sqrt(j); m += 2) {
            if (j % m == 0) return false;
        }
        return true;
    }
}
