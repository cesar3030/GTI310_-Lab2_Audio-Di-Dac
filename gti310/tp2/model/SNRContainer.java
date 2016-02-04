package gti310.tp2.model;

import java.util.HashMap;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public class SNRContainer {

    private final static int SIZE = 15;

    SNRBean[] data = new SNRBean[SIZE];

    public void add(String fileName,double snr ){

    }

    /**
     * Insertion sort
     */
    public void sort(){

    }

}

class SNRBean{
    private String filename;
    private Double snr;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Double getSnr() {
        return snr;
    }

    public void setSnr(Double snr) {
        this.snr = snr;
    }
}

