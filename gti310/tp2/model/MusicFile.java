package gti310.tp2.model;

import gti310.tp2.utils.Convert;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public abstract class MusicFile {

    protected byte[] header;
    protected int bitPerSample;
    protected int byteRate;
    protected int sampleRate;
    protected int subChunk2Size;
    protected int numChannels;

    /**
     * Constructor
     * @param header
     */
    public MusicFile(byte[] header){
        this.header = header;
        System.out.println("header = " + header.length);
        //readHeader();
    }

    /**
     * Method that read the header of the file to set the attribute values
     */
    public abstract void readHeader();

    /**
     * Method that extract the BitPerSample value form the header
     */
    public abstract int readBitPerSample();

    /**
     * Method that extract the ByteRate value from the header
     */
    public abstract int readByteRate();

    /**
     * Method that extract the SampleRate value from the header
     */
    public abstract int readSampleRate();

    /**
     * Method that extract the SubChunk2Size value from the header
     */
    public abstract int readSubChunk2Size();

    /**
     * Method that extract the NumChannels value from the header
     */
    public abstract int readNumChannels();

    /**
     * Method that update header information for the given bitPerSample
     * @param bitPerSample The bit per sample that we want the header to convert in
     */
    public abstract byte[] updateHeader(int bitPerSample);

    /**
     * Method that calculate the ByteRate value for the given bitPerSample
     * @param bitPerSample
     * @return the ByteRate
     */
    protected abstract int calculateByteRate(int bitPerSample);

    /**
     * Method that calculate the BlockAlign value for the given bitPerSample
     * @param bitPerSample
     * @return the BlockAlign
     */
    protected abstract int calculateBlockAlign(int bitPerSample);

    /**
     * Method that calculate the SubChunk2Size value for the given bitPerSample
     * @param bitPerSample
     * @return the SubChunk2Size
     */
    protected abstract int calculateSubChunk2Size(int bitPerSample);

    /**
     * Method that calculate the SubChunk1Size value for the given bitPerSample
     * @param bitPerSample
     * @return the SubChunk1Size

    protected abstract int calculateSubChunk1Size(int bitPerSample);
     */


    /**
     * Method that return the int stored in a list of byte
     * @param offset  Where the reading starts
     * @param size    The number of bytes needed to be read
     * @return the unsigned int value
     */
    public int readBytes(int offset, int size){

        if((offset+size) > header.length){
            throw new IndexOutOfBoundsException();
        }

        //We store the bytes in the inverse order to make it easy to read (from right to left)
        byte[] selectedBytes = new byte[size];
        int index = (size-1);

        for(int i = offset; i<(offset+size); i++){
            selectedBytes[index] = header[i];
            index--;
        }

        //We convert the byte sequence into an int
        int value = Convert.readInt(selectedBytes);

        return value;
    }

    @Override
    public String toString() {
        return "MusicFile{" +
                "header=" + Arrays.toString(header) +
                ", bitPerSample=" + bitPerSample +
                ", byteRate=" + byteRate +
                ", sampleRate=" + sampleRate +
                ", subChunk2Size=" + subChunk2Size +
                ", numChannels=" + numChannels +
                '}';
    }
}
