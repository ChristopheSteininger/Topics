package backpropagation;

// An interface to define the input and output functions which
// all trainer IOs must provide and the number of inputs and output to
// the network.
public interface TrainerIO {
    
    double getLearningRate();
    double getMomentumFactor();

    int getInputs();
    int getOutputs();
    int getMedialNeurons();
    int getLayers();

    double[] getExpectedOutput(double[] input);
    double[] getValidInput();

    Network getValidNetwork();
}
