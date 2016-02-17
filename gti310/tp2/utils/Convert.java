package gti310.tp2.utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static com.google.common.primitives.UnsignedBytes.toInt;
/**
 * Class that provides methods to convert data
 */
public class Convert {

    /**
     * Method that convert a signed int into an unsigned int
     * @param num the signed number
     * @return the unsigned value of num
     */
    public static int toUnsignedInt(Byte num){
        //return ((num & 0xFF) <<  0);
            return toInt(num); //16 195 240 355 2
    }

    public static int readInt(byte[] in){

        int value = 0;
        ByteBuffer bb = ByteBuffer.wrap(in);

        if(in.length==2){
           value = bb.getShort();
        }
        else if(in.length == 4){
            value = bb.getInt();
        }

        return value;
    }

    public static short readShort(byte in){
        return (short)(in | (in << 8));
    }

    public static byte toByte(int value){
        return new Byte("1"); //TODO
    }
}
