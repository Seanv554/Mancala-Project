/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Author: Nick Satic
 * Class:
 * Section:
 * Assignment:
 * Date: Apr 27, 2014
 * 
 */
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;

public class BoardController {

    BoardModel data;

    public BoardController() {
        this.data = null;
    }

    public BoardController(BoardModel data) {
        this.data = data;
    }

    public void processClick(MouseEvent e, Shape[] shapes) {
        Point location = new Point(e.getX(), e.getY());
        if (data.getPlayer() == 0) {
            for (int i = 0; i < 6; i++) {
                if (shapes[i].contains(location)) {
                    data.move(i);
                }
            }
        } else {
            for (int i = 6; i < 12; i++) {
                if (shapes[i].contains(location)) {
                    data.move(i);
                }
            }
        }
    }
}
