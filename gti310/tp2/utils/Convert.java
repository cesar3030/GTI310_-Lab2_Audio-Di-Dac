package gti310.tp2.utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static com.google.common.primitives.UnsignedBytes.toInt;
/**
 * Class that provides methods to convert data
 *
 * intToBytes method find on stackOverFlow: http://stackoverflow.com/questions/1936857/convert-integer-into-byte-array-java
 */
public class Convert {


    /**
     * Method that convert a byte array into an int
     * @param in the byte array
     * @return the unsigned value of num
     */
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

    /**
     * Method that convert an int to a byte Array
     * @param i the int
     * @return The byte array corresponding to the given int
     * O(1)
     */
    public static byte toByte(int i) {
        return (byte) i;
    }




    /**
     * Method that convert an int to a byte Array
     * @param i the int
     * @return The byte array corresponding to the given int
     * O(1)
     */
    public static byte[] intToByte(int i) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        return bb.array();
    }

    /**
     * Method that convert an short to a byte Array
     * @param i the int
     * @return The byte array corresponding to the given int
     * O(1)
     */
    public static byte[] shortToByte(short i) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.putShort(i);
        return bb.array();
    }

}
