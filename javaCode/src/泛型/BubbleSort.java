package 泛型;

import java.util.Comparator;

/**
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2021-04-19-11:36
 */
public class BubbleSort<T> {

    public Comparator<T> comp;

    public BubbleSort(Comparator<T> comp) {
        super();
        this.comp = comp;
    }

    public BubbleSort() {
        super();
    }

    @SuppressWarnings("unchecked")
    public int compare(T t1, T t2) {
        if (comp == null) {
            Comparable<T> t = (Comparable<T>) t1;
            return t.compareTo(t2);
        } else {
            return comp.compare(t1, t2);
        }
    }

    private void swap(T[] array, int i, int j) {
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < n - 1 - i; j++) {
                if (compare(array[j], array[j + 1]) > 0) {
                    flag = false;
                    swap(array, j, j + 1);
                }
            }
            if (flag) {
                break;
            }
        }
    }


}
