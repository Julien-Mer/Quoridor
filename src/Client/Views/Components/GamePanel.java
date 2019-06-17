package Client.Views.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GamePanel extends JPanel {
    private static final int CLUSTER = 3;
    private static final int MAX_ROWS = 9;
    private static final float FIELD_PTS = 32f;
    private static final int GAP = 3;
    private static final Color BG = Color.BLACK;
    private static final Color SOLVED_BG = Color.LIGHT_GRAY;
    public static final int TIMER_DELAY = 2 * 1000;
    private JTextField[][] fieldGrid = new JTextField[MAX_ROWS][MAX_ROWS];
    JPanel[][] panels;
    public GamePanel() {
    	int a=0;
        JPanel mainPanel = new JPanel(new GridLayout(CLUSTER, CLUSTER));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.setBackground(BG);
        int j=0;
        this.panels = new JPanel[CLUSTER][CLUSTER];
        for (int i = 0; i < panels.length; i++) {
            for (j = 0; j < panels[i].length; j++) {
            	panels[i][j] = new JPanel(new GridLayout(CLUSTER, CLUSTER, 1, 1));
                mainPanel.add(panels[i][j]);
            }
        }
        
        int m=0;
        for (int row = 0; row < fieldGrid.length; row++) {
            for (int col = 0; col < fieldGrid[row].length; col++) {
            	int i = row / 3;
                int k = col / 3;
                fieldGrid[row][col] = createField(row, col);
                panels[i][k].add(fieldGrid[row][col]);
                fieldGrid[row][col].setBorder(BorderFactory.createBevelBorder(0,Color.WHITE,Color.WHITE));
            }
        }

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    private JTextField createField(int row, int col) {
        JTextField field = new JTextField(2);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(field.getFont().deriveFont(Font.BOLD, FIELD_PTS));
        field.setEnabled(false);
        field.setBackground(new Color(139,69,19));
        return field;
    }
}
