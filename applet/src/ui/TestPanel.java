package ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class TestPanel extends JPanel {
    
    private JSpinner spnR = new JSpinner();
    private JSpinner spnTheta = new JSpinner();
    
    private JLabel lblX = new JLabel();
    private JLabel lblY = new JLabel();
    
    private JLabel lblEX = new JLabel();
    private JLabel lblEY = new JLabel();

    public TestPanel(int maxSpinnerSizeX) {
        
        setBorder(BorderFactory.createTitledBorder("Test Network"));
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(createTest(maxSpinnerSizeX));
    }
    
    // Create the test panel.
    private JPanel createTest(int maxSpinnerSizeX) {

        JPanel result = new JPanel();
        
        JLabel lblR = new JLabel("r:");
        JLabel lblTheta = new JLabel("theta (degrees):");
        
        JLabel lblActual = new JLabel("Actual:");
        JLabel lblExpected = new JLabel("Expected:");
        
        spnR.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
        spnTheta.setModel(new SpinnerNumberModel(45.0, 0.0, 90.0, 0.5));
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        int preferredSize = GroupLayout.PREFERRED_SIZE;
        int spinnerSizeY = spnR.getPreferredSize().height;
        int minSizeX = spnR.getMinimumSize().width;
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblR)
                        .addComponent(lblTheta))
                .addGroup(layout.createParallelGroup()
                        .addComponent(spnR, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX)
                        .addComponent(spnTheta, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblActual)
                        .addComponent(lblExpected))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblX)
                        .addComponent(lblEX))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY)
                        .addComponent(lblEY))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblR)
                        .addComponent(spnR, preferredSize, spinnerSizeY, preferredSize)
                        .addComponent(lblActual)
                        .addComponent(lblX)
                        .addComponent(lblY))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblTheta)
                        .addComponent(spnTheta, preferredSize, spinnerSizeY, preferredSize)
                        .addComponent(lblExpected)
                        .addComponent(lblEX)
                        .addComponent(lblEY)));
        
        return result;
    }
    
    public JSpinner getRSpinner() {
        
        return spnR;
    }
    
    public JSpinner getThetaSpinner() {
        
        return spnTheta;
    }
    
    public void setLabels(Double x, Double y) {
        
        final double roundingFactor = 1000000.0;
        
        x = (double)Math.round(x * roundingFactor) / roundingFactor;
        y = (double)Math.round(y * roundingFactor) / roundingFactor;
        
        Double r = (Double)spnR.getValue();
        Double theta = (Double)spnTheta.getValue() * Math.PI / 180;
        
        Double eX = r * Math.cos(theta);
        Double eY = r * Math.sin(theta);
        
        eX = (double)Math.round(eX * roundingFactor) / roundingFactor;
        eY = (double)Math.round(eY * roundingFactor) / roundingFactor;

        lblX.setText("x = " + x.toString());
        lblY.setText("y = " + y.toString());
        lblEX.setText("x = " + eX.toString());
        lblEY.setText("y = " + eY.toString());
    }
}
