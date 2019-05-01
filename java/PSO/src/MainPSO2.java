import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;


import java.util.ArrayList;

import source.Algorithm;
import source.Pbest;
import source.Problem;
import util.Constants;

public class MainPSO2 {

	public static void main(String[] args) throws IOException {
		Constants.TOPOLOGY_TYPE = Constants.GLOBAL;
		Problem.PROBLEM_TYPE = Constants.SPHERE;
		Problem.DIMENSION_HIGH = 100;    // Sphere
		Problem.DIMENSION_LOW = -100;
			
		Constants.SWARM_SIZE = 30;
		Constants.MAX_ITERATION = 4000;
		Constants.PROBLEM_DIMENSION = 30;
		Algorithm local = new Algorithm();
		ArrayList<Pbest> retorno_loc_sph = local.execute();
		// -----------------------------------------------------------
		Problem.DIMENSION_HIGH = 5.12;   // Rastrigin 
		Problem.DIMENSION_LOW = -5.12;
		Constants.TOPOLOGY_TYPE = Constants.GLOBAL;
		Problem.PROBLEM_TYPE = Constants.RASTRIGIN;
		Algorithm global = new Algorithm();
		ArrayList<Pbest> retorno_glob_sph = global.execute();		

		// -----------------------------------------------------------
		Problem.DIMENSION_HIGH = 30;   		//Rosenbrock
		Problem.DIMENSION_LOW = -30;			
		Constants.TOPOLOGY_TYPE = Constants.GLOBAL;
		Problem.PROBLEM_TYPE = Constants.ROSENBROCK;
		Algorithm focal = new Algorithm();
		ArrayList<Pbest> retorno_foc_sph = focal.execute();	
		
		// ###################### PREPARA OS DADOS PARA GERACAO DO GRAFICO ######################
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		int i = 1;
		for (Pbest pbest : retorno_loc_sph) {
			//System.out.printf("Iteration: %d -> %f\n", i++, pbest.getFitness());
			ds.addValue(pbest.getFitness(), "Sphere", Integer.toString(i++));
		}
		i = 1;
		for (Pbest pbest : retorno_glob_sph) {
			ds.addValue(pbest.getFitness(), "Rastrigin", Integer.toString(i++));
		}
		i = 1;
		for (Pbest pbest : retorno_foc_sph) {
			ds.addValue(pbest.getFitness(), "Rosenbrock", Integer.toString(i++));
		}		


		
		// CRIA O GRAFICO 
		XYSeries series = new XYSeries("Amostra 1");
		series.add(new XYDataItem(20, 3.3));
		series.add(40.0, 2.0);
		series.add(70.0, 5.0);
		
		XYSeries series2 = new XYSeries("Amostra 2");
		series.add(new XYDataItem(18.2, 1.3));
		series.add(32.5, 2.5);
		series.add(58.7, 5.8);
		
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
				 xyseriescollection.addSeries(series);
				 xyseriescollection.addSeries(series2);
				 

		
		//XYDataset xyDataset = (XYDataset) xyseriescollection;

		 
		JFreeChart grafico = ChartFactory.createXYAreaChart
		 ("Sample XY Chart",  // Title
		 "Height",           // X-Axis label
		 "Weight",           // Y-Axis label
		 
		 xyseriescollection,          // Dataset
		 PlotOrientation.HORIZONTAL,
		true, false, false
		 
		 );
		
		XYPlot xyplot = (XYPlot) grafico.getPlot();

		xyplot.setBackgroundPaint(Color.white);
		 xyplot.setDomainGridlinePaint(Color.gray);
		 xyplot.setRangeGridlinePaint(Color.gray);


		
		
		
		// ###################### SALVA O GRAFICO EM UM ARQUIVO DE IMAGEM PNG ######################
		
		OutputStream arquivo = new FileOutputStream("imagens/focal.png");
		//ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
		arquivo.close();

		// ###################### EXIBE O GRAFICO NA TELA ######################
		
		JPanel panel = new ChartPanel(grafico);

		JFrame frame = new JFrame("Algoritmo PSO");
		frame.add(panel);
		
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);		
	}
	
}
