package gti310.tp2.audio;

import gti310.tp2.io.FileSource;
import gti310.tp2.model.MusicFile;
import gti310.tp2.model.WaveFile;
import gti310.tp2.utils.Convert;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Iron_Cesar on 16-02-04.
 */
public class SNRFilter implements AudioFilter {

    private static final int NB_BYTES_TO_POP = 60;
    private ArrayList<MusicFile> files;
    private FileSource originalFile = null;
    private FileSource comparedFile = null;

    public SNRFilter(String[] args){
        try {
            this.originalFile = new FileSource(args[1]);
           // this.originalFile.mark();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred during when SNRFilter tried to read the original file: "+originalFile);
            e.printStackTrace();
        }

        //We instanciate an arrayListe to store all the file that gonna have to be check
        this.files = new ArrayList<MusicFile>();
        //We add the original file at the beginning of the list
        files.add(new WaveFile(args[1]));

        //We add the others file
        for (int i = 2; i <args.length; i++) {
            files.add(new WaveFile(args[i]));
        }
    }

    @Override
    public void process() {

        MusicFile original = files.get(0);
        //We test if there is enough files to compare
        if(files.size() > 2){

            for (int i = 1; i < files.size(); i++) {

                MusicFile fileInvestigate = files.get(i);

                try {
                    originalFile = new FileSource(original.getFilePath());
                    comparedFile = new FileSource(fileInvestigate.getFilePath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //we calculate the SNR and set it to the current file being investigate
                double snr = calculateSNR(comparedFile);
                fileInvestigate.setSnr(snr);
            }

            originalFile.close();
            comparedFile.close();

            System.out.println("-------- RESULT --------");
            for (int i = 1; i < files.size(); i++) {
                MusicFile fileInvestigate = files.get(i);
                System.out.println(fileInvestigate.getFilePath() + " \t \t \t  SNR: "+ fileInvestigate.getSnr());
            }
            System.out.println("------------------------");

        }
        else{
            System.out.println("You need to give an original file and one or more file to compare");
        }

    }

    /**
     * Method that compare the given file to the original file and return the SNR
     * @param fileToCompare The file we will compare
     * @return the SNR
     */
    public double calculateSNR(FileSource fileToCompare){

        double originalSampleSum = 0;
        double comparedSampleSum = 0;

        //We skip the file header
        originalFile.skip(44);
        comparedFile.skip(44);

        byte[] tmpOriginal = originalFile.pop(NB_BYTES_TO_POP);
        byte[] tmpCompared = comparedFile.pop(NB_BYTES_TO_POP);
        byte[] tmp = new byte[]{0,0};
        byte[] tmp2 = new byte[]{0,0};


        while(tmpCompared != null || tmpOriginal != null){
            for (int i = 0; i < NB_BYTES_TO_POP; i++) {
                tmp[1]=tmpOriginal[i];
                tmp2[1]=tmpCompared[i];

                int original = Convert.readInt(tmp);
                int compared = Convert.readInt(tmp2);

                originalSampleSum+=Math.pow(original,2);
                comparedSampleSum+=Math.pow((original-compared),2);
            }

            tmpOriginal = originalFile.pop(NB_BYTES_TO_POP);
            tmpCompared = comparedFile.pop(NB_BYTES_TO_POP);
        }

        return 10*Math.log10(originalSampleSum/comparedSampleSum);
    }
}
