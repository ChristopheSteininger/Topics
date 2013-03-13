package backpropagation;

import java.util.Random;

// Simulates a simple feed forward network with one hidden (medial) layer
// containing a variable number of neurons, as well as a variable number
// of input and output neurons. The network can also calculate the output
// given its inputs.
public class Network {
    
    // The medial layers in the network.
    private NeuronLayer[] layers;
    
    // The weights leading to the output layer.
    private NeuronLayer outputLayer;
    
    // The number of neurons in the medial layer.
    private final int neuronCount;
    
    // The number of inputs to the network.
    private final int inputCount;
    
    // The number of outputs of the network.
    private final int outputCount;
    
    // Creates a new feed forward network with the given number of inputs,
    // outputs and medial neurons in the network.
    public Network(int inputs, int outputs, int neurons, int layers) {
        
        neuronCount = neurons;
        inputCount = inputs;
        outputCount = outputs;

        this.layers = new NeuronLayer[layers];
        
        reset();
    }

    // Resets the weights of the network.
    public void reset() {
        
        Random random = new Random();
        
        for (int i = 0; i < layers.length; i++)
        {
            int layerInputs = neuronCount;
            if (i == 0)
            {
                layerInputs = inputCount;
            }
            
            layers[i] = new NeuronLayer(layerInputs, neuronCount, random);
        }
        
        outputLayer = new NeuronLayer(neuronCount, outputCount, random);
    }

    // Calculates the output of the network with the given inputs.
    // Pre: The length of the input array matches the number of
    // input neurons.
    public double[] getOutput(double[] inputs) {
        
        for (int layer = 0; layer < layers.length; layer++)
        {
            inputs = layers[layer].getOutput(inputs, true);
        }
        
        return outputLayer.getOutput(inputs, false);
    }
    
    public NeuronLayer getLayer(int layer) {
        
        return layers[layer];
    }
    
    public int getLayerCount() {
        
        return layers.length;
    }
    
    public NeuronLayer getOutputLayer() {
        
        return outputLayer;
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
