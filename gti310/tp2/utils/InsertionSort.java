package gti310.tp2.utils;
import java.util.List;

/**
 * InsertSort Class
 */
public class InsertionSort{

    /**
     * The given list is going to be sort by insertion sort.
     * @param list list to sort
     */
    public static void sort(List<Comparable> list){
        //this.sort(list.toArray());
    }

    /**
     * The given list is going to be sort by insertion sort.
     * @param array Array to sort
     */
    public static <T extends Comparable<? super T>> void sort(T[] array){
        for(int i = 1; i < array.length; i++){
            for (int j = i-1; j >=0 ; j--) {
               if(array[j].compareTo(array[j+1]) > 0){
                   swap(array,j);
               }
            }

        }
    }

    /**
     * Method that swipe 2 elements at the given index i and j inside the array
     * (index j) = (index i)-1
     * @param array
     * @param j
     */
    private static <T> void swap(T[] array,int j){
        T tmp = array[j+1];
        array[j+1] = array[j];
        array[j] = tmp;
    }


}
