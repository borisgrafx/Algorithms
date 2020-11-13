package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    //Сложность алгоритма ~O(MN), M и N - длина первой и второй строки
    //Алгоритм из книги "Computer Science: An Interdisciplinary Approach" с использованием StringBuilder
    public static String longestCommonSubSequence(String first, String second) {
        int[][] opt = new int[first.length() + 1][second.length() + 1];
        for (int i = first.length() - 1; i >= 0; i--)
            for (int j = second.length() - 1; j >= 0; j--)
                if (first.charAt(i) == second.charAt(j))
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                else
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
        StringBuilder lcs = new StringBuilder();
        int i = 0, j = 0;
        while (i < first.length() && j < second.length()) {
            if (first.charAt(i) == second.charAt(j)) {
                lcs.append(first.charAt(i));
                i++;
                j++;
            } else if (opt[i + 1][j] >= opt[i][j + 1]) i++;
            else j++;
        }
        return lcs.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //Исправленный алгоритм со stackoverflow.com
    //Сложность алгоритма ~O(N)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int[][] data = new int[list.size()][2];
        List<Integer> ans = new ArrayList<>();
        int max_length = 0;
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = -1;
            data[i][1] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (list.get(i) > list.get(j)) {
                    if (data[i][1] <= data[j][1] + 1) {
                        data[i][1] = data[j][1] + 1;
                        data[i][0] = j;
                    }
                }
            }
            if (max_length < data[i][1])
                max_length = data[i][1];
        }
        for (int i = 0; i < list.size(); i++) {
            if (data[i][1] == max_length) {
                int current = i;
                while (current != -1) {
                    ans.add(0, list.get(current));
                    current = data[current][0];
                }
                break;
            }
        }
        return ans;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
