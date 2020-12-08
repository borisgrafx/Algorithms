package lesson1;

import kotlin.NotImplementedError;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    // Трудоёмкость O(N * logN)
    // Ресурсоёмкость O(N)
    static public void sortTimes(String inputName, String outputName) throws Exception {
        File input = new File(inputName);
        Scanner scanner = new Scanner(input);
        List<String> times = new ArrayList<>();
        while (scanner.hasNextLine()) {
            times.add(scanner.nextLine());
        }
        if (times.size() < 1)
            throw new IllegalArgumentException();
        int[] digitalTimes = new int[times.size()];
        for (int i = 0; i < digitalTimes.length; i++) {
            digitalTimes[i] = datesToInt(times.get(i));
        }

        Sorts.quickSort(digitalTimes);

        PrintWriter pw = new PrintWriter(outputName);
        for (int digitalTime : digitalTimes) {
/*          System.out.println(digitalTime);
            System.out.println(datesToString(digitalTime));*/
            pw.println(datesToString(digitalTime));
        }
        pw.close();
        scanner.close();
    }

    static private int datesToInt(String date) {
        int digitalDate;
        try {
            int koef;
            String[] digitAP = date.split(" ");
            switch (digitAP[1]) {
                case "PM":
                    koef = 43200;
                    break;
                case "AM":
                    koef = 0;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            String[] digitOnly = digitAP[0].split(":");
            if (Integer.parseInt(digitOnly[0]) == 12)
                digitOnly[0] = "0";
            else if (Integer.parseInt(digitOnly[0]) == 0 || Integer.parseInt(digitOnly[0]) > 12)
                throw new IllegalArgumentException();
            digitalDate = (Integer.parseInt(digitOnly[0]) * 3600 + Integer.parseInt(digitOnly[1]) * 60
                    + Integer.parseInt(digitOnly[2])) + koef;
            if (Integer.parseInt(digitOnly[1]) > 60 || Integer.parseInt(digitOnly[2]) > 60)
                throw new IllegalArgumentException();
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
        return digitalDate;
    }

    static private String datesToString(int date) {
        String AP;
        if (date < 43200)
            AP = "AM";
        else {
            AP = "PM";
            date -= 43200;
        }
        String hours = "";
        if (date / 3600 < 10)
            hours = "0";
        if (date / 3600 != 0)
            hours += Integer.toString(date / 3600);
        else
            hours = "12";
        date %= 3600;
        String minutes = "";
        if (date / 60 < 10)
            minutes = "0";
        minutes += Integer.toString(date / 60);
        date %= 60;
        String seconds = "";
        if (date < 10)
            seconds = "0";
        seconds += Integer.toString(date);
        return hours + ":" + minutes + ":" + seconds + " " + AP;
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws Exception {
        // Трудоёмкость O(N * logN)
        // Ресурсоёмкость O(N)
        File input = new File(inputName);
        Scanner scanner = new Scanner(input);
        List<Double> temps = new ArrayList<>();
        while (scanner.hasNextLine()) {
            double next = Double.parseDouble(scanner.nextLine());
            temps.add(next * 10);
            if (next < -273.0 || next > 500.0)
                throw new NumberFormatException("Число вне требуемого диапазона");
        }
        if (temps.size() < 1)
            throw new NumberFormatException("Пустой файл");
        int[] tempsMas = new int[temps.size()];
        for (int i = 0; i < temps.size(); i++) {
            tempsMas[i] = (int) (double) temps.get(i);
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
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
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
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
