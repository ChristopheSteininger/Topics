package backpropagation;

public class MedialLayer {
    
    // The weights between the inputs of the layer and the neurons.
    private double[][] weights;
    
    // Calculates the output of the layer with the given inputs.
    public double[] getOutput(double[] inputs) {
        
        //Debug.Assert(inputs.Length == weights.GetLength(0),
        //        "Input length does not match weight lengths.");
        
        double[] outputs = new double[weights[0].length];

        for (int neuron = 0; neuron < outputs.length; neuron++) {
            
            double sum = 0;

            for (int input = 0; input < inputs.length; input++) {
                
                sum += inputs[input] * weights[input][neuron];
            }

            outputs[neuron] = logistic(sum);
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
    
    public void setWeights(double[][] weights) {
        
        this.weights = weights;
    }
}