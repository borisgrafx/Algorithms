package lesson1;

import kotlin.NotImplementedError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws Exception {
        File input = new File(inputName);
        Scanner scanner  = new Scanner(input);
        ArrayList<String> times = new ArrayList<>();
        while(scanner.hasNextLine())
            times.add(scanner.nextLine());
        int[] digitalTimes = new int[times.size()];
        int k = 0;
        try {
            for (String clock : times) {
                int koef;
                String[] digitAP = clock.split(" ");
                switch (digitAP[1]) {
                    case "PM":
                        koef = 1;
                        break;
                    case "AM":
                        koef = -1;
                        break;
                    default:
                        throw new Exception("Неверный формат даты");
                }
                String[] digitOnly = digitAP[0].split(":");
                if (Integer.parseInt(digitOnly[0]) == 12)
                    digitOnly[0] = "0";
                else if (Integer.parseInt(digitOnly[0]) == 0)
                    throw new Exception("Неверный формат даты");
                digitalTimes[k] = (Integer.parseInt(digitOnly[0]) * 3600 + Integer.parseInt(digitOnly[1]) * 60 + Integer.parseInt(digitOnly[2])) * koef;
                if (Math.abs(digitalTimes[k]) >= 46800)
                    throw new Exception("Неверный формат даты");
                k++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        for (int i = 0; i < digitalTimes.length; i++) {
            int min = digitalTimes[i];
            int min_i = i;
            for (int j = i+1; j < digitalTimes.length; j++) {
                if (digitalTimes[j] < min) {
                    min = digitalTimes[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                int tmp = digitalTimes[i];
                String tmp2 = times.get(i);
                digitalTimes[i] = digitalTimes[min_i];
                times.set(i, times.get(min_i));
                digitalTimes[min_i] = tmp;
                times.set(min_i, tmp2);
            }
        }


        for (int i = 0; i < digitalTimes.length; i++) {
            if (digitalTimes[i] < 0) {
                int max = digitalTimes[i];
                int max_i = i;
                for (int j = i + 1; j < digitalTimes.length; j++) {
                    if (digitalTimes[j] > max && digitalTimes[j] < 0) {
                        max = digitalTimes[j];
                        max_i = j;
                    }
                }
                if (i != max_i) {
                    int tmp = digitalTimes[i];
                    String tmp2 = times.get(i);
                    digitalTimes[i] = digitalTimes[max_i];
                    times.set(i, times.get(max_i));
                    digitalTimes[max_i] = tmp;
                    times.set(max_i, tmp2);
                }
            } else
                break;
        }

        //System.out.println(times);
        PrintWriter pw = new PrintWriter(outputName);
        for (String sortedTime : times) {
            pw.println(sortedTime);
        }
        pw.close();
        scanner.close();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws Exception{
        File input = new File(inputName);
        Scanner scanner  = new Scanner(input);
        ArrayList<Double> temps = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {
                double next = Double.parseDouble(scanner.nextLine());
                temps.add(next * 10);
                if (next < -273.0 || next > 500.0)
                    throw new NumberFormatException("Число вне требуемого диапазона");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        int[] tempsMas = new int[temps.size()];
        for (int i = 0; i < temps.size(); i++) {
            tempsMas[i] = (int)(double)temps.get(i);
        }
        Sorts.quickSort(tempsMas);
        for (int i = 0; i < temps.size(); i++) {
            temps.set(i, (double) tempsMas[i] / 10);
        }
        PrintWriter pw = new PrintWriter(outputName);
        for (Double sortedTemps : temps) {
            pw.println(sortedTemps);
        }
        pw.close();
        scanner.close();
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
