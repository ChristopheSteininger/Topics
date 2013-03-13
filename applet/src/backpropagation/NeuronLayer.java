package backpropagation;

import java.util.Random;

public class NeuronLayer {
    
    // The weights between the inputs of the layer and the neurons.
    private double[][] weights;
    
    private double[][] oldAdjustments;

    // Sets each synaptic weight between to a small random value in the range (-1, 1).
    public NeuronLayer(int inputs, int outputs, Random random) {

        weights = new double[inputs][outputs];
        oldAdjustments = new double[weights.length][weights[0].length];

        for (int y = 0; y < outputs; y++) {
            
            for (int x = 0; x < inputs; x++) {
                
                weights[x][y] = random.nextDouble() - 0.5;
            }
        }
    }
    
    // Calculates the output of the layer with the given inputs.
    public double[] getOutput(double[] inputs, boolean squash) {
        
        double[] outputs = new double[weights[0].length];

        for (int neuron = 0; neuron < outputs.length; neuron++) {
            
            outputs[neuron] = 0;

            for (int input = 0; input < inputs.length; input++) {
                
                outputs[neuron] += inputs[input] * weights[input][neuron];
            }

            if (squash) {
                
                outputs[neuron] = logistic(outputs[neuron]);
            }
        }

        return outputs;
    }
    
    // Returns the value of a sigmoid function, specifically the logistic function
    // with the given input x.
    private double logistic(double x) {
        
        return 1.0 / (1 + Math.exp(-x));
    }
    
    public double[][] getWeights() {
        
        return weights;
    }
    
    public double getOldAdjustment(int input, int neuron) {
        
        return oldAdjustments[input][neuron];
    }
    
    public void adjustWeight(int input, int neuron, double adjustment) {
        
        oldAdjustments[input][neuron] = adjustment;
        weights[input][neuron] += adjustment;
    }
}