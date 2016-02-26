import gti310.tp2.utils.Convert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertTest {

    byte[] b1;
    byte[] b2;
    int i1;
    int i2;

    @Before
    public void setUp() throws Exception {
        b1 = new byte[2];
        b1[0]= (byte) 10;
        b1[1] = (byte) 229;

        b2 = new byte[4];
        b2[0] = (byte) 4;
        b2[1]= (byte) 149;
        b2[2] = (byte) 11;
        b2[3]= (byte) 164;

        i1 = 32;
        i2 = -35;

    }

    @Test
    public void testReadInt() throws Exception {
        int value = Convert.readInt(b1);
        assertEquals(2789,value);

        value = Convert.readInt(b2);
        assertEquals(76876708,value);
    /*TODO Do some tests with negative values if needed
        b3 =new byte[2];
        b1[1] = (byte) 189;
        b1[0]= (byte) 255;
        value = Convert.readInt(b3);
        assertEquals(-67,value);
    */



    }

    @Test
    public void testToByte() throws Exception {

        byte value = Convert.toByte(i1);
        assertEquals((byte) 32, value);

        value = Convert.toByte(i2);
        assertEquals((byte) -35, value);
    }

    @Test
    public void testIntToByte() throws Exception {

        byte[] value = Convert.intToByte(76876708);
        /*assertEquals(4, value[0]);
        assertEquals(149, value[1]);
        assertEquals(11, value[2]);
        assertEquals(164, value[3]);*/
        assertEquals(76876708,Convert.readInt(value));
    }

    @Test
    public void testShortToByte() throws Exception {

        byte[] value = Convert.shortToByte((short)16);
        assertEquals(0, value[0]);
        assertEquals(16, value[1]);

        value = Convert.shortToByte((short)8);
        assertEquals(0, value[0]);
        assertEquals(8, value[1]);
    }
}