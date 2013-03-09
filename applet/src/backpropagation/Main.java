package backpropagation;

import java.applet.Applet;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Main extends Applet {
    
    public void init() {
        
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
        
        // Create the controls in the setup panel.
        JPanel setupPanel = new JPanel();
        
        JLabel lblLayers = new JLabel("Number of layers:");
        JSpinner spnLayers = new JSpinner();
        
        JLabel lblNeurons = new JLabel("Number of neurons:");
        JSpinner spnNeurons = new JSpinner();
        
        JLabel lblRate = new JLabel("Learning rate:");
        JSpinner spnRate = new JSpinner();
        
        
        // Setup the group layout.
        GroupLayout layout = new GroupLayout(setupPanel);
        setupPanel.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                     .addComponent(lblLayers)
                     .addComponent(spnLayers))
                .addGroup(layout.createParallelGroup()
                     .addComponent(lblNeurons)
                     .addComponent(spnNeurons))
                .addGroup(layout.createParallelGroup()
                     .addComponent(lblRate)
                     .addComponent(spnRate)));
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                      .addComponent(lblLayers)
                      .addComponent(lblNeurons)
                      .addComponent(lblRate))
                .addGroup(layout.createParallelGroup()
                      .addComponent(spnLayers)
                      .addComponent(spnNeurons)
                      .addComponent(spnRate)));
        
        add(setupPanel);
    }
}
