
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
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

    private BoardStyle style;
    private BoardController control;
    private BoardModel data;
    private final static int WIDTH = 600;
    private final static int HEIGHT = 300;

    public BoardView() {
        this(new RoundedPitBoard(750, 250));
    }

    public BoardView(BoardStyle style) {
        super();
        //JFrame stylePrompt = new JFrame("Please choose your style");
        //stylePrompt.setVisible(true);
        useStyle(style);
        data = new BoardModel();
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

    public void useStyle(BoardStyle style) {
        this.style = style;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        style.paintStyle(g);
        int[] score = data.getPits();
        style.arrangeMarbles(g, score);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
