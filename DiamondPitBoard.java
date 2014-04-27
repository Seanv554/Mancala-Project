
import java.awt.Graphics;
import java.awt.Shape;

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
    public void arrangeMarbles(Graphics g, int[] scores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape[] getShapes() {
        return shapes;
    }

}
