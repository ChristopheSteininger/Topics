package ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class SetupPanel extends JPanel {
    
    private JSpinner spnLayers = new JSpinner();
    private JSpinner spnNeurons = new JSpinner();
    
    private JButton btnCancel = new JButton("Cancel");
    private JButton btnApply = new JButton("Apply");
    
    public SetupPanel(int maxSpinnerSizeX, int layers, int neurons) {

        setBorder(BorderFactory.createTitledBorder("Setup Network"));
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(createSpinners(maxSpinnerSizeX, layers, neurons));
        add(createButtons());
    }
    
    // Create the labels and spinners to get the layers, neurons and
    // learning rate.
    private JPanel createSpinners(int maxSpinnerSizeX, int layers, int neurons) {
        
        JPanel result = new JPanel();
        
        JLabel lblLayers = new JLabel("Number of hidden layers:");
        JLabel lblNeurons = new JLabel("Neurons per hidden layer:");
        
        spnLayers.setModel(new SpinnerNumberModel(layers, 0, 10, 1));
        spnNeurons.setModel(new SpinnerNumberModel(neurons, 0, 200, 1));
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        int preferredSize = GroupLayout.PREFERRED_SIZE;
        int spinnerSizeY = spnLayers.getPreferredSize().height;
        int minSizeX = spnLayers.getMinimumSize().width;
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblLayers)
                        .addComponent(lblNeurons))
                .addGroup(layout.createParallelGroup()
                        .addComponent(spnLayers, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX)
                        .addComponent(spnNeurons, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblLayers)
                        .addComponent(spnLayers, preferredSize, spinnerSizeY, preferredSize))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblNeurons)
                        .addComponent(spnNeurons, preferredSize, spinnerSizeY, preferredSize)));
        
        return result;
    }
    
    // Create the apply and cancel buttons.
    private JPanel createButtons() {
        
        JPanel result = new JPanel();
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(btnCancel)
                .addComponent(btnApply)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
        
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(btnCancel)
                .addComponent(btnApply));
        
        return result;
    }
    
    public int getLayers() {
        
        return (Integer)spnLayers.getValue();
    }
    
    public void setLayers(int layers) {
        
        spnLayers.setValue(layers);
    }
    
    public int getNeurons() {
        
        return (Integer)spnNeurons.getValue();
    }
    
    public void setNeurons(int neurons) {
        
        spnNeurons.setValue(neurons);
    }
    
    public JButton getApplyButton() {
        
        return btnApply;
    }
    
    public JButton getCancelButton() {
        
        return btnCancel;
    }
}
