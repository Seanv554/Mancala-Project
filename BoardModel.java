
import java.util.ArrayList;
import javax.swing.event.ChangeListener;

/**
 * BoardModel controls the basic mechanics of the Mancala game
 *
 * @author Sean Vail
 * @author Alvin Ko
 * @author Nick Saric
 */
public class BoardModel {

    private int storeA, storeB, storeABackUp, storeBBackUp, currentPlayer, currentPlayerBackUp, undoCount, initialValue;
    private int[] pits, pitsBackUp, pitsCopy;
    boolean canUndo, victory;
    private ArrayList<ChangeListener> listeners; //ChangeListeners

    /*
     * Default constructor for the BoardModel class
     */
    BoardModel() {
        this(4);
    }
    /*
     * Constructor for the BoardModel class
     * @param n fills in each pit with the specified parameter
     */

    BoardModel(int n) {
        storeA = 0; //holds score for Player A (player 0)
        storeB = 0; //holds score for Player B (player 1)
        pits = new int[12]; //pits
        canUndo = false;
        currentPlayer = 0;
        initialValue = n; //initial number of marbles per pit

        listeners = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            pits[i] = n; //fill each pit

        }
    }

    /*
     * Sets up the board for a new game
     */
    public void newGame() {
        storeA = 0; //holds score for Player A (player 0)
        storeB = 0; //holds score for Player B (player 1)
        pits = new int[12]; //pits
        undoCount = 0;
        canUndo = false;
        currentPlayer = 0;
        for (int i = 0; i < 12; i++) {
            pits[i] = initialValue;

        }
        notifyListeners();
    }
    /*
     * Traversal through the board. Incorporates the rules of Mancala
     * @param x the pit the player wants to move
     */

    public void backUp() {
        pitsBackUp = new int[12];
        for (int i = 0; i < 12; i++) {
            pitsBackUp[i] = pits[i];
        }
        storeABackUp = storeA;
        storeBBackUp = storeB;
        canUndo = true;
        currentPlayerBackUp = currentPlayer;
    }

    /*
     * Basic traversal for the Mancala game
     * @param x the pit you want to move (choose b/w 0 to 11)
     */
    public void move(int x) {

        int[] rows = pits; //copy of the array "pits"
        int paramX = x; //copy of the parameter "x"
        int rightStore = storeA; //keeps track of the score for PlayerA (or Player 0?)
        int leftStore = storeB; //keeps track of the score for PlayerB (or Player 1?)
        int marbles = pits[x]; //number of marbles in each pit
        int increment = 1; //increments is incremented by 1 in "traversing"
        boolean onlyOnce = true; //USED when user selects pit 5 (bottom rightmost) & 11 (top leftmost) 
        boolean multipleTurn = false;
        boolean marblesStolen = false;

        /*
         * CHECK INITIAL CONDITIONS
         */
        if (x > 11) //if player choose a nonexistant pit
        {
            System.out.println("↓↓ ERROR: Only values from 0 to 11 are accepted. ↓↓");
            return;
        }
        if (rows[x] == 0)//check to see if current pit is empty
        {
            System.out.println("↓↓ ERROR: There are no marbles in this pit. ↓↓");
            return;
            //rightStore += backRow[i] + 1; //take enemies marbles on opposite pit
            //backRow[i] = 0; //set opposite pit to 0
            //marbles --;
        }
        if (canUndo) {
            undoCount = 0;
        }
        backUp();

        traversing: //the name of while loop
        while (marbles > 0) {
            //if user choose pit 5
            if (x == 5 && onlyOnce) {
                onlyOnce = false;

                if (marbles != 0) //increment storeB
                {
                    storeA++;
                    marbles--;
                    if (marbles == 0) {
                        multipleTurn = true;
                    }
                }

                //continue traversing;
            }

            //if user choose pit 11
            if (x == 11 && onlyOnce) {
                onlyOnce = false;

                if (marbles != 0) //increment storeB
                {
                    storeB++;
                    marbles--;
                    if (marbles == 0) {
                        multipleTurn = true;
                    }
                }

                //continue traversing;
            }

            //reset pit to start at 0
            if ((x + increment) > 11) {
                //reset increment to 0
                x = 0;
                increment = 0;
                //continue traversing;
            }


            /*
             * INCREMENT STOREA AND STOREB
             */
            if ((x + increment) == 5 && marbles != 0) //last pit for first player
            {

                rows[x + increment]++;
                marbles--;
                if (rows[x + increment] == 1 && rows[(11 - (x + increment))] != 0 && marbles == 0) { //Marble Steal, Player A, Pits 5 Special Case
                    if (currentPlayer == 0) {
                        storeA += rows[(11 - (x + increment))] + 1;
                        rows[(11 - (x + increment))] = 0;
                        rows[x + increment] = 0;
                        marblesStolen = true;
                    }
                    /**
                     * else if (currentPlayer == 1) { storeB += rows[(11 - (x +
                     * increment))] + 1; }
                     */
                }

                if (marbles != 0) //increment storeB
                {
                    storeA++;
                    marbles--;
                    if (marbles == 0) {
                        multipleTurn = true;
                    }

                    increment++;
                }

                //continue traversing;
            }

            if ((x + increment) == 11 && marbles != 0) //last pit for second player
            {
                rows[x + increment]++;
                marbles--;
                if (rows[x + increment] == 1 && rows[(11 - (x + increment))] != 0 && marbles == 0) { //Marble Steal, Player B, Pit 11 Special Case
                    /**
                     * if (currentPlayer == 0) { storeA += rows[(11 - (x +
                     * increment))] + 1; } else
                     */
                    if (currentPlayer == 1) {
                        storeB += rows[(11 - (x + increment))] + 1;
                        rows[(11 - (x + increment))] = 0;
                        rows[x + increment] = 0;
                        marblesStolen = true;
                    }

                }

                if (marbles != 0) //increment storeB
                {
                    storeB++;
                    marbles--;
                    if (marbles == 0) {
                        multipleTurn = true;
                    }
                    increment++;
                }

                //reset values
                x = 0;
                increment = 0;
                continue traversing;
            }

            if (!marblesStolen) {
                rows[x + increment]++;
                marbles--;
            }
            if (rows[x + increment] == 1 && marbles == 0 && rows[(11 - (x + increment))] != 0) {
                if (currentPlayer == 0 && (x + increment) <= 5) {
                    storeA += rows[(11 - (x + increment))] + 1;
                    rows[(11 - (x + increment))] = 0;
                    rows[x + increment] = 0;
                } else if (currentPlayer == 1 && (x + increment) > 5) {
                    storeB += rows[(11 - (x + increment))] + 1;
                    rows[(11 - (x + increment))] = 0;
                    rows[x + increment] = 0;
                }

            }
            increment++;

        }

        if (!multipleTurn && getPlayer() == 0) {
            currentPlayer = 1;
        } else if (!multipleTurn && getPlayer() == 1) {
            currentPlayer = 0;
        }
        rows[paramX] = 0;
        notifyListeners();
    }

    /*
     * getCanUndo returns the canUndo boolean variable
     * @return canUndo true if the player can undo and false if not
     */
    public boolean canUndo()
    {
        return canUndo;
    }
    
    public void undoLastMove() {
        if (!canUndo) {
            System.err.println("Error: Cannot undo");
            return;
        } else if (canUndo && undoCount == 3) {
            System.err.println("Error: Undo limit reached");
            canUndo = false;
            notifyListeners();
            return;
        }
        pits = new int[12];
        for (int i = 0; i < 12; i++) {
            pits[i] = pitsBackUp[i];
        }
        storeA = storeABackUp;
        storeB = storeBBackUp;
        canUndo = false;
        undoCount++;
        currentPlayer = currentPlayerBackUp;
        notifyListeners();
    }

    public int getPlayer() {
        return currentPlayer;
    }

    @Override
    public String toString() {
        String board = "";
        board += "+++++++++++++++++++++++++++++++++++++++++++++++++++\n";
        board += "   "; //gap before 
        for (int i = 11; i >= 6; i--) {
            board += " ( " + pits[i] + " ) ";
        }
        board += "\n[ " + storeB + " ]";
        board += "======================================";

        board += "[ " + storeA + " ]\n";
        board += "   ";
        for (int i = 0; i < 6; i++) {
            board += " ( " + pits[i] + " ) ";
        }

        return board;
    }

    public int getStore(int player) {
        //player a is storeA
        //player b is storeB

        if (player == 0) {
            return storeA;
        }

        return storeB;
    }

    /*
     * 
     */
    public int[] getPits() {
        //return (player == 0)? pitA[i] : pitB[i];
        return pits;
    }

    /*
     * returns the total number of marbles in either player A's or player B's mancalas
     * @param player the player's mancala to return
     * @return storeA if parameter is 0
     * @return storeB if parameter is 1
     */
    public int getTotal(int player) {
        return (player == 0) ? storeA : storeB;
    }

    /*
     * If pits 0-5 are collectively empty or pits 6-11 are collectively empty the game ends
     * @return true if pits on either side are collectively empty
     * @return false if pits on either side are NOT collectively empty
     */
    public boolean checkVictory() {
        int sumA = 0;
        int sumB = 0;

        pitsCopy = new int[12];
        for(int i = 0; i < 12; i++) {
            pitsCopy[i] = pits[i];
        }

        for (int i = 0; i < 6; i++) {
            sumA += pitsCopy[i];
        }
        for (int i = 6; i < 12; i++) {
            sumB += pitsCopy[i];
        }

        if (sumA == 0 || sumB == 0) 
        {
            storeB += sumB;
            storeA += sumA;
            for(int i = 0; i < 6; i++)
            {
                pits[i] = 0;
                pits[11-i] = 0;
            }
            return true;
        }

        return false;
    }

    /*
     * Returns the victor of the Mancala game
     * @return 0 player A won
     * @return 1 player B won
     * @return 2 player A and B tie
     */
    public int getVictor() {
        if (checkVictory() == true) {
            if (storeA > storeB) {
                return 0; //player A won
            } else if (storeB > storeA) {
                return 1; //player B won
            }

        }

        return 2; //2 means it's a tie
    }
   

    /*
     * Notify listeners of a state change
     */
    public void notifyListeners() {
        for (ChangeListener l : listeners) {
            l.stateChanged(null);
        }
    }

    /*
     * attach listeners with ChangeListener listerner
     */
    public void attach(ChangeListener listener) {
        listeners.add(listener);
    }
}
