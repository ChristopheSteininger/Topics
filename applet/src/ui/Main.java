package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backpropagation.Network;
import backpropagation.PolarToCartesianIO;
import backpropagation.Trainer;
import backpropagation.TrainerIO;

@SuppressWarnings("serial")
public class Main extends JApplet {
    
    // The network and trainers to use.
    private Network network;
    private Trainer trainer;
    private TrainerIO io = new PolarToCartesianIO();
    
    private final int maxSpinnerSizeX = 80;
    
    // The panels of the UI.
    private SetupPanel plSetup;
    private GraphPanel plGraph;
    private TestPanel plTest;
    
    public void init() {
        
        // Setup the network and trainer.
        network = io.getValidNetwork();
        trainer = new Trainer(network, io);
        
        setSize(670, 800);
        
        // Setup the GUI.
        try {
            
            SwingUtilities.invokeAndWait(new Runnable() {
                
                public void run() {
                    
                    createGUI(getContentPane());
                }
            });
        }
        
        catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
    }
    
    // Creates the GUI in the given container.
    private void createGUI(Container container) {
        
        plSetup = new SetupPanel(maxSpinnerSizeX, network.getLayers().length,
                network.getMedialNeurons(), trainer.getLearningRate());
        plGraph = new GraphPanel(maxSpinnerSizeX);
        plTest = new TestPanel(maxSpinnerSizeX);
        
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(plSetup);
        container.add(plGraph);
        container.add(plTest);
        
        attachHandlers();
    }
    
    private void attachHandlers() {
        
        // Add apply button handler.
        plSetup.getApplyButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                btnApplyHandler();
            }
        });
        
        // Add train button handler.
        plGraph.getTrainButton().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                btnTrainHandler();
            }
        });
        
        plGraph.getResetButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                btnResetHandler();
            }
        });
        
        plTest.getRSpinner().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                
                spnTestHandler();
            }
        });
        
        plTest.getThetaSpinner().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                
                spnTestHandler();
            }
        });
    }

    private void btnTrainHandler() {

        int iterations = plGraph.getIterations();
        
        double[] errors = trainer.train(iterations);
        
        plGraph.getGraph().clear();
        plGraph.getGraph().setData(errors);
        
        plGraph.getGraph().redraw();
    }

    private void btnResetHandler() {
        
        network.reset();
    }

    private void btnApplyHandler() {
        
        int layers = plSetup.getLayers();
        int neurons = plSetup.getNeurons();
        double rate = plSetup.getRate();
        
        plGraph.getGraph().clear();
        plGraph.getGraph().redraw();
        
        network = new Network(io.getInputs(), io.getOutputs(),
                neurons, layers);
        trainer.setLearningRate(rate);
    }

    private void spnTestHandler() {
        
        double r = (Double)plTest.getRSpinner().getValue();
        double theta = (Double)plTest.getThetaSpinner().getValue() * Math.PI / 180;
        
        double[] outputs = network.getOutput(new double[] { r, theta });
        
        plTest.setLabels(outputs[0], outputs[1]);
    }
}
