package gti310.tp2.audio;

import gti310.tp2.io.FileSink;
import gti310.tp2.io.FileSource;
import gti310.tp2.model.WaveFile;
import gti310.tp2.utils.Convert;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Arrays;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public class EightBitFilter implements AudioFilter{

    //Number of bytes we want to pop from the file at a time
    int nb16BitsSampleRead = 100;
    private String pathWaveFile;
    private String pathNewFile;
    private FileSource fileSource = null;
    private FileSink fileSink = null;
    private WaveFile waveFile = null;

    public EightBitFilter(String pathWaveFile, String pathNewFile) {
        this.pathWaveFile = pathWaveFile;
        this.pathNewFile = pathNewFile;
    }

    @Override
    public void process() {

        try {
            this.fileSource = new FileSource(pathWaveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //We retrieve header information
        byte[] tmp = fileSource.pop(44);
        //We create a waveFile instance to manipulate the header
        this.waveFile = new WaveFile(tmp);
        //We read the header
        this.waveFile.readHeader();

        if(this.waveFile.getBitPerSample()==8){
            fileSource.close();
            System.out.println("It's already a 8 Bits file");
        }
        else{
            try {

                this.fileSink = new FileSink(pathNewFile);

                System.out.println(waveFile.toString());
                //We update the header value and push it in the new file
                byte[] newHeader = this.waveFile.updateHeader(8);

                WaveFile test= new WaveFile(newHeader);
                test.readHeader();
                System.out.println(test.toString());

                fileSink.push(newHeader);
                fileSource.close();

                //We convert data in 8bits and push it into the new file
                this.convertData();

                fileSink.close();
                System.out.println("Conversion from 16 Bits to 8 Bits Done");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }





    }



    /**
     * Method that read the original file 16bits data, convert them into a 8bits and push the new values into the new file
     */
    private void convertData(){


        //Buffer that store the 8bites values before to be push in the new file
        byte[] data8Bits = new byte[this.nb16BitsSampleRead/2];
        int index = 0;

        try {
            this.fileSource = new FileSource(pathWaveFile);
            //We skip the 44 fist byte that contain header
            fileSource.pop(44);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] tmp = new byte[2];
        //Array that store the bytes from the original file
        byte[] data16Bits = fileSource.pop(this.nb16BitsSampleRead);


        while (data16Bits != null){

            for(int i=0;i<data16Bits.length;i=i+2){
                tmp[0]=data16Bits[i];
                tmp[1]=data16Bits[i+1];
                data8Bits[index] = to8bits(tmp);
                index++;
            }

            this.fileSink.push(data8Bits);
            index=0;
            data16Bits = this.fileSource.pop(nb16BitsSampleRead);
        }

        this.fileSource.close();
    }

    /**
     * Method that convert a 16 bits value stored in 2 bytes in a 8 bits stored in a single byte
     * @param data Array of two bytes
     * @return the 8 bits value
     */
    private byte to8bits(byte[] data){
        byte[] tmp = new byte[2];
        tmp[0]=data[1];
        tmp[1]=data[0];

        int value = Convert.readInt(tmp);

        int newValue = (byte)((value / 256)+128);

        return Convert.toByte(newValue);
    }

}
