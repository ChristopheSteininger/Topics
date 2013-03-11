package ui;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {
    
    private List<Double> data = new LinkedList<Double>();
    
    public void add(double[] newData) {
        
        for (double item : newData) {
            
            data.add(item);
        }
    }
    
    public void clear() {
        
        data.clear();
    }

    public void redraw() {
        
        double[] normalizedData = getNormalizedData();
        
        Graphics graphics = getGraphics();
        
        for (int x = 0; x < normalizedData.length; x++) {
            
            graphics.drawLine(x, getHeight(), x, (int)normalizedData[x]);
        }
    }
    
    private double[] getNormalizedData() {
        
        int averageSize = Math.max(data.size() / getWidth(), 1);
        double[] result = new double[data.size() / averageSize];
        
        double maxValue = Double.NEGATIVE_INFINITY;
        
        // Average the data such that it fits on the x axis.
        Iterator<Double> iterator = data.iterator();
        for (int i = 0; i < result.length; i++) {
        
            double sum = 0;
            for (int j = 0; j < averageSize; j++) {
                
                double current = iterator.next();
                sum += current;
            }
            
            result[i] = sum / averageSize;
            maxValue = Math.max(result[i], maxValue);
        }
        
        // Scale the data to fit the y axis.
        for (int i = 0; i < result.length; i++) {
            
            result[i] = getHeight() - result[i] * getHeight() / maxValue;
        }
        
        return result;
    }
}
