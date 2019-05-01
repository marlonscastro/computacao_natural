import java.io.IOException;

import source.Algorithm;
import util.Constants;

public class MainSimulations {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Algorithm alg;
		for (Constants.CURRENT_SIMULATION = 0; 
				Constants.CURRENT_SIMULATION < Constants.SIMULATION_SIZE; 
				Constants.CURRENT_SIMULATION++) {
			alg = new Algorithm();
			alg.execute();
		}

	}

}
