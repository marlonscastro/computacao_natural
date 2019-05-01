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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class GraficoFitness {


	public static void main(String[] args) throws IOException {
		// cria o conjunto de dados
		DefaultCategoryDataset ds = new DefaultCategoryDataset();

		ds.addValue(40.5, "Local", "1");
		ds.addValue(38.2, "Local", "2");
		ds.addValue(37.3, "Local", "3");
		ds.addValue(31.5, "Local", "4");
		ds.addValue(35.7, "Local", "5");
		ds.addValue(42.5, "Local", "6");
		
		ds.addValue(10.5, "Global", "1");
		ds.addValue(34.2, "Global", "2");
		ds.addValue(47.3, "Global", "3");
		ds.addValue(11.5, "Global", "4");
		ds.addValue(45.7, "Global", "5");
		ds.addValue(992.5, "Global", "6");

		ds.addValue(1.5, "Focal", "1");
		ds.addValue(3.2, "Focal", "2");
		ds.addValue(14.3, "Focal", "3");
		ds.addValue(21.5, "Focal", "4");
		ds.addValue(67.7, "Focal", "5");
		ds.addValue(145.5, "Focal", "6");

		// cria o gráfico
		JFreeChart grafico = 
				ChartFactory.createLineChart(
					"Gráfico da função de Fitness", 
					"Iterações", 
					"Fitness", 
					ds, 
					PlotOrientation.VERTICAL, true, true, false);
		
		OutputStream arquivo = new FileOutputStream("grafico.png");
		ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
		arquivo.close();

		JPanel panel = new ChartPanel(grafico);

		JFrame frame = new JFrame("Algoritmo PSO");
		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
		
	}

}
