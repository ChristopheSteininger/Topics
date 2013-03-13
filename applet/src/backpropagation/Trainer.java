package backpropagation;

public class Trainer {
    
    // The network to train.
    private final Network network;
    
    // The input/output to train the network against.
    private final TrainerIO io;
    
    // The learning rate used when training.
    private double learningRate;
    
    // The momentum factor used when training.
    private double momentum;

    // Creates a new instance of trainer with the network to train and
    // the input/output to train for.
    public Trainer(Network network, TrainerIO io) {
        
        this.network = network;
        this.io = io;
        
        learningRate = io.getLearningRate();
        momentum = io.getMomentumFactor();
    }

    // Trains the network, and writes to the log file if specified.
    public double[] train(int iterations) {
        
        System.out.println("Training with " + iterations + " iterations and "
                + "rate of " + learningRate);
        
        double[] allErrors = new double[iterations];
        double[] inputs, expected, actual;

        // Train and test the network for the given number of times.
        for (int i = 0; i < iterations; i++) {
            
            // Get the input and expected output from the IO.
            inputs = io.getValidInput();
            expected = io.getExpectedOutput(inputs);

            // Get the actual output of the network.
            actual = network.getOutput(inputs);

            // Apply the back propagation algorithm.
            trainTestPass(inputs, expected, actual);

            // Calculate the total error as summation in quadrature.
            allErrors[i] = getTotalError(expected, actual);
        }

        return allErrors;
    }

    // Calculates the total error of the output given the expected result.
    private double getTotalError(double[] expected, double[] actual) {

        double sum = 0;
        for (int i = 0; i < expected.length; i++) {
            
            sum += Math.pow(expected[i] - actual[i], 2);
        }

        return Math.sqrt(sum);
    }

    // Applies an iteration of the back propagation algorithm to the network.
    private void trainTestPass(double[] inputs, double[] expected, double[] actual) {

        // Calculate the error.
        double[] errors = new double[actual.length];
        for (int error = 0; error < actual.length; error++) {
            
            errors[error] = expected[error] - actual[error];
        }

        // Calculate the delta values for the medial layer.
        double[][] deltas = new double[network.getLayerCount()][];
        double[][] weights = network.getOutputLayer().getWeights();
        double[] neuronOutputs = errors;
        for (int layer = network.getLayerCount() - 1; layer >= 0; layer--) {
            
            deltas[layer] = getDeltaValues(weights, neuronOutputs);

            weights = network.getLayer(layer).getWeights();
            neuronOutputs = deltas[layer];
        }

        // Adjust weights.
        double[] currentInputs = inputs;
        for (int layer = 0; layer < network.getLayerCount(); layer++) {
            
            for (int input = 0; input < currentInputs.length; input++) {
                
                for (int neuron = 0; neuron < network.getMedialNeurons(); neuron++) {
                    
                    double weightUpdate = currentInputs[input] * deltas[layer][neuron]
                            * dLogistic(network.getLayer(layer).getLastOutputs()[neuron]);
                    
                    double adjustment = learningRate * weightUpdate + momentum
                            * network.getLayer(layer).getLastAdjustment(input, neuron);
                    
                    network.getLayer(layer).adjustWeight(input, neuron, adjustment);
                }
            }

            currentInputs = network.getLayer(layer).getLastOutputs();
        }

        // Adjust weights of the last layer.
        for (int neuron = 0; neuron < currentInputs.length; neuron++) {
            
            for (int output = 0; output < network.getOutputs(); output++) {
                
                double weightUpdate = currentInputs[neuron] * errors[output];
                
                double adjustment = learningRate * weightUpdate + momentum
                        * network.getOutputLayer().getLastAdjustment(neuron, output);
                
                network.getOutputLayer().adjustWeight(neuron, output, adjustment);
            }
        }

        return;
    }

    // Calculates the delta values of a set of neurons using the synaptic weights
    // and the output of the neurons at the end of the synapses.
    private double[] getDeltaValues(double[][] weights, double[] neuronOutputs) {
        assert weights[0].length == neuronOutputs.length :
            "Number of output neurons in weights array not equal to number of outputs.";

        double[] delta = new double[weights.length];

        for (int input = 0; input < delta.length; input++) {
            
            double sum = 0;
            for (int output = 0; output < neuronOutputs.length; output++) {
                
                sum += weights[input][output] * neuronOutputs[output];
            }

            delta[input] = sum;
        }

        return delta;
    }

    // Returns the value of the derivative of the logistic function with
    // using the result of the logistic function y where y = 1 / (1 + exp(-x)).
    private double dLogistic(double y) {
        
        return y * (1 - y);
    }
    
    // Gets the learning rate to use when training.
    public double getLearningRate() {
        
        return learningRate;
    }
    
    // Sets the learning rate when training.
    public void setLearningRate(double learningRate) {
        
        this.learningRate = learningRate;
    }
    
    public double getMomentum() {
        
        return momentum;
    }

    public void setMomentum(double momentum) {
        
        this.momentum = momentum;
    }
}
