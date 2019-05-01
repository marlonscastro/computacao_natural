import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import source.Algorithm;
import source.Pbest;
import source.Problem;
import util.Constants;

/**
 * @see https://stackoverflow.com/a/35814571/230513
 */
public class BoxPlot {

    private static final String ROW_KEY = "City";
    private static final String ROW_KEY2 = "Bairro";
    private static final String ROW_KEY3 = "teste";
    
    @SuppressWarnings("serial")
	private void display() {
    	
        JFrame f = new JFrame("BoxPlot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultBoxAndWhiskerCategoryDataset data = new DefaultBoxAndWhiskerCategoryDataset();
        
    	Constants.TOPOLOGY_TYPE = Constants.LOCAL;
		Problem.PROBLEM_TYPE = Constants.ROSENBROCK;
		//Problem.DIMENSION_HIGH = 100;    // Sphere
		//Problem.DIMENSION_LOW = -100;
		
		//Problem.DIMENSION_HIGH = 5.12;   // Rastrigin 
		//Problem.DIMENSION_LOW = -5.12;
		
		Problem.DIMENSION_HIGH = 30;   		//Rosenbrock
		Problem.DIMENSION_LOW = -30;		
		
		Constants.SWARM_SIZE = 30;
		Constants.MAX_ITERATION = 10000;
		Constants.PROBLEM_DIMENSION = 30;
		Algorithm local = new Algorithm();
		ArrayList<Pbest> retorno_loc_sph = null;
		try {
			retorno_loc_sph = local.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// -----------------------------------------------------------
		
		Constants.TOPOLOGY_TYPE = Constants.GLOBAL;
		Problem.PROBLEM_TYPE = Constants.ROSENBROCK;
		Algorithm global = new Algorithm();
		ArrayList<Pbest> retorno_glob_sph = null;
		try {
			retorno_glob_sph = global.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		// -----------------------------------------------------------
		
		Constants.TOPOLOGY_TYPE = Constants.FOCAL;
		Problem.PROBLEM_TYPE = Constants.ROSENBROCK;
		Algorithm focal = new Algorithm();
		ArrayList<Pbest> retorno_foc_sph = null;
		try {
			retorno_foc_sph = focal.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	        
        
		int i = 1;
		final List<Double> list = new ArrayList<Double>();
		for (Pbest pbest : retorno_loc_sph) {
			list.add(pbest.getFitness());
		}
        data.add(list, "Local", Integer.toString(i++)); 
        list.clear();
		i = 1;
		for (Pbest pbest : retorno_glob_sph) {
			list.add(pbest.getFitness());
		}
		data.add(list, "Global", Integer.toString(i++)); 
		list.clear();
		i = 1;
		for (Pbest pbest : retorno_foc_sph) {
			list.add(pbest.getFitness());
		}	        
        data.add(list, "Focal", Integer.toString(i++));          	
    	
    	

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
            "Box and Whisker Chart", "Global", "", data, false);
        f.add(new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 480);
            }
        });
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private List<Number> getInputData() {
        Scanner s = new Scanner("30 36 46 55 65 22 81 80 71 59 44 34");
        List<Number> list = new ArrayList<>();
        do {
            list.add(s.nextDouble());
        } while (s.hasNext());
        s.close();
        return list;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new BoxPlot()::display);
    }
}