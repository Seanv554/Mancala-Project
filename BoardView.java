
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Author: Sean Vail
 * Class:
 * Section:
 * Assignment:
 * Date: Apr 27, 2014
 * 
 */
public class BoardView extends JPanel implements ChangeListener {

    private BoardController control;
    private BoardModel data;
    private BoardGraphics board;
    private final static int WIDTH = 800;
    private final static int HEIGHT = 300;

    public BoardView() {
        this(new RoundedPitBoard(650, 250));
    }

    public BoardView(BoardStyle style) {
        super();
        
        JFrame stylePrompt = new JFrame("Please choose your style");
        stylePrompt.setVisible(true);
        
        JPanel buttonPanel = new JPanel();
        board = new BoardGraphics(style);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());
        this.add(board, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.undoLastMove();
            }
        });
        
        buttonPanel.add(undoButton);
        
        
        data = new BoardModel(4);
        control = new BoardController(data);
        final Shape[] shapes = style.getShapes();
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                control.processClick(e, shapes);
            }

        });
        data.attach(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        super.repaint();
    }

    private class BoardGraphics extends JPanel {

        private BoardStyle style;

        public BoardGraphics() {
            this(new RoundedPitBoard(650, 250));
        }

        public BoardGraphics(BoardStyle style) {
            this.style = style;
        }
        
        public BoardStyle getStyle(){
            return style;
        }

        public void useStyle(BoardStyle style) {
            this.style = style;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            style.paintStyle(g);
            int[] score = data.getPits();
            style.arrangePitMarbles(g, score);
            int[] storeScores = {data.getStore(0), data.getStore(1)};
            style.arrangeStoreMarbles(g, storeScores);

        }
    }
}
