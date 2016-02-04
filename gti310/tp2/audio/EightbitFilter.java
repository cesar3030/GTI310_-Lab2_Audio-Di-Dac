package gti310.tp2.audio;

import java.io.File;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public class EightbitFilter implements AudioFilter{



    public EightbitFilter(File wavefile,String pathNewFile) {

        //TODO: Initialiser FileSource avec the given file

        //TODO: Initialiser le FileSink avec le PathNewFile


    }

    @Override
    public void process() {
        //TODO: implement process

        //On lit le fichier avec File Source

        //Créer une instance de WaveFile et lire l'entete du fichier pour setter les attibuts

        //Convert in 8bits

        //send the data to the fileSink instance

    }

    public byte[] convert(){
        //TODO: Implement algorithm
        return null;
    }
}
