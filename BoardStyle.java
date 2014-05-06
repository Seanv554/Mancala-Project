
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
/*
 * Author: Sean Vail
 * Class: CS-151
 * Section: 02
 * Assignment: Mancala Project
 * Date: Apr 27, 2014
 * 
 */

/**
 * @author Nick Saric
 * @author Sean Vail
 * @author Alvin Ko
 *
 * Interface BoardStyle Implemented by: RectanglularPitBoard and RoundedPitBoard
 *
 * Each BoardStyle provides the specific implementation by which to render the
 * Mancala board that can be decided upon by the user during runtime.
 */
interface BoardStyle {

    public static final int pitWidth = 75;
    public static final int pitHeight = 75;
    public static final int pitSpacing = pitWidth + 10;

    /**
     * Strategy by which to paint the pit and store areas of the Mancala board
     * as well as a marble counter for each pit and store based on given scores
     *
     * @param g - Graphics Object
     * @param pitScores - Current Pit Scores, int[12] expected
     * @param storeScores - Current Mancala Scores , int[2] expected
     */
    public void paintStyle(Graphics g, int[] pitScores, int[] storeScores);

    /**
     * Used to arrange marbles in a way such that they visually fit into each
     * pit, up to 12 visible at a time.
     *
     * @param g - Graphics Object
     * @param scores - Current Pit Scores, int[12] expected
     */
    public void arrangePitMarbles(Graphics g, int[] scores);

    /**
     * Used to arrange marbles in a way such that they visually fit into each
     * Mancala.
     *
     * @param g - Graphics Object
     * @param scores - Current Mancala Scores, int[2] expected
     */
    public void arrangeStoreMarbles(Graphics g, int[] scores);

    /**
     *
     * @return int - Specified Width of BoardStyle
     */
    public int getWidth();

    /**
     *
     * @return int - Specified Height of BoardStyle
     */
    public int getHeight();

    /**
     * Returns references of all the pit shapes drawn by the BoardStyle. So that
     * the mouse listener may check to see if clicked Points are contained
     * within
     *
     * @return Shape[12] - References to all pit Shapes drawn by Style
     */
    public Shape[] getShapes();
}
