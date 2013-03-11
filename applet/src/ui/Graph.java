package ui;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {
    
    private List<Double> data = new LinkedList<Double>();
    
    public Graph() {
        
        setBorder(BorderFactory.createTitledBorder("Total Error against Iterations"));
    }
    
    // Append new data to the graph.
    public void add(double[] newData) {
        
        for (double item : newData) {
            
            data.add(item);
        }
    }
    
    // Removes all data from the graph.
    public void clear() {
        
        data.clear();
    }
    
    // Redraws the graph width the current data.
    public void redraw() {
        
        double[] normalizedData = getNormalisesData();
        
        Graphics graphics = getGraphics();
        
        for (int x = 0; x < normalizedData.length; x++) {
            
            graphics.drawLine(x, getHeight(), x, (int)normalizedData[x]);
        }
    }
    
    // Normalises the data so that it can be displayed on the panel width
    // and height.
    private double[] getNormalisesData() {
        
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
