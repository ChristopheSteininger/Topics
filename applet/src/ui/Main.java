package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;

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
    
    // The spinner controls to adjust properties of the network.
    private JSpinner spnLayers = new JSpinner();
    private JSpinner spnNeurons = new JSpinner();
    private JSpinner spnRate = new JSpinner();
    
    // The buttons to apply or reset the properties of the network.
    private JButton btnReset = new JButton("Reset");
    private JButton btnApply = new JButton("Apply");

    // The controls used to train the network.
    private JSpinner spnIterations = new JSpinner();
    private JButton btnTrain = new JButton("Train");
    
    private Graph plGraph = new Graph();
    
    public void init() {
        
        // Setup the network and trainer.
        network = io.getValidNetwork();
        trainer = new Trainer(network, io);
        
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
        
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(new SetupPanel());
        container.add(createGraphPanel());
        container.add(createTestPanel());
    }
    
    private JPanel createSetupPanel() {
        
        // Create the controls in the setup panel.
        JPanel plSetup = new JPanel();
        plSetup.setBorder(BorderFactory.createTitledBorder("Setup Network"));
        
        JLabel lblLayers = new JLabel("Number of layers:");
        JLabel lblNeurons = new JLabel("Number of neurons:");
        JLabel lblRate = new JLabel("Learning rate:");
        
        // Attach a click handler to the apply button.
        btnApply.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                btnApplyHandler();
            }
        });
        
        // Setup the group layout.
        GroupLayout layout = new GroupLayout(plSetup);
        plSetup.setLayout(layout);
        //plSetup.setBackground(new Color(255, 0, 0));
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblLayers)
                        .addComponent(lblNeurons)
                        .addComponent(lblRate)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnReset)
                                .addComponent(btnApply)))
                .addGroup(layout.createParallelGroup()
                        .addComponent(spnLayers)
                        .addComponent(spnNeurons)
                        .addComponent(spnRate))
                .addGroup(layout.createParallelGroup()));
                
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblLayers)
                        .addComponent(spnLayers))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblNeurons)
                        .addComponent(spnNeurons))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblRate)
                        .addComponent(spnRate))
                .addGroup(layout.createParallelGroup()
                        .addComponent(btnReset)
                        .addComponent(btnApply)));
        
        return plSetup;
    }
    
    private JPanel createGraphPanel() {
        
        // Create the controls for the graph.
        JLabel lblIterations = new JLabel("Iterations:");
        
        btnTrain.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnTrainHandler();
            }
        });
        
        // Add the iteration controls a panel.
        JPanel plIterations = new JPanel();
        plIterations.setLayout(new BoxLayout(plIterations, BoxLayout.LINE_AXIS));
        plIterations.setAlignmentX(RIGHT_ALIGNMENT);

        plIterations.add(lblIterations);
        plIterations.add(Box.createRigidArea(new Dimension(10, 0)));
        plIterations.add(spnIterations);
        plIterations.add(Box.createHorizontalGlue());
        
        // Add the train button to a panel.
        JPanel plTrain = new JPanel();
        plTrain.setLayout(new BoxLayout(plTrain, BoxLayout.LINE_AXIS));
        plTrain.setAlignmentX(RIGHT_ALIGNMENT);
        
        plTrain.add(btnTrain);
        plTrain.add(Box.createHorizontalGlue());
        
        // Add the subpanels to the final panel and return.
        JPanel plTrainArea = new JPanel();
        plGraph.setBorder(BorderFactory.createTitledBorder("Total Errors over Time"));
        
        //plTrainArea.setBackground(new Color(0, 255, 0));
        plTrainArea.setBorder(BorderFactory.createTitledBorder("Train Network"));
        plTrainArea.setLayout(new BoxLayout(plTrainArea, BoxLayout.PAGE_AXIS));
        
        plTrainArea.add(plIterations);
        plTrainArea.add(plGraph);
        plTrainArea.add(plTrain);
        
        return plTrainArea;
    }
    
    private JPanel createTestPanel() {
        
        // Create the controls of the test panel.
        JLabel lblX = new JLabel("X:");
        JSpinner spnX = new JSpinner();
        
        JLabel lblY = new JLabel("Y:");
        JSpinner spnY = new JSpinner();
        
        JLabel lblR = new JLabel("r:");
        JLabel spnR = new JLabel("R");
        
        JLabel lblTheta = new JLabel("theta:");
        JLabel spnTheta = new JLabel("THETA");
        
        // Add the controls to the test panel and return.
        JPanel plTest = new JPanel();
        //plTest.setBackground(new Color(0, 0, 255));
        plTest.setBorder(BorderFactory.createTitledBorder("Test Network"));
        
        GroupLayout layout = new GroupLayout(plTest);
        plTest.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblX)
                        .addComponent(lblY))
                .addGroup(layout.createParallelGroup()
                        .addComponent(spnX)
                        .addComponent(spnY))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblR)
                        .addComponent(lblTheta))
                .addGroup(layout.createParallelGroup()
                        .addComponent(spnR)
                        .addComponent(spnTheta)));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblX)
                        .addComponent(spnX)
                        .addComponent(lblR)
                        .addComponent(spnR))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY)
                        .addComponent(spnY)
                        .addComponent(lblTheta)
                        .addComponent(spnTheta)));
        
        return plTest;
    }

    private void btnTrainHandler() {

        int iterations = (Integer)spnIterations.getValue();
        
        double[] errors = trainer.train(iterations);
        
        plGraph.clear();
        plGraph.add(errors);
        
        plGraph.redraw();
    }

    private void btnApplyHandler() {
        
        int layers = (Integer)spnLayers.getValue();
        int neurons = (Integer)spnNeurons.getValue();
        int rate = (Integer)spnRate.getValue();
        
        network = new Network(io.getInputs(), io.getOutputs(),
                neurons, layers);
        trainer.setLearningRate(rate / 10.0);
    }
}
