package ui;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class SetupPanel extends JPanel {
    
    private JLabel lblLayers = new JLabel("Number of hidden layers:");
    private JSpinner spnLayers = new JSpinner();
    private JSpinner spnNeurons = new JSpinner();
    private JSpinner spnRate = new JSpinner();
    
    public SetupPanel() {
        
        setBorder(BorderFactory.createTitledBorder("Setup Network"));
        
        GroupLayout layout = new GroupLayout(this);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblLayers))
                .addGroup(layout.createParallelGroup()
                        .addComponent(spnLayers)
                        .addComponent(spnNeurons)
                        .addComponent(spnRate)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblLayers)
                        .addComponent(spnLayers))
                .addGroup(layout.createParallelGroup()
                    //    .addComponent(lblLayers)
                        .addComponent(spnNeurons))
                .addGroup(layout.createParallelGroup()
                     //   .addComponent(lblLayers)
                        .addComponent(spnRate)));
    }
}
