package backpropagation;

import java.applet.Applet;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Main extends Applet {
    
    public void init() {
        
        setSize(500, 500);
        try {
         
            SwingUtilities.invokeAndWait(new Runnable() {
                
                public void run() {
                    
                    createGUI();
                }
            });
        }
        
        catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
    }
    
    private void createGUI() {
        
        JPanel plMain = new JPanel();
        plMain.setLayout(new BoxLayout(plMain, BoxLayout.PAGE_AXIS));
        plMain.add(createSetupPanel());
        plMain.add(createGraphPanel());
        plMain.add(createTestPanel());
        
        add(plMain);
    }
    
    private JPanel createSetupPanel() {
        
        // Create the controls in the setup panel.
        JPanel plSetup = new JPanel();
        plSetup.setBorder(BorderFactory.createTitledBorder("Setup Network"));
        
        JLabel lblLayers = new JLabel("Number of layers:");
        JSpinner spnLayers = new JSpinner();
        
        JLabel lblNeurons = new JLabel("Number of neurons:");
        JSpinner spnNeurons = new JSpinner();
        
        JLabel lblRate = new JLabel("Learning rate:");
        JSpinner spnRate = new JSpinner();
        
        JButton btnApply = new JButton("Apply");
        JButton btnReset = new JButton("Reset");
        
        
        // Setup the group layout.
        GroupLayout layout = new GroupLayout(plSetup);
        plSetup.setLayout(layout);
        
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
        JSpinner spnIterations = new JSpinner();
        JButton btnTrain = new JButton("Train");
        
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
        JPanel plGraph = new JPanel();
        plGraph.setBorder(BorderFactory.createTitledBorder("Total Errors over Time"));
        plGraph.setBorder(BorderFactory.createTitledBorder("Test Network"));
        plGraph.setLayout(new BoxLayout(plGraph, BoxLayout.PAGE_AXIS));
        
        plGraph.add(plIterations);
        plGraph.add(plTrain);
        
        return plGraph;
    }
    
    private JPanel createTestPanel() {
        
        // Create the controls of the test panel.
        JLabel lblX = new JLabel("X:");
        JSpinner spnX = new JSpinner();
        
        JLabel lblY = new JLabel("Y:");
        JSpinner spnY = new JSpinner();
        
        JLabel lblR = new JLabel("r:");
        JSpinner spnR = new JSpinner();
        
        JLabel lblTheta = new JLabel("theta:");
        JSpinner spnTheta = new JSpinner();
        
        // Add the controls to the test panel and return.
        JPanel plTest = new JPanel();
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
}
