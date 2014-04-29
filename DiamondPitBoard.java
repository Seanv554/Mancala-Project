
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
public class DiamondPitBoard implements BoardStyle{
    private int currentColor = 0;
    private int width, height;
    
    public DiamondPitBoard(){
        width = 600;
        height = 250;
    }
    
    public DiamondPitBoard(int width, int height){
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void paintStyle(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void arrangePitMarbles(Graphics g, int[] scores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape[] getShapes() {
        return pitShapes;
    }

    @Override
    public void arrangePitMarblesHelper(Graphics g, int n, Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawMarble(int x, int y, Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void arrangeStoreMarbles(Graphics g, int[] scores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void arrangeStoreMarblesHelper(Graphics g, int n, Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
