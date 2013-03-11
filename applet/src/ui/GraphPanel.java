package ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
    
    private JSpinner spnIterations = new JSpinner();
    private JButton btnTrain = new JButton("Train");
    
    private Graph graph = new Graph();
    
    public GraphPanel(int maxSpinnerSizeX) {
        
        setBorder(BorderFactory.createTitledBorder("Train Network"));
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(createIterations(maxSpinnerSizeX));
        add(graph);
        add(createTrainButton());
    }
    
    // Create the iterations label and spinner.
    private JPanel createIterations(int maxSpinnerSizeX) {
        
        JPanel result = new JPanel();
        
        JLabel lblIterations = new JLabel("Iterations");
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        int preferredSize = GroupLayout.PREFERRED_SIZE;
        int spinnerSizeY = spnIterations.getPreferredSize().height;
        int minSizeX = spnIterations.getMinimumSize().width;
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(lblIterations)
                .addComponent(spnIterations, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
        
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(lblIterations)
                .addComponent(spnIterations, preferredSize, spinnerSizeY, preferredSize));
        
        return result;
    }
    
    // Create the train button.
    private JPanel createTrainButton() {

        JPanel result = new JPanel();
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(btnTrain)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
        
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(btnTrain));
        
        return result;
    }
    
    public int getIterations() {
        
        return (Integer)spnIterations.getValue();
    }
    
    public Graph getGraph() {
        
        return graph;
    }
    
    public JButton getTrainButton() {
        
        return btnTrain;
    }
}
