package ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;

@SuppressWarnings("serial")
public class TestPanel extends JPanel {
    
    private JSpinner spnX = new JSpinner();
    private JSpinner spnY = new JSpinner();
    
    private JLabel lblR = new JLabel("r:");
    private JLabel lblTheta = new JLabel("theta:");

    public TestPanel(int maxSpinnerSizeX) {
        
        setBorder(BorderFactory.createTitledBorder("Test Network"));
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(createTest(maxSpinnerSizeX));
    }
    
    // Create the test panel.
    private JPanel createTest(int maxSpinnerSizeX) {

        JPanel result = new JPanel();
        
        JLabel lblX = new JLabel("x:");
        JLabel lblY = new JLabel("y:");
        
        GroupLayout layout = new GroupLayout(result);
        result.setLayout(layout);
        
        int preferredSize = GroupLayout.PREFERRED_SIZE;
        int spinnerSizeY = spnX.getPreferredSize().height;
        int minSizeX = spnX.getMinimumSize().width;
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblX)
                        .addComponent(lblY))
                .addGroup(layout.createParallelGroup()
                        .addComponent(spnX, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX)
                        .addComponent(spnY, minSizeX, maxSpinnerSizeX, maxSpinnerSizeX))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblR)
                        .addComponent(lblTheta))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblX)
                        .addComponent(spnX, preferredSize, spinnerSizeY, preferredSize)
                        .addComponent(lblR))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblY)
                        .addComponent(spnY, preferredSize, spinnerSizeY, preferredSize)
                        .addComponent(lblTheta)));
        
        return result;
    }
}
