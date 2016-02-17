package gti310.tp2.model;

import gti310.tp2.utils.Convert;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public class WaveFile extends MusicFile{


    public WaveFile(byte[] header) {
        super(header);
    }

    @Override
    public void readHeader() {
        super.bitPerSample = readBitPerSample();
        super.byteRate = readByteRate();
        super.numChannels = readNumChannels();
        super.sampleRate = readSampleRate();
        super.subChunk2Size = readSubChunk2Size();
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
        return new byte[0];
    }


}
