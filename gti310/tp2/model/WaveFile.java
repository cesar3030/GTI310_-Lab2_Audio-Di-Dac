package gti310.tp2.model;

import gti310.tp2.utils.Convert;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public class WaveFile extends MusicFile{


    public WaveFile(byte[] header) {
        super(header);
    }

    public WaveFile(String filePath){ super(filePath);}


    @Override
    public void readHeader() {
        super.bitPerSample = readBitPerSample();
        super.byteRate = readByteRate();
        super.numChannels = readNumChannels();
        super.sampleRate = readSampleRate();
        super.subChunk2Size = readSubChunk2Size();
        super.chunkSize = readChunkSize();
    }

    @Override
    public int readChunkSize() {
        return readBytes(4,4);
    }

    @Override
    public int readBitPerSample() {
        return readBytes(34,2);
    }

    @Override
    public int readByteRate() {
        return readBytes(28,4);
    }

    @Override
    public int readSampleRate() {
        return readBytes(24,4);
    }

    @Override
    public int readSubChunk2Size() {
        return readBytes(40,4);
    }

    @Override
    public int readNumChannels() {
        return readBytes(22,2);
    }

    @Override
    public byte[] updateHeader(int bitPerSample) {
       updateBitPerSample(bitPerSample);
       updateByteRate(bitPerSample);
       updateBlockAlign(bitPerSample);
       updateSubChunk2Size(bitPerSample);
       updateChunkSize();

       return super.header;
    }

    /**
     * Method that update the value of the field ChunkSize in the wave original header
     */
    private void updateChunkSize() {
        int newByteRate = calculateChunkSize();
        byte[] tmp = Convert.intToByte(newByteRate);
        super.header[4]=tmp[3];
        super.header[5]=tmp[2];
        super.header[6]=tmp[1];
        super.header[7]=tmp[0];
    }

    /**
     * Method that calculate the ChunkSize field value
     * @return ChunkSize
     */
    private int calculateChunkSize() {
        return 36+super.subChunk2Size;
    }


    /**
     * Method that update the value of the field bitPerSample in the wave original header
     * @param bitPerSample
     */
    private void updateByteRate(int bitPerSample){
        int newByteRate = calculateByteRate(bitPerSample);
        byte[] tmp = Convert.intToByte(newByteRate);
        super.header[28]=tmp[3];
        super.header[29]=tmp[2];
        super.header[30]=tmp[1];
        super.header[31]=tmp[0];
    }

    /**
     * Method that update the value of the field BlockAlign in the wave original header
     * @param bitPerSample
     */
    private void updateBlockAlign(int bitPerSample){
        short newBlockAlign = (short) calculateBlockAlign(bitPerSample);
        byte[] tmp = Convert.shortToByte(newBlockAlign);
        super.header[32]=tmp[1];
        super.header[33]=tmp[0];
    }

    /**
     * Method that update the value of the field SubChunk2Size in the wave original header
     * @param bitPerSample
     */
    public void updateSubChunk2Size(int bitPerSample){
        int newSubChunk2Size = calculateSubChunk2Size(bitPerSample);
        byte[] tmp = Convert.intToByte(newSubChunk2Size);
        super.header[40]=tmp[3];
        super.header[41]=tmp[2];
        super.header[42]=tmp[1];
        super.header[43]=tmp[0];
    }

    private void updateBitPerSample(int bitPerSample) {
        byte[] tmp = Convert.shortToByte((short)bitPerSample);
        super.header[34]=tmp[1];
        super.header[35]=tmp[0];
    }


    /**
     * Method that calculate the ByteRate value for the given bitPerSample
     * @param bitPerSample
     * @return the ByteRate
     */
    public int calculateByteRate(int bitPerSample) {
        return sampleRate * numChannels * (bitPerSample/8);
    }


    /**
     * Method that calculate the BlockAlign value for the given bitPerSample
     * @param bitPerSample
     * @return the BlockAlign
     */
    public int calculateBlockAlign(int bitPerSample) {
        return numChannels * (bitPerSample/8);
    }

    /**
     * Method that calculate the SubChunk2Size value for the given bitPerSample
     * @param bitPerSample
     * @return the SubChunk2Size
     */
    public int calculateSubChunk2Size(int bitPerSample) {
        int numSample = subChunk2Size / (numChannels * (super.bitPerSample /8));
        return numSample * numChannels * (bitPerSample/8);
    }


}
