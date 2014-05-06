
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/* File: BoardView.java
 * Author: Sean Vail
 * Class: CS-151
 * Section: 02
 * Assignment: Mancala Group Project
 * Date: Apr 27, 2014
 */

/**
 *
 * @author Nick Saric
 * @author Sean Vail
 * @author Alvin Ko BoardView Implements: ChangeListener
 *
 * Generates a Visual Mancala board and an underlying BoardModel object to
 * handle game mechanics.
 *
 * Uses a BoardStyle Object to determine the style in which the pits and stores
 * are drawn.
 *
 * Sends all clicks to BoardController to be processed and have the
 * corresponding move executed based on which pit was clicked on.
 *
 */
public class BoardView extends JPanel implements ChangeListener {

    private BoardController control;
    private BoardModel data;
    private final BoardPanel board;
    private final JLabel currentPlayer = new JLabel("Player A's Turn");
    public final static int viewWIDTH = 800;
    public final static int viewHEIGHT = 300;
    private final JButton undoButton;
    private boolean gameOver = false;

    /**
     * Constructs default BoardView with RoundPitBoard Style and 4 initial
     * marbles
     */
    public BoardView() {
        this(new RoundedPitBoard(650, 250), 4);
    }

    /**
     * Constructs BoardView with specified BoardStyle and specified number of
     * initial starting marbles
     *
     * @param style - BoardStyle to be used
     * @param initialMarbles - Number of marbles for initial start
     */
    public BoardView(BoardStyle style, int initialMarbles) {
        super();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        board = new BoardPanel(style);

        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.undoLastMove();

            }
        });
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.newGame();

            }
        });
        JButton changeStyleButton = new JButton("Change Style");
        changeStyleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final JFrame f = new JFrame("Change Style");
                final JCheckBox rectangleChoice = new JCheckBox("Rectangular Pit Choice");
                final JCheckBox roundedChoice = new JCheckBox("Rounded Pit Choice");
                final JButton okayButton = new JButton("Okay");
                final JButton cancelButton = new JButton("Cancel");
                rectangleChoice.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        roundedChoice.setSelected(false);
                    }
                });
                roundedChoice.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rectangleChoice.setSelected(false);
                    }
                });
                okayButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (roundedChoice.isSelected()) {
                            board.useStyle(new RoundedPitBoard(650, 250));
                        } else if (rectangleChoice.isSelected()) {
                            board.useStyle(new RectangularPitBoard(650, 250));
                        }
                        if (roundedChoice.isSelected() || rectangleChoice.isSelected()) {
                            board.repaint();
                            f.dispose();
                        }

                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();
                    }
                });
                f.setLayout(new FlowLayout());
                f.add(roundedChoice);
                f.add(rectangleChoice);
                f.add(okayButton);
                f.add(cancelButton);
                f.setVisible(true);
                f.pack();

            }
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(changeStyleButton);
        buttonPanel.add(currentPlayer);

        data = new BoardModel(initialMarbles);
        control = new BoardController(data);
        final Shape[] shapes = style.getShapes();
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!isOver()) {
                    control.processClick(e, shapes);
                }
                if (data.checkVictory()) {
                    gameOver();
                }

            }
        });

        this.setPreferredSize(new Dimension(viewWIDTH, viewHEIGHT));
        this.setLayout(new BorderLayout());
        this.add(board, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        data.attach(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (data.getPlayer() == 0) {
            currentPlayer.setText("Player A's Turn");
        } else {
            currentPlayer.setText("Player B's Turn");
        }
        if (!data.canUndo()) {
            undoButton.setEnabled(false);
        } else {
            undoButton.setEnabled(true);
        }
        super.repaint();
    }

    /**
     * Used to disable the mouse input processing once the game has ended
     */
    private void gameOver() {
        gameOver = true;
        JLabel l;
        final JFrame f = new JFrame("Game Over!");
        f.setLayout(new FlowLayout());
        if (data.getVictor() == 0 || data.getVictor() == 1) {
            String victor = (data.getVictor() == 0) ? "A" : "B";
            l = new JLabel("Player " + victor + " has won!");
        } else {
            l = new JLabel("It's a draw!");
        }

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.newGame();
                f.dispose();
                resetGameOver();
            }
        });
        JButton exitGame = new JButton("Exit");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        f.add(l);
        f.add(newGame);
        f.add(exitGame);
        f.setVisible(true);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Used to re-enable the mouse input processing incase the user undoes their
     * last move
     */
    private void resetGameOver() {
        gameOver = false;
    }

    /**
     * Used to check if the game is over by an action listener
     *
     * @return boolean - whether or not game is over
     */
    private boolean isOver() {
        return gameOver;
    }

    /**
     * A JPanel that contains the rendering of the actual Mancala board
     */
    private class BoardPanel extends JPanel {

        private BoardStyle style;

        /**
         * Creates the default Mancala board with a Rounded Pit Style
         */
        public BoardPanel() {
            this(new RoundedPitBoard(650, 250));
        }

        /**
         * Creates a mancala board with a specified style
         *
         * @param style
         */
        public BoardPanel(BoardStyle style) {
            this.style = style;
        }

        /**
         * Returns the style currently being used
         *
         * @return BoardStyle - Style currently being used
         */
        public BoardStyle getStyle() {
            return style;
        }

        /**
         * Sets the style to be used to render the various objects on the board
         *
         * @param style - BoardStyle to be used
         */
        public void useStyle(BoardStyle style) {
            this.style = style;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int[] pitScores = data.getPits();
            int[] storeScores = {data.getStore(0), data.getStore(1)};
            style.paintStyle(g, pitScores, storeScores);
            style.arrangePitMarbles(g, pitScores);
            style.arrangeStoreMarbles(g, storeScores);
        }
    }
}
