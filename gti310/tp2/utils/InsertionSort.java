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
    public static <T extends Comparable<? super T>>void sort(List<T> list){
        for(int i = 1; i < list.size(); i++){
            for (int j = i-1; j >=0 ; j--) {
                if(list.get(j).compareTo(list.get(j+1)) > 0){
                    swap(list,j);
                }
            }
        }
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
     * Method that swipe 2 elements at the index j and j+1 inside an array
     * @param array
     * @param j index needed to be swipe with the index j+1
     */
    private static <T> void swap(T[] array,int j){
        T tmp = array[j+1];
        array[j+1] = array[j];
        array[j] = tmp;
    }

    /**
     * Method that swipe 2 elements at the index j and j+1 inside a list
     * @param list
     * @param j
     */
    private static <T> void swap(List<T>list ,int j){
        T tmp = list.get(j+1);
        list.set(j+1,list.get(j));
        list.set(j,tmp);
    }


}
