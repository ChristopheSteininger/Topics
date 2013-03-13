package backpropagation;

import java.util.Random;

// Provides the inputs and expected outputs to train a network
// in converting polar coordinates the Cartesian equivalent.
public class PolarToCartesianIO implements TrainerIO {
    
    // Gets the suggested learning rate for this behaviour.
    @Override
    public double getLearningRate() {
        
        return 0.1;
    }
    
    // Gets the suggested momentum rate for this behaviour.
    @Override
    public double getMomentumFactor() {
        
        return 0.2;
    }
    
    // Gets the number of inputs for this behaviour.
    @Override
    public int getInputs() {
        
        return 2;
    }
    
    // Gets the number of outputs for this behaviour.
    @Override
    public int getOutputs() {
        
        return 2;
    }
    
    // Gets the number of layers for this network.
    @Override
    public int getLayers() {
        
        return 2;
    }
    
    // Gets the suggested number of medial numbers for this behaviour.
    @Override
    public int getMedialNeurons() {
        
        return 15;
    }

    private Random random = new Random();
    
    // Returns the Cartesian representation of the polar input.
    // Pre: Input length is 2.
    @Override
    public double[] getExpectedOutput(double[] input)
    {
        //Debug.Assert(input.Length == 2, "Wrong input length");

        double r = input[0];
        double theta = input[1];

        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);

        return new double[] { x, y };
    }
    
    // Returns a valid, random input to the network, where the first
    // element gives the value for r: [0, 1) and the second gives
    // the theta value: [0, 2*PI).
    @Override
    public double[] getValidInput()
    {
        double r = random.nextDouble();
        double theta = random.nextDouble() * 0.5 * Math.PI;

        return new double[] { r, theta };
    }

    // Returns a network with the correct number of inputs and outputs,
    // as well as the suggested number of medial neurons.
    @Override
    public Network getValidNetwork()
    {
        return new Network(getInputs(), getOutputs(), getMedialNeurons(), getLayers());
    }
}