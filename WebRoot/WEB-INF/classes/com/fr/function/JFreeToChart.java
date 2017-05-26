// “˝»ÎJFreeChartÕº±Ì 
package com.fr.function;  
  
import java.awt.Color;  
import java.awt.image.BufferedImage;  
import java.text.NumberFormat;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.CategoryLabelPositions;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.DatasetRenderingOrder;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.renderer.category.LineAndShapeRenderer;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.DataUtilities;  
import org.jfree.data.DefaultKeyedValues;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.general.DatasetUtilities;  
import org.jfree.util.SortOrder;  
  
import com.fr.script.AbstractFunction;  
  
public class JFreeToChart extends AbstractFunction {  
    private String x, y;  
  
    public Object run(Object[] args) {  
        this.x = args[0].toString();  
        this.y = args[1].toString();  
        BufferedImage image = createImage(600, 400);  
        return image;  
    }  
  
    private BufferedImage createImage(int width, int height) {  
        CategoryDataset acategorydataset[] = createDatasets();  
        JFreeChart jfreechart = createChart(acategorydataset);  
        return jfreechart.createBufferedImage(width, height);  
    }  
  
    private CategoryDataset[] createDatasets() {  
        DefaultKeyedValues defaultkeyedvalues = new DefaultKeyedValues();  
        String[] xValue = this.x.split(",");  
        String[] yValue = this.y.split(",");  
        for (int i = 0; i < xValue.length; i++) {  
            defaultkeyedvalues.addValue(xValue[i], Double.valueOf(yValue[i]));  
        }  
        defaultkeyedvalues.sortByValues(SortOrder.DESCENDING);  
        org.jfree.data.KeyedValues keyedvalues = DataUtilities  
                .getCumulativePercentages(defaultkeyedvalues);  
        CategoryDataset categorydataset = DatasetUtilities  
                .createCategoryDataset("Languages", defaultkeyedvalues);  
        CategoryDataset categorydataset1 = DatasetUtilities  
                .createCategoryDataset("Cumulative", keyedvalues);  
        return (new CategoryDataset[] { categorydataset, categorydataset1 });  
  
    }  
  
    private JFreeChart createChart(CategoryDataset acategorydataset[]) {  
        JFreeChart jfreechart = ChartFactory.createBarChart(  
                "Freshmeat Software Projects", "Language", "Projects",  
                acategorydataset[0], PlotOrientation.VERTICAL, true, true,  
                false);  
        jfreechart.addSubtitle(new TextTitle("By Programming Language"));  
        jfreechart.addSubtitle(new TextTitle("As at 5 March 2003"));  
        jfreechart.setBackgroundPaint(Color.white);  
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();  
        categoryplot.setBackgroundPaint(Color.lightGray);  
        categoryplot.setRangeGridlinePaint(Color.white);  
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();  
        categoryaxis.setLowerMargin(0.02D);  
        categoryaxis.setUpperMargin(0.02D);  
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();  
        NumberAxis numberaxis1 = new NumberAxis("Percent");  
        numberaxis1.setNumberFormatOverride(NumberFormat.getPercentInstance());  
        categoryplot.setRangeAxis(1, numberaxis1);  
        categoryplot.setDataset(1, acategorydataset[1]);  
        categoryplot.setRenderer(1, lineandshaperenderer);  
        categoryplot.mapDatasetToRangeAxis(1, 1);  
        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);  
        return jfreechart;  
    }  
}