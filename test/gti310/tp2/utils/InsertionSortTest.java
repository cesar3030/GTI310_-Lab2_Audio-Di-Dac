package gti310.tp2.utils;

import gti310.tp2.model.MusicFile;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class InsertionSortTest {

    @Test
    public void testSort() throws Exception {
        Integer[] array = new Integer[]{3,6,7,2,1,5,7,2,0,4,5};
        InsertionSort.sort(array);
        assertArrayEquals(new Integer[]{0,1,2,2,3,4,5,5,6,7,7},array);
    }
}