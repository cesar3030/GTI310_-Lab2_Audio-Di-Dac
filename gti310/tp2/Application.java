package gti310.tp2;

import gti310.tp2.audio.EightBitFilter;
import gti310.tp2.audio.SNRFilter;

public class Application {

	/**
	 * Launch the application
	 * @param args This parameter is ignored
	 */
	public static void main(String args[]) {

        if(args[0].equals("programme1")){
            new EightBitFilter(args[1],args[2]).process();
        }
        else if(args[0].equals("programme2")){
            new SNRFilter(args).process();
        }
        else{
            System.out.println("You need to give a first parameter !");
        }

	}
}
