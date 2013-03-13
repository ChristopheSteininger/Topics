package backpropagation;

import java.util.Random;

// Simulates a simple feed forward network with one hidden (medial) layer
// containing a variable number of neurons, as well as a variable number
// of input and output neurons. The network can also calculate the output
// given its inputs.
public class Network {
    
    // The medial layers in the network.
    private MedialLayer[] layers;
    
    // The output of each medial neuron.
    private double[][] medout;
    
    // The weights leading to the output layer.
    private double[][] synOut;
    
    // The number of neurons in the medial layer.
    private final int neuronCount;
    
    // The number of inputs to the network.
    private final int inputCount;
    
    // The number of outputs of the network.
    private final int outputCount;
    
    // Creates a new feed forward network with the given number of inputs,
    // outputs and medial neurons in the network.
    public Network(int inputs, int outputs, int neurons, int layers) {
        
        Random random = new Random();

        this.medout = new double[layers][];

        this.neuronCount = neurons;
        this.inputCount = inputs;
        this.outputCount = outputs;

        this.synOut = initialiseSynArray(neurons, outputs, random);
        this.layers = new MedialLayer[layers];

        for (int i = 0; i < layers; i++)
        {
            int layerInputs = neurons;
            if (i == 0)
            {
                layerInputs = inputs;
            }

            this.layers[i] = new MedialLayer();
            this.layers[i].setWeights(initialiseSynArray(layerInputs, neurons, random));
        }
    }

    // Resets the weights of the network.
    public void reset() {
        
        Random random = new Random();
        
        synOut = initialiseSynArray(neuronCount, outputCount, random);

        for (int i = 0; i < layers.length; i++)
        {
            int layerInputs = neuronCount;
            if (i == 0)
            {
                layerInputs = inputCount;
            }
            
            layers[i].setWeights(initialiseSynArray(layerInputs, neuronCount, random));
        }
    }

    // Calculates the output of the network with the given inputs.
    // Pre: The length of the input array matches the number of
    // input neurons.
    public double[] getOutput(double[] inputs)
    {
        //Debug.Assert(inputs.Length == inputCount, "Incorrect number of inputs.");

        // Calculate the values of the medial neurons.
        for (int layer = 0; layer < layers.length; layer++)
        {
            medout[layer] = layers[layer].getOutput(inputs);
            inputs = medout[layer];
        }

        // Calculate the values of the output neurons.
        double[] outputs = new double[outputCount];
        for (int output = 0; output < outputCount; output++)
        {
            outputs[output] = 0;

            for (int neuron = 0; neuron < neuronCount; neuron++)
            {
                outputs[output] += inputs[neuron] * synOut[neuron][output];
            }
        }

        return outputs;
    }

    // Helper method to set the synaptic weights between two neurons to a small
    // random value: [0, 0.1).
    private double[][] initialiseSynArray(int a, int b, Random random)
    {
        double[][] result = new double[a][b];

        for (int y = 0; y < b; y++)
        {
            for (int x = 0; x < a; x++)
            {
                result[x][y] = random.nextDouble() - 0.5;
            }
        }

        return result;
    }
    
    public MedialLayer[] getLayers() {
        
        return layers;
    }
    
    public double[][] getMedout() { 
        
        return medout;
    }
    
    public double[][] getSynOut() {
        
        return synOut;
    }
    
    public void setSynOut(double[][] synOut) {
        
        this.synOut = synOut;
    }
    
    public int getMedialNeurons() {
        
        return neuronCount;
    }
    
    public int getInputs() {
        
        return inputCount;
    }
    
    public int getOutputs() {
        
        return outputCount;
    }
}
