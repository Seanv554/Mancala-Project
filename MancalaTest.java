
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/* File: BoardView.java
 * Author: Sean Vail
 * Class: CS-151
 * Section: 02
 * Assignment: Mancala Group Project
 * Date: Apr 27, 2014
 */
public class MancalaTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final JFrame stylePrompt = new JFrame("Please choose your style");
        stylePrompt.setLayout(new FlowLayout());

        final JCheckBox roundedOption = new JCheckBox("Rounded pits and mancalas");
        final JCheckBox rectangularOption = new JCheckBox("Rectangular pits and mancalas");
        roundedOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangularOption.setSelected(false);
            }
        });
        rectangularOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundedOption.setSelected(false);
            }
        });

        final JCheckBox threeMarbles = new JCheckBox("3 Marbles");
        final JCheckBox fourMarbles = new JCheckBox("4 Marbles");
        fourMarbles.setSelected(true);
        roundedOption.setSelected(true);
        threeMarbles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fourMarbles.setSelected(false);
            }
        });

        fourMarbles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threeMarbles.setSelected(false);
            }
        });

        JButton startButton = new JButton("Start Game");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int marbles = 0; //number of marbles per pit
                if (fourMarbles.isSelected()) {
                    marbles = 4;
                } else if (threeMarbles.isSelected()) {
                    marbles = 3;
                }
                if (roundedOption.isSelected()) {
                    startGame(new RoundedPitBoard(), marbles);
                } else if (rectangularOption.isSelected()) {
                    startGame(new RectangularPitBoard(), marbles);
                }
                if ((rectangularOption.isSelected() || roundedOption.isSelected()) && (threeMarbles.isSelected() || fourMarbles.isSelected())) {
                    stylePrompt.dispose();
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stylePrompt.dispose();
            }
        });
        stylePrompt.add(rectangularOption);
        stylePrompt.add(roundedOption);
        stylePrompt.add(threeMarbles);
        stylePrompt.add(fourMarbles);
        stylePrompt.add(startButton);
        stylePrompt.add(exitButton);
        stylePrompt.pack();
        stylePrompt.setVisible(true);
        stylePrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static void startGame(BoardStyle style, int marbles) {
        JFrame f = new JFrame();

        BoardView v = new BoardView(style, marbles);
        v.setVisible(true);

        f.add(v);
        f.setPreferredSize(new Dimension(765, 350));
        f.setVisible(true);
        f.pack();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
