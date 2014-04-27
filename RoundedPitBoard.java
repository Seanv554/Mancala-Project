
import java.awt.BasicStroke;
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
public class RoundedPitBoard implements BoardStyle{
    
    private int width, height;
    
    public RoundedPitBoard(){
        width = 600;
        height = 250;
    }
    
    public RoundedPitBoard(int width, int height){
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void paintStyle(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRoundRect(10, 10, width/8, height-(height/8), 20, 20);
        for(int i = 0; i < 6; i++){
            Ellipse2D.Double currentPit = new Ellipse2D.Double(
                    20+(width/8)+(pitSpacing*i), 10, pitWidth, pitHeight);
            shapes[i+6] = currentPit;
            g2.setStroke(new BasicStroke(3));
            g2.draw(currentPit);
        }
        g2.drawRoundRect(20+(width/8)+(pitSpacing*6), 10, width/8, height-(height/8), 20, 20);
        for(int i = 0; i < 6; i++){
            Ellipse2D.Double currentPit = new Ellipse2D.Double(
                    20+(width/8)+(pitSpacing*i), 
                    (int)(height-pitHeight*1.30), pitWidth, pitHeight);
            shapes[i] = currentPit;
            g2.setStroke(new BasicStroke(3));
            g2.draw(currentPit);
        }
    }

    @Override
    public void arrangeMarbles(Graphics g, int[] scores) {
        for(int i = 0; i < 12; i++){
            Rectangle2D r = shapes[0].getBounds2D();
            double x = r.getX();
            double y = r.getY();
            
        }
    }

    @Override
    public Shape[] getShapes() {
        return shapes;
    }

}
