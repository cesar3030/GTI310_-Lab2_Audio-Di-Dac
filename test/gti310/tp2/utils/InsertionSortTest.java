package gti310.tp2.utils;

import gti310.tp2.model.MusicFile;
import gti310.tp2.model.WaveFile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InsertionSortTest {

    @Test
    public void testSortArray() throws Exception {

        //First try with an Integer Array
        Integer[] array = new Integer[]{3,6,7,2,1,5,7,2,0,4,5};
        InsertionSort.sort(array);
        assertArrayEquals(new Integer[]{0,1,2,2,3,4,5,5,6,7,7},array);


        //Second try with a MusicFile Array
        MusicFile m1 = new WaveFile("file 1 snr=10");
        MusicFile m2 = new WaveFile("file 2 snr=20");
        MusicFile m3 = new WaveFile("file 3 snr=30");
        MusicFile m4 = new WaveFile("file 4 snr=40");
        MusicFile m5 = new WaveFile("file 5 snr=50");
        m1.setSnr(10);
        m2.setSnr(20);
        m3.setSnr(30);
        m4.setSnr(40);
        m5.setSnr(50);

        MusicFile[] arrayMusicFile = new MusicFile[]{m5,m3,m2,m4,m1};

        InsertionSort.sort(arrayMusicFile);

        assertEquals(m1.getSnr(), arrayMusicFile[0].getSnr(), 0);
        assertEquals(m2.getSnr(),arrayMusicFile[1].getSnr(),0);
        assertEquals(m3.getSnr(),arrayMusicFile[2].getSnr(),0);
        assertEquals(m4.getSnr(),arrayMusicFile[3].getSnr(),0);
        assertEquals(m5.getSnr(),arrayMusicFile[4].getSnr(),0);


    }

    @Test
    public void testSortList() throws Exception {

        //First try with an Integer list
        List<Integer> integerList = Arrays.asList(3,6,7,2,1,5,7,2,0,4,5);

        InsertionSort.sort(integerList);

        assertEquals((Integer)0,integerList.get(0));
        assertEquals((Integer)1,integerList.get(1));
        assertEquals((Integer)2,integerList.get(2));
        assertEquals((Integer)2,integerList.get(3));
        assertEquals((Integer)3,integerList.get(4));
        assertEquals((Integer)4,integerList.get(5));
        assertEquals((Integer)5,integerList.get(6));
        assertEquals((Integer)5,integerList.get(7));
        assertEquals((Integer)6,integerList.get(8));
        assertEquals((Integer)7,integerList.get(9));
        assertEquals((Integer)7,integerList.get(10));


        //Second try with a MusicFile Array
        MusicFile m1 = new WaveFile("file 1 snr=10");
        MusicFile m2 = new WaveFile("file 2 snr=20");
        MusicFile m3 = new WaveFile("file 3 snr=30");
        MusicFile m4 = new WaveFile("file 4 snr=40");
        MusicFile m5 = new WaveFile("file 5 snr=50");
        m1.setSnr(10);
        m2.setSnr(20);
        m3.setSnr(30);
        m4.setSnr(40);
        m5.setSnr(50);

        List<MusicFile> musicFileList = Arrays.asList(m5,m3,m2,m4,m1);

        InsertionSort.sort(musicFileList);

        assertEquals(m1.getSnr(),musicFileList.get(0).getSnr(),0);
        assertEquals(m2.getSnr(),musicFileList.get(1).getSnr(),0);
        assertEquals(m3.getSnr(),musicFileList.get(2).getSnr(),0);
        assertEquals(m4.getSnr(),musicFileList.get(3).getSnr(),0);
        assertEquals(m5.getSnr(),musicFileList.get(4).getSnr(),0);


    }
}