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
public class GraphPanel extends JPanel {
    
    private JSpinner spnIterations = new JSpinner();
    private JSpinner spnRate = new JSpinner();
    private JSpinner spnMomentum = new JSpinner();
    
    private JButton btnTrain = new JButton("Train");
    private JButton btnReset = new JButton("Reset Weights");
    
    private Graph graph = new Graph();
    
    public GraphPanel(int maxSpinnerSizeX, double rate, double momentum) {
        
        setBorder(BorderFactory.createTitledBorder("Train Network"));
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(createIterations(maxSpinnerSizeX, rate, momentum));
        add(createGraphPanel());
        add(createTrainResetButton());
    }
    
    // Create the iterations label and spinner.
    private JPanel createIterations(int maxSpinnerSizeX, double rate, double momentum) {
        
        JPanel result = new JPanel();
        
        JLabel lblIterations = new JLabel("Iterations:");
        JLabel lblRate = new JLabel("Learning rate:");
        JLabel lblMomentum = new JLabel("Momentum");
        
        spnRate.setModel(new SpinnerNumberModel(rate, 0.0, 10.0, 0.01));
        spnMomentum.setModel(new SpinnerNumberModel(momentum, 0.0, 1.0, 0.05));
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        int preferredSize = GroupLayout.PREFERRED_SIZE;
        int spinnerSizeY = spnIterations.getPreferredSize().height;
        int minSizeX = spnIterations.getMinimumSize().width;
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(lblIterations)
                    .addComponent(lblRate)
                    .addComponent(lblMomentum))
                .addGroup(layout.createParallelGroup()
                    .addComponent(spnIterations, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX)
                    .addComponent(spnRate, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX)
                    .addComponent(spnMomentum, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(lblIterations)
                    .addComponent(spnIterations, preferredSize, spinnerSizeY, preferredSize))
                .addGroup(layout.createParallelGroup()
                    .addComponent(lblRate)
                    .addComponent(spnRate, preferredSize, spinnerSizeY, preferredSize))
                .addGroup(layout.createParallelGroup()
                    .addComponent(lblMomentum)
                    .addComponent(spnMomentum, preferredSize, spinnerSizeY, preferredSize)));
        
        return result;
    }
    
    // Create the graph area.
    private JPanel createGraphPanel() {
        
        JPanel result = new JPanel();
        
        result.setBorder(BorderFactory.createTitledBorder("Total Error against Iterations"));
        result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));
        result.add(graph);
        
        return result;
    }
    
    // Create the button area
    private JPanel createTrainResetButton() {

        JPanel result = new JPanel();
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(btnTrain)
                .addComponent(btnReset)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
        
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(btnTrain)
                .addComponent(btnReset));
        
        return result;
    }
    
    public int getIterations() {
        
        return (Integer)spnIterations.getValue();
    }
    
    public double getRate() {
        
        return (Double)spnRate.getValue();
    }
    
    public void setRate(double rate) {
        
        spnRate.setValue(rate);
    }
    
    public double getMomentum() {
        
        return (Double)spnMomentum.getValue();
    }
    
    public void setMomentum(double rate) {
        
        spnMomentum.setValue(rate);
    }
    
    public Graph getGraph() {
        
        return graph;
    }
    
    public JButton getTrainButton() {
        
        return btnTrain;
    }
    
    public JButton getResetButton() {
        
        return btnReset;
    }
}
