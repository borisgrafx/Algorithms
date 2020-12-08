package lesson5;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private final Object empty = new Object();

    private final int bits;

    private final int capacity;

    private final Object[] storage;

    private int size = 0;

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     * <p>
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     * <p>
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != empty) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     * <p>
     * Если элемент есть в таблица, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     * <p>
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     * <p>
     * Средняя
     */
    //Трудоёмкость O(1 / (1 - A)), где A = n / N , n - число заполненных эл-тов, N - размерность массива
    //Ресурсоёмкость O(1)
    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        for (int i = startingIndex(o); i < storage.length; i++) {
            Object current = storage[i];
            if (current != null && current.equals(o)) {
                storage[i] = empty;
                size--;
                break;
            }
        }
        return true;
    }

    /**
     * Создание итератора для обхода таблицы
     * <p>
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     * <p>
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     * <p>
     * Средняя (сложная, если поддержан и remove тоже)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new tableIterator();
    }

    public class tableIterator implements Iterator<T> {
        int index = 0;
        int step = 0;
        Object cur;


        //Трудоёмкость O(1)
        //Ресурсоёмкость O(1)
        @Override
        public boolean hasNext() {
            return step < size;
        }

        //Трудоёмкость O(N)
        //Ресурсоёмкость O(1)
        @Override
        public T next() {
            if (!hasNext())
                throw new IllegalStateException();
            cur = null;
            for (; index < storage.length; index++) {
                if (cur != null && cur != empty)
                    break;
                cur = storage[index];
            }
            step++;
            return (T) cur;
        }

        //Трудоёмкость O(1)
        //Ресурсоёмкость O(1)
        @Override
        public void remove() {
            if (cur == null || cur == empty)
                throw new IllegalStateException();
            size--;
            step--;
            cur = null;
            storage[index - 1] = empty;
        }
    }
}
