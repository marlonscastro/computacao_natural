import java.awt.BasicStroke;
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
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
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

public class MainPSO {

	public static void main(String[] args) throws IOException {
		Problem.PROBLEM_TYPE = Constants.ROSENBROCK;
		Constants.TOPOLOGY_TYPE = Constants.LOCAL;

		Problem.DIMENSION_HIGH = 30;  				
		Problem.DIMENSION_LOW = -30;
			
		Constants.SWARM_SIZE = 30;
		Constants.MAX_ITERATION = 500000;
		Constants.PROBLEM_DIMENSION = 30;
		int i;
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		/*Algorithm l = new Algorithm();
		ArrayList<Pbest> local = l.execute();
	
		
		// ###################### PREPARA OS DADOS PARA GERACAO DO GRAFICO ######################

		XYSeries serie1 = new XYSeries("PSO");
		i = 1;
		for (Pbest pbest : local) {
			if(i%100==0){
				ds.addValue(pbest.getFitness(), "PSO", Integer.toString(i));
				serie1.add(new XYDataItem(i++, pbest.getFitness()));}
				i++;
		}*/
		i = 1;
		XYSeries serie2 = new XYSeries("FSS");
		FSS f= new FSS(30, 500000, 30, Constantes.ROSENBROCK);
		ArrayList<Double> a = f.executar();		
		for (Double value : a) {
			if(i%10==0){
				ds.addValue(value, "FSS", Integer.toString(i));
				serie2.add(new XYDataItem(i++, value.floatValue()));}
				i++;
		}
		
		String tipoGraf = 	Problem.PROBLEM_TYPE == 1 ?	"Sphere" 
						  : Problem.PROBLEM_TYPE == 2 ?	"Rastrigin" 
						  : "Rosenbrock";
		
		XYSeriesCollection datasetCollection = new XYSeriesCollection();
		datasetCollection.addSeries(serie2);
		//datasetCollection.addSeries(serie2);


		JFreeChart grafico = 
				ChartFactory.createXYLineChart(
					tipoGraf, 
					"Iterações", 
					"Fitness", 
					datasetCollection, 
					PlotOrientation.VERTICAL, true, true, false);
		
		// ################### Customização do Gráfico #####################
		XYPlot plot = grafico.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.red);
		renderer.setSeriesStroke(0,  new BasicStroke(2.0f));
		
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
		grafico.getLegend().setFrame(BlockBorder.NONE);
		
		
		// ###################### SALVA O GRAFICO EM UM ARQUIVO DE IMAGEM PNG ######################
		

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
