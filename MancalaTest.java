/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean Vail
 */
public class MancalaTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoardModel m = new BoardModel();
        System.out.println(m);
        m.move(0);
        System.out.println(m);
        m.move(1);
        System.out.println(m);
        m.move(1);
        System.out.println(m);
        m.move(1);
        System.out.println(m);
        
    }
    
}
