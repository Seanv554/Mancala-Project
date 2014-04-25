
import java.util.ArrayList;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean Vail
 */
public class BoardModel {
    private int storeA, storeB, currentPlayer;
    private int[] pitA, pitB, lastMove; 
    private ArrayList<ChangeListener> listeners; //ChangeListeners
    
    BoardModel(){
        this(4);
    }
    
    BoardModel(int n){
        storeA = 0;
        storeB = 0;
        pitA = new int[6]; //front row of pits, Player A
        pitB = new int[6]; //back row of pits, Player B
        listeners = new ArrayList<>(); 
        for(int i = 0; i < 6; i++){
            pitA[i] = n;
            pitB[i] = n;
        }
    }
    public void move(int x){
        
        int[] frontRow = (currentPlayer == 0)? pitA : pitB;
        int[] backRow = (currentPlayer == 0)? pitB : pitA;
        int rightStore = (currentPlayer == 0)? storeA : storeB;
        int leftStore = (currentPlayer == 0)? storeB : storeA;
        int marbles = frontRow[x];
        
        for(int i = x + 1; marbles != 0; i++){
            if(i < 6){ //traversing friendly pits
                if(frontRow[i] == 0){ //check to see if current pit is empty
                    rightStore += backRow[i] + 1; //take enemies marbles on opposite pit
                    backRow[i] = 0; //set opposite pit to 0
                    marbles --;
                }else{ //normal traversal
                    frontRow[i]++;
                    marbles --;
                }
            }else if(i == 6){ //traversing friendly store
                rightStore++;
                marbles --;
            }else if (i > 6){ //traversing enemy pits
                backRow[i-(2*(i-6))]++;
                marbles --;
            }else if (i == 13){ //tranversing enemy store
                i = 0;
            }
        }
        currentPlayer = (currentPlayer == 0)? 1 : 0;
        frontRow[x] = 0;
    }
    
    public void undoLastMove(){
        if(lastMove[0] == -1){
            System.err.println("Error: Can only undo once.");
        }
        else{
            
        }
    }
    
    public String toString(){
        String board = "";
        board += "------------------------------\n";
        board += "[ " + storeB + " ]";
        for(int i = 0; i < 6; i++){
            board += " ( " + pitB[i] + " ) ";
        }
        board += "[  ]\n";
        board += "[  ]"; 
        for(int i = 0; i < 6; i++){
            board += " ( " + pitA[i] + " ) ";
        }
        board += "[ " + storeA + " ]\n";
        
        return board;
    }
    
    public int getStore(int player)
    {
        //player a is storeA
        //player b is storeB
        
        if(player == 0)
        {
            return storeA; 
        }

        return storeB; 
    }
    

    public int getPit(int player, int i)
    {
        return (player == 0)? pitA[i] : pitB[i];
    }

    public int getTotal(int player)
    {
        return (player == 0)? storeA : storeB;
    }

    public int checkVictory()
    {
        return 0; //needs implementation
    }

    public int getVictor()
    {
        return 0; //needs implementation
    }

    public void notifyListeners()
    {
        for(ChangeListener l : listeners){
            
        }
    }

    public void attach(ChangeListener listener)
    {

    }
}
