package gti310.tp2.test;

import gti310.tp2.model.MusicFile;
import gti310.tp2.model.WaveFile;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

public class MusicFileTest {

    @Test
    public void testReadBytes() throws Exception {
        byte[] bytes = new byte[4];
        bytes[0] = 68;
        bytes[1] = -84;
        bytes[2] = 0;
        bytes[3] = 0;

        MusicFile target = new WaveFile(bytes);

        assertEquals(target.readBytes(0,4),44100);

    }
}