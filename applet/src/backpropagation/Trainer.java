package backpropagation;

public class Trainer {

    // The filename to write the log to.
    private final String logFile = "training.log";

    // The network to train.
    private final Network network;
    
    // The input/output to train the network against.
    private final TrainerIO io;
    
    // The learning rate used when training.
    private double learningRate;

    // Creates a new instance of trainer with the network to train and
    // the input/output to train for.
    public Trainer(Network network, TrainerIO io)
    {
        this.network = network;
        this.io = io;
        learningRate = io.getLearningRate();
    }

    // Trains the network, and writes to the log file if specified.
    public double[] Train(int iterations, /*BackgroundWorker worker,*/ boolean printData)
    {
        //StreamWriter writer = null;
        double totalErrorSum = 0;
        final int averageSize = 20;

        // Open the log file if the method should write to it.
        if (printData)
        {
            //writer = new StreamWriter(logFile);
            //writer.WriteLine("Test\tAverage Error\t{0} layer(s) with {1} neuron(s) per "
            //    + "layer. Learning rate = {2}", io.Layers, io.MedialNeurons, learningRate);
        }

        double[] allErrors = new double[iterations];
        double[] inputs, expected, actual;

        // Train and test the network for the given number of times.
        for (int i = 0; i < iterations; i++)
        {
            // Get the input and expected output from the IO.
            inputs = io.GetValidInput();
            expected = io.GetExpectedOutput(inputs);

            // Get the actual output of the network.
            actual = network.GetOutput(inputs);

            // Apply the back propagation algorithm.
            TrainTestPass(inputs, expected, actual);

            // Calculate the total error as summation in quadrature.
            allErrors[i] = GetTotalError(expected, actual);

            // Report progress every 100 iterations to the worker.
            if (i % 100 == 0)
            {
            //    worker.ReportProgress(i);
            }

            // Print the average total error of every 20th
            // iteration to the log file.
            if (printData)
            {
                totalErrorSum += allErrors[i];

                if (i % averageSize == 0)
                {
                    //writer.WriteLine("{0}\t{1}", i,
                    //    totalErrorSum / averageSize);

                    totalErrorSum = 0;
                }
            }
        }

        // Close the writer if the log file was updated.
        if (printData)
        {
            //writer.Close();
        }

        // Report finished to worker.
        //worker.ReportProgress(iterations);

        return allErrors;
    }

    // Calculates the total error of the output given the expected result.
    private double GetTotalError(double[] expected, double[] actual)
    {
        //Debug.Assert(expected.Length == actual.Length,
        //    "Parameter lengths must be equal.");

        double sum = 0;
        for (int i = 0; i < expected.length; i++)
        {
            sum += Math.pow(expected[i] - actual[i], 2);
        }

        return Math.sqrt(sum);
    }

    // Applies an iteration of the back propagation algorithm to the network.
    private void TrainTestPass(double[] inputs, double[] expected, double[] actual)
    {
        //Debug.Assert(expected.Length == actual.Length,
        //    "Expected and actual output lengths must be equal.");

        // Calculate the error.
        double[] errors = new double[actual.length];
        for (int error = 0; error < actual.length; error++)
        {
            errors[error] = expected[error] - actual[error];
        }

        // Calculate the delta values for the medial layer.
        double[][] deltas = new double[network.getLayers().length][];
        double[][] weights = network.getSynOut();
        double[] neuronOutputs = errors;
        for (int layer = network.getLayers().length - 1; layer >= 0; layer--)
        {
            deltas[layer] = GetDeltaValues(weights, neuronOutputs);

            weights = network.getLayers()[layer].getWeights();
            neuronOutputs = deltas[layer];
        }

        // Adjust weights.
        double[] currentInputs = inputs;
        for (int layer = 0; layer < network.getLayers().length; layer++)
        {
            for (int input = 0; input < currentInputs.length; input++)
            {
                for (int neuron = 0; neuron < network.getMedialNeurons(); neuron++)
                {
                    network.getLayers()[layer].getWeights()[input][neuron] += learningRate
                        * currentInputs[input] * deltas[layer][neuron]
                        * dLogistic(network.getMedout()[layer][neuron]);
                }
            }

            currentInputs = network.getMedout()[layer];
        }

        // Adjust weights of the last layer.
        for (int neuron = 0; neuron < network.getMedialNeurons(); neuron++)
        {
            for (int output = 0; output < network.getOutputs(); output++)
            {
                network.getSynOut()[neuron][output] += learningRate * currentInputs[neuron]
                    * errors[output];
            }
        }

        return;
    }

    // Calculates the delta values of a set of neurons using the synaptic weights
    // and the output of the neurons at the end of the synapses.
    private double[] GetDeltaValues(double[][] weights, double[] neuronOutputs)
    {
        //Debug.Assert(weights.GetLength(1) == neuronOutputs.Length,
        //    "Number of output neurons in weights array not equal to number of outputs.");

        double[] delta = new double[weights.length];

        for (int input = 0; input < delta.length; input++)
        {
            double sum = 0;
            for (int output = 0; output < neuronOutputs.length; output++)
            {
                sum += weights[input][output] * neuronOutputs[output];
            }

            delta[input] = sum;
        }

        return delta;
    }

    // Returns the value of the derivative of the logistic function with
    // using the result of the logistic function y where y = 1 / (1 + exp(-x)).
    private double dLogistic(double y)
    {
        return y * (1 - y);
    }

    public double getLearningRate() {
        
        return learningRate;
    }
    
    public void setLearningRate(double learningRate) {
        
        this.learningRate = learningRate;
    }
}
