package gti310.tp2.model;

import java.io.File;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public abstract class MusicFile {

    protected int frequency;
    protected int size;
    private File file;

    /**
     * Method that read the header of the file to set the attribute values
     */
    public abstract void readHeader();

}
