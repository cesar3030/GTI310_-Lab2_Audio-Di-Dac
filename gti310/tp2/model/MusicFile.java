package gti310.tp2.model;

import gti310.tp2.utils.Convert;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public abstract class MusicFile implements Comparable{

    protected byte[] header;
    protected int bitPerSample;
    protected int byteRate;
    protected int sampleRate;
    protected int subChunk2Size;
    protected int numChannels;
    protected int chunkSize;
    //Signal to noise ration
    protected double snr;
    //The path to access to the file
    protected String filePath;

    /**
     * Constructor with file header
     * @param header
     */
    public MusicFile(byte[] header){
        this.header = header;
    }

    /**
     * Constructor with file path
     * @param filePath the path to the music file
     */
    public MusicFile(String filePath){
        this.filePath = filePath;
    }

    /**
     * Method that read the header of the file to set the attribute values
     */
    public abstract void readHeader();

    /**
     * Method that extract the ChunkSize value form the header
     */
    public abstract int readChunkSize();

    /**
     * Method that extract the BitPerSample value form the header
     */
    protected abstract int readBitPerSample();

    /**
     * Method that extract the ByteRate value from the header
     */
    protected abstract int readByteRate();

    /**
     * Method that extract the SampleRate value from the header
     */
    protected abstract int readSampleRate();

    /**
     * Method that extract the SubChunk2Size value from the header
     */
    protected abstract int readSubChunk2Size();

    /**
     * Method that extract the NumChannels value from the header
     */
    protected abstract int readNumChannels();

    /**
     * Method that update header information for the given bitPerSample
     * @param bitPerSample The bit per sample that we want the header to convert in
     */
    public abstract byte[] updateHeader(int bitPerSample);




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

    public int getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(int numChannels) {
        this.numChannels = numChannels;
    }

    public int getSubChunk2Size() {
        return subChunk2Size;
    }

    public void setSubChunk2Size(int subChunk2Size) {
        this.subChunk2Size = subChunk2Size;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getByteRate() {
        return byteRate;
    }

    public void setByteRate(int byteRate) {
        this.byteRate = byteRate;
    }

    public int getBitPerSample() {
        return bitPerSample;
    }

    public void setBitPerSample(int bitPerSample) {
        this.bitPerSample = bitPerSample;
    }

    public byte[] getHeader() {
        return header;
    }

    public void setHeader(byte[] header) {
        this.header = header;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public double getSnr() {
        return snr;
    }

    public void setSnr(double snr) {
        this.snr = snr;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int compareTo(MusicFile o){
        if(this.snr > o.getSnr())
            return 1;
        else if(this.snr < o.getSnr())
            return -1;
        else
            return 0;
    }
}
