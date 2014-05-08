/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Author: Nick Saric
 * Class: CS-151
 * Section: 02
 * Assignment: Mancala Project
 * Date: Apr 27, 2014
 * 
 */
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;

public class BoardController {

    BoardModel data;

     /**
     * Constructs an empty controller to use.
     */
    public BoardController() {
        this.data = null;
    }

     /**
     * Constructs a controller attached to the BoardModel.
     *
     * @param data - BoardModel to be used
     */
    public BoardController(BoardModel data) {
        this.data = data;
    }

     /**
     * Takes the MouseEvent from parameter e and checks the array
     * shapes to see which shape contains the point in e and calls
     * the move(int i) method in BoardModel on data.
     *
     * @param e - the MouseEvent to be processed
     * @param shapes - the array of shapes to be used
     */
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