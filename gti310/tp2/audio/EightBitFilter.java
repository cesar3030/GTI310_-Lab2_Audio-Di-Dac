package gti310.tp2.audio;

import gti310.tp2.io.FileSink;
import gti310.tp2.io.FileSource;
import gti310.tp2.model.WaveFile;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public class EightBitFilter implements AudioFilter{

    private FileSource fileSource = null;
    private FileSink fileSink = null;
    private WaveFile waveFile = null;

    public EightBitFilter(String pathWaveFile, String pathNewFile) {

        try {

            this.fileSource = new FileSource(pathWaveFile);
            this.fileSink = new FileSink(pathNewFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() {
        //TODO: implement process

        //We retrieve header informations
        byte[] tmp = fileSource.pop(44);
        //Créer une instance de WaveFile et lire l'entete du fichier pour setter les attibuts
        this.waveFile = new WaveFile(tmp);
        this.waveFile.readHeader();
        System.out.println(waveFile.toString());
        //Convert in 8bits

        //send the data to the fileSink instance

    }

    public byte[] convert(){
        //TODO: Implement algorithm
        return null;
    }
}
