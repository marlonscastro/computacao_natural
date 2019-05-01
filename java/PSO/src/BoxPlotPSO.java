import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import source.Algorithm;
import source.Pbest;
import source.Problem;
import util.Constants;

@SuppressWarnings("serial")
public class BoxPlotPSO {
	    
	private void display() throws IOException {
        JFrame f = new JFrame("BoxPlot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        
        
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
		ArrayList<Pbest> retorno_loc_sph = local.execute();
		// -----------------------------------------------------------
		
		Constants.TOPOLOGY_TYPE = Constants.GLOBAL;
		Problem.PROBLEM_TYPE = Constants.ROSENBROCK;
		Algorithm global = new Algorithm();
		ArrayList<Pbest> retorno_glob_sph = global.execute();		

		// -----------------------------------------------------------
		
		Constants.TOPOLOGY_TYPE = Constants.FOCAL;
		Problem.PROBLEM_TYPE = Constants.ROSENBROCK;
		Algorithm focal = new Algorithm();
		ArrayList<Pbest> retorno_foc_sph = focal.execute();	        
        
		int i = 1;
		final List<Double> list = new ArrayList<Double>();
		for (Pbest pbest : retorno_loc_sph) {
			list.add(pbest.getFitness());
		}
        dataset.add(list, "Local", Integer.toString(i++)); 
        list.clear();
		i = 1;
		for (Pbest pbest : retorno_glob_sph) {
			list.add(pbest.getFitness());
		}
		dataset.add(list, "Global", Integer.toString(i++)); 
		list.clear();
		i = 1;
		for (Pbest pbest : retorno_foc_sph) {
			list.add(pbest.getFitness());
		}	        
        dataset.add(list, "Focal", Integer.toString(i++));        

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
            "BoxPlot (Rosenbrock)", "Local", "", dataset, false);
        f.add(new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(620, 480);
            }
        });
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }	


    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
			try {
				new BoxPlotPSO().display();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }

}