
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

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
public class RoundedPitBoard implements BoardStyle {

    private int currentColor = 0;
    private int width, height;
    private int marbleSize = 15;

    public RoundedPitBoard() {
        width = 750;
        height = 250;
    }

    public RoundedPitBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintStyle(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(30, 30, width / 8, height - (height / 8), 20, 20);
        for (int i = 0; i < 6; i++) {
            String label = "B" + (i + 1);
            g2.setFont(new Font("Arial Bold", Font.BOLD, 20));
            g2.drawString(label, (int) 
                    (width * 0.089) + (width / 8) + (pitSpacing * i), 
                    (int) (height * 0.08));
            Ellipse2D.Double currentPit = new Ellipse2D.Double(
                    (width * 0.053) + (width / 8) + (pitSpacing * i), 
                    (int) (height * 0.12), pitWidth, pitHeight);
            shapes[i + 6] = currentPit;
            g2.draw(currentPit);
        }
        g2.drawRoundRect((int) 
                (width * 0.053) + (width / 8) + (pitSpacing * 6), 
                (int) (height * 0.12), 
                width / 8, height - (height / 8), 20, 20);
        for (int i = 0; i < 6; i++) {
            Ellipse2D.Double currentPit = new Ellipse2D.Double(
                    (width * 0.053) + (width / 8) + (pitSpacing * i), 
                    (height * 0.64), pitWidth, pitHeight);
            String label = "A" + (i + 1);
            g2.drawString(label, (int) 
                    (width * 0.089) + (width / 8) + (pitSpacing * i), 
                    (int) (height * 1.04));
            shapes[i] = currentPit;
            g2.draw(currentPit);
        }
    }

    @Override
    public void arrangePitMarbles(Graphics g, int[] scores) {
        for (int i = 0; i < 12; i++) {
            Rectangle2D r = shapes[i].getBounds2D();
            int x = (int) r.getX();
            int y = (int) r.getY();
            arrangePitMarblesHelper(g, scores[i], r);
        }
    }

    @Override
    public void arrangePitMarblesHelper(Graphics g, int n, Rectangle2D r) {
        int x = (int) r.getX() + (int) (width * 0.002);
        int y = (int) r.getY() - (int) (height * 0.005);
        if (n == 0) {
            return;
        } else if (n == 1) {
            drawMarble(x, y, g);
            return;
        }
        double xInterval = r.getWidth() / 4;
        double yInterval = r.getHeight() / 4;
        Rectangle2D.Double subRec;
        for (int i = 0; i < 12 && n != 0; i++) {
            if (i < 2) {
                subRec = new Rectangle2D.Double(x + xInterval * (i + 1),
                        y + (yInterval * 0.25), xInterval, yInterval);
                arrangePitMarblesHelper(g, 1, subRec);
                n--;
            } else if (i >= 2 && i < 6) {
                subRec = new Rectangle2D.Double(x + xInterval * (i - 2),
                        y + (yInterval * 1.3), xInterval, yInterval);
                arrangePitMarblesHelper(g, 1, subRec);
                n--;
            } else if (i >= 6 && i < 10) {
                subRec = new Rectangle2D.Double(x + xInterval * (i - 6),
                        y + (yInterval * 2.3), xInterval, yInterval);
                arrangePitMarblesHelper(g, 1, subRec);
                n--;
            } else if (i >= 10 && i < 12) {
                subRec = new Rectangle2D.Double(x + xInterval * (i - 10 + 1),
                        y + (yInterval * 3.3), xInterval, yInterval);
                arrangePitMarblesHelper(g, 1, subRec);
                n--;
            }
        }

    }

    @Override
    public void drawMarble(int x, int y, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.black);
        g2.drawOval(x, y, marbleSize, marbleSize);
        int colorInt = currentColor++;
        if (currentColor == 8) {
            currentColor = 0;
        }
        g2.setColor(marbleColors[colorInt]);
        g2.fillOval(x, y, marbleSize, marbleSize);
    }

    @Override
    public Shape[] getShapes() {
        return shapes;
    }
}
