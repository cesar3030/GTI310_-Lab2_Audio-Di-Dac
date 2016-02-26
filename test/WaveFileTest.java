import gti310.tp2.io.FileSource;
import gti310.tp2.model.WaveFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.fest.reflect.core.Reflection.method;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

public class WaveFileTest {

    private static WaveFile waveFile;

    @BeforeClass
    public static void setUp() throws IOException {
        FileSource fs = new FileSource("sample/Programme1/Test1Stereo16bits.wav");
        waveFile = new WaveFile(fs.pop(44));
        waveFile.readHeader();
        fs.close();
        /*
        waveFile.setBitPerSample(16);
        waveFile.setByteRate(32000);
        waveFile.setNumChannels(2);
        waveFile.setSampleRate(8000);
        waveFile.setSubChunk2Size(4000);
        */

    }

    @Test
    public void testReadBitPerSample() throws Exception {
        assertEquals(waveFile.getBitPerSample(), 16);
    }

    @Test
    public void testReadByteRate() throws Exception {
        assertEquals(waveFile.getByteRate(), 176400);
    }

    @Test
    public void testReadSampleRate() throws Exception {
        assertEquals(waveFile.getSampleRate(), 44100);
    }

    @Test
    public void testReadSubChunk2Size() throws Exception {
        //TODO
    }

    @Test
    public void testReadNumChannels() throws Exception {
        assertEquals(waveFile.getNumChannels(), 2);
    }

    @Test
    public void testCalculateByteRate() throws Exception {
        int value = waveFile.calculateByteRate(8);
        assertEquals(waveFile.getByteRate()/2, value);

        value = waveFile.calculateByteRate(16);
        assertEquals(waveFile.getByteRate(), value);
    }

    @Test
    public void testCalculateBlockAlign() throws Exception {
        int value = waveFile.calculateBlockAlign(8);
        assertEquals(waveFile.getNumChannels(), value);

        value = waveFile.calculateBlockAlign(16);
        assertEquals(waveFile.getNumChannels()*2, value);
    }

    @Test
    public void testCalculateSubChunk2Size() throws Exception {
        int value = waveFile.calculateSubChunk2Size(8);
        assertEquals(waveFile.getSubChunk2Size()/2, value);

        value = waveFile.calculateSubChunk2Size(16);
        assertEquals(waveFile.getSubChunk2Size(), value);
    }

    @Test
    public void testUpdateHeader() throws Exception {
        byte[] newHeader = waveFile.updateHeader(16);

        WaveFile waveFile2 = new WaveFile(newHeader);
        waveFile2.readHeader();

        assertEquals(waveFile.getSubChunk2Size(), waveFile2.getSubChunk2Size());
        assertEquals(waveFile.getBitPerSample(), waveFile2.getBitPerSample());
        assertEquals(waveFile.getSampleRate(),waveFile2.getSampleRate());
        assertEquals(waveFile.getByteRate(), waveFile2.getByteRate());
        assertEquals(waveFile.getNumChannels(),waveFile2.getNumChannels());
        assertEquals(waveFile.getChunkSize(),waveFile2.getChunkSize());

        newHeader = waveFile.updateHeader(8);

        waveFile2 = new WaveFile(newHeader);
        waveFile2.readHeader();

        assertEquals(waveFile.getSubChunk2Size()/2, waveFile2.getSubChunk2Size());
        assertEquals(waveFile.getBitPerSample()/2, waveFile2.getBitPerSample());
        assertEquals(waveFile.getSampleRate(),waveFile2.getSampleRate());
        assertEquals(waveFile.getByteRate()/2, waveFile2.getByteRate());
        assertEquals(waveFile.getNumChannels(),waveFile2.getNumChannels());
        assertEquals(36+waveFile.getSubChunk2Size(),waveFile2.getChunkSize());

    }


}