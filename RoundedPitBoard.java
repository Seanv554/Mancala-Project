
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/*
 * Author: Sean Vail
 * Class:
 * Section:
 * Assignment:
 * Date: Apr 27, 2014
 * 
 */
/**
 * @author Nick Saric
 * @author Sean Vail
 * @author Alvin Ko
 */
public class RoundedPitBoard implements BoardStyle {

    private final Shape[] pitShapes; //0-5 Player A, 6-11 Player B
    private final Shape[] storeShapes;
    private static final Color[] marbleColors = {Color.RED, Color.CYAN,
        Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.GREEN, Color.PINK, Color.YELLOW};
    private int currentColor = 0;
    private int width, height;
    private int marbleSize = 15;

    /**
     * Constructs Default Rounded Pit Board
     */
    public RoundedPitBoard() {
        this.pitShapes = new Shape[12];
        this.storeShapes = new Shape[2];
        width = 650;
        height = 250;
    }

    /**
     * Constructs Rounded Pit Board with Specified width and height
     *
     * @param width - Width to be used
     * @param height - Height to be used
     */
    public RoundedPitBoard(int width, int height) {
        this.pitShapes = new Shape[12];
        this.storeShapes = new Shape[2];
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintStyle(Graphics g, int[] pitScores, int[] storeScores) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        //Create store B
        RoundRectangle2D.Double currentStore;
        currentStore = new RoundRectangle2D.Double(30, 30, width / 8 - 2,
                height - (height / 8), 20, 20);
        storeShapes[1] = currentStore;
        g2.draw(currentStore);

        //Draw Mancala B label
        String mLabel = "MANCALA B";
        g2.setFont(new Font("Arial Bold", Font.BOLD, 20));
        for (int i = 0; i < mLabel.length(); i++) {
            g2.drawString(Character.toString(mLabel.charAt(i)),
                    (float) (width * 0.0076), (float) (height * 0.24) + (20 * i));
        }

        //Draw Mancala B score label;
        g2.drawRoundRect((int) (width * 0.0076) - 2,
                (int) ((height * 0.24) + (height * 0.768)) + 3, 40, 20, 5, 5);
        g2.setColor(Color.white);
        g2.fillRoundRect((int) (width * 0.0076) - 2,
                (int) ((height * 0.24) + (height * 0.768)) + 3, 40, 20, 5, 5);
        g2.setColor(Color.black);
        String storeBScore = Integer.toString(storeScores[1]);
        g2.drawString(storeBScore, (float) (width * 0.0076),
                (float) ((height * 0.24) + (height * 0.84) + 3));


        //Create B pits
        for (int i = 0; i < 6; i++) {
            //Pit Label
            String label = "B" + (6 - i);
            g2.setFont(new Font("Arial Bold", Font.BOLD, 20));
            g2.drawString(label, (int) (width * 0.089) + (width / 8) + (pitSpacing * i),
                    (int) (height * 0.08));
            //Pit
            Ellipse2D.Double currentPit = new Ellipse2D.Double(
                    (int) (width * 0.053) + (width / 8) + (pitSpacing * i),
                    (int) (height * 0.12), pitWidth, pitHeight);

            //Pit Score
            g2.drawString(Integer.toString(pitScores[11 - i]),
                    (int) (width * 0.053) + (width / 8) + (pitSpacing * i),
                    (int) (height * 0.47) + 8);

            pitShapes[11 - i] = currentPit;
            g2.draw(currentPit);
        }
        //Create store A
        currentStore = new RoundRectangle2D.Double(
                (width * 0.053) + (width / 8) + (pitSpacing * 6) - 4, (height * 0.12),
                width / 8, height - (height / 8), 20, 20);
        storeShapes[0] = currentStore;
        g2.draw(currentStore);

        //Draw Mancala A label
        mLabel = "MANCALA A";
        g2.setFont(new Font("Arial Bold", Font.BOLD, 20));
        for (int i = 0; i < mLabel.length(); i++) {
            g2.drawString(Character.toString(mLabel.charAt(i)),
                    (float) (width * 1.107) - 10, (float) (height * 0.24) + (20 * i));
        }

        //Draw Mancala A score label;
        g2.drawRoundRect((int) (width * 1.107) - 12,
                (int) ((height * 0.24) + (height * 0.768)) + 3, 40, 20, 5, 5);
        g2.setColor(Color.white);
        g2.fillRoundRect((int) (width * 1.107) - 12,
                (int) ((height * 0.24) + (height * 0.768)) + 3, 40, 20, 5, 5);
        g2.setColor(Color.black);
        String storeAScore = Integer.toString(storeScores[0]);
        g2.drawString(storeAScore, (float) (width * 1.107) - 12,
                (float) ((height * 0.24) + (height * 0.84) + 3));


        //Create A pits
        for (int i = 0; i < 6; i++) {
            //Pit Label
            String label = "A" + (i + 1);
            g2.drawString(label, (int) (width * 0.089) + (width / 8) + (pitSpacing * i),
                    (int) (height * 1.04) + 14);
            //Pit
            Ellipse2D.Double currentPit = new Ellipse2D.Double(
                    (width * 0.053) + (width / 8) + (pitSpacing * i),
                    (height * 0.64) + 14, pitWidth, pitHeight);

            //Pit Scores
            g2.drawString(Integer.toString(pitScores[i]),
                    (int) (width * 0.053) + (width / 8) + (pitSpacing * i),
                    (int) (height * 0.64) + 10);

            pitShapes[i] = currentPit;
            g2.draw(currentPit);
        }

    }

    @Override
    public void arrangePitMarbles(Graphics g, int[] scores) {

        for (int i = 0; i < 12; i++) {
            Rectangle2D r = pitShapes[i].getBounds2D();
            if (scores[i] == 1) {
                drawMarble((int) r.getX() + 32, (int) r.getY() + 32, g);
            } else {
                arrangePitMarblesHelper(g, scores[i], r);
            }
        }
    }

    /**
     * Recurssive Helper method to help arrange marbles within the bounds of a
     * specific pit
     *
     * @param g - Graphics Object
     * @param n - Score of specific pit
     * @param r - Bounding Rectangle of pit
     */
    private void arrangePitMarblesHelper(Graphics g, int n, Rectangle2D r) {
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

    /**
     * Draws an individual marble with the next color to be used
     *
     * @param x - X position of marble
     * @param y - Y position of marble
     * @param g - Graphics Object
     */
    private void drawMarble(int x, int y, Graphics g) {
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
        return pitShapes;
    }

    @Override
    public void arrangeStoreMarbles(Graphics g, int[] scores) {
        for (int i = 0; i < 2; i++) {
            Rectangle2D r = storeShapes[i].getBounds2D();
            int x = (int) r.getX();
            int y = (int) r.getY();
            arrangeStoreMarblesHelper(g, scores[i], r);
        }
    }

    /**
     * Recurssive Helper method to help arrange marbles within the bounds of a
     * specific Mancala
     *
     * @param g - Graphics Object
     * @param n - Score of specific Mancala
     * @param r - Bounding Rectangle of Mancala
     */
    private void arrangeStoreMarblesHelper(Graphics g, int n, Rectangle2D r) {
        int x = (int) r.getX();
        int y = (int) r.getY();
        if (n == 0) {
            return;
        } else if (n == 1) {
            drawMarble(x, y, g);
            return;
        }
        double xInterval = r.getWidth() / 4;
        double yInterval = r.getHeight() / 14;
        Rectangle2D.Double subRec;
        for (int i = 0; i < 14 && n != 0; i++) {
            for (int j = 0; j < 4 && n != 0; j++) {
                subRec = new Rectangle2D.Double(x + xInterval * j, y + yInterval * i, xInterval, yInterval);
                arrangeStoreMarblesHelper(g, 1, subRec);
                n--;
            }
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
