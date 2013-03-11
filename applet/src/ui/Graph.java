package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {
    
    private List<Double> data = new LinkedList<Double>();
    
    private double currentMaxValue;
    
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
        
        final int xAxisSize = 40;
        final int yAxisSize = 60;
        final int yAxisLines = 10;
        final int xAxisLines = 10;
        final int graphWidth = getWidth() - yAxisSize;
        final int graphHeight = getHeight() - xAxisSize;
        
        double[] normalisedData = getNormalisedData(graphWidth, graphHeight);
        
        Graphics graphics = getGraphics();
        paintComponent(graphics);
        
        graphics.setColor(new Color(120, 120, 120));
        
        // Draw the y axis.
        for (int i = 0; i <= yAxisLines; i++) {
            
            int yPos = graphHeight * i / yAxisLines;
            Double label = currentMaxValue * (1 - i / (double)yAxisLines);
            label = (double)Math.round(label * 10000) / 100;
            
            graphics.drawLine(yAxisSize - 5, yPos, getWidth(), yPos);
            graphics.drawString(label.toString() + "%", 0, yPos);
        }
        
        // Draw the x axis.
        for (int i = 0; i <= xAxisLines; i++) {
            
            int xPos = graphWidth * i / xAxisLines + yAxisSize - 1;
            Double label = (double)(data.size() * i / xAxisLines) / 1000;
            
            graphics.drawLine(xPos, 0, xPos, graphHeight + 5);
            graphics.drawString(label.toString() + "k", xPos, getHeight() - 5);
        }
        
        graphics.setColor(new Color(0, 0, 0));
        
        // Draw data lines.
        for (int x = 0; x < normalisedData.length; x++) {
            
            graphics.drawLine(x + yAxisSize, getHeight() - xAxisSize, x + yAxisSize,
                    getHeight() - ((int)normalisedData[x] + xAxisSize));
        }
    }
    
    // Normalises the data so that it can be displayed on the panel width
    // and height.
    private double[] getNormalisedData(int width, int height) {
        
        int averageSize = Math.max(data.size() / width, 1);
        double[] result = new double[data.size() / averageSize];
        
        currentMaxValue = Double.NEGATIVE_INFINITY;
        
        // Average the data such that it fits on the x axis.
        Iterator<Double> iterator = data.iterator();
        for (int i = 0; i < result.length; i++) {
        
            double sum = 0;
            for (int j = 0; j < averageSize; j++) {
                
                double current = iterator.next();
                sum += current;
            }
            
            result[i] = sum / averageSize;
            currentMaxValue = Math.max(result[i], currentMaxValue);
        }
        
        // Scale the data to fit the y axis.
        for (int i = 0; i < result.length; i++) {
            
            result[i] = result[i] * height / currentMaxValue;
        }
        
        return result;
    }
}
