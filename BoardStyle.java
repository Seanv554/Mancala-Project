
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
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
interface BoardStyle {
    public static final int pitWidth = 75;
    public static final int pitHeight = 75;
    public static final int pitSpacing = pitWidth + 10;
    public static final Shape[] pitShapes = new Shape[12]; //0-5 Player A, 6-11 Player B
    public static final Shape[] storeShapes = new Shape[2];
    public static final Color[] marbleColors = {Color.RED, Color.CYAN, 
        Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.GREEN, Color.PINK, Color.YELLOW};
    public void paintStyle(Graphics g);
    public void arrangePitMarbles(Graphics g, int[] scores);
    public void arrangePitMarblesHelper(Graphics g, int n, Rectangle2D r);
    public void arrangeStoreMarbles(Graphics g, int[] scores);
    public void arrangeStoreMarblesHelper(Graphics g, int n, Rectangle2D r);
    public void drawMarble(int x, int y, Graphics g);
    public Shape[] getShapes();
}
