import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;

public class Grafico2 {

	public static void main(String[] args) {
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

		ds.addValue(10.5, "Focal", "1");
		ds.addValue(34.2, "Focal", "2");
		ds.addValue(47.3, "Focal", "3");
		ds.addValue(11.5, "Focal", "4");
		ds.addValue(45.7, "Focal", "5");
		ds.addValue(992.5, "Focal", "6");		

		// cria o gráfico
		JFreeChart grafico = 
				ChartFactory.createLineChart(
					"Gráfico da função de Fitness", 
					"Iterações", 
					"Fitness", 
					ds, 
					PlotOrientation.VERTICAL, true, true, false);
		
		XYPlot plot = (XYPlot) grafico.getPlot();
		LegendTitle lt = new LegendTitle(plot);
		lt.setItemFont(new Font("Dialog", Font.PLAIN, 9));
		lt.setBackgroundPaint(new Color(200, 200, 255, 100));
		lt.setFrame(new BlockBorder(Color.white));
		lt.setPosition(RectangleEdge.BOTTOM);
		XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, lt,RectangleAnchor.BOTTOM_RIGHT);

		ta.setMaxWidth(0.48);
		plot.addAnnotation(ta);

	}

}
