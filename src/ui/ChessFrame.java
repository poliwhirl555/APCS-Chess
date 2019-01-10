package ui;

import GameSet.ChessPiece;
import GameSet.Chessboard;
import GameSet.EmptySquare;
import GameSet.King;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ChessFrame extends JFrame {
    private ChessPanel chessPanel;
    private Chessboard board;
    private JTextField inputLine;
    private JScrollPane consoleMirrorScroll;
    private JTextArea consoleMirror;
    private String turnColour;
    private BufferedImage boardImgWhite;
    private BufferedImage boardImgBlack;
    private BufferedImage wKing;
    private BufferedImage bKing;
    private BufferedImage wQueen;
    private BufferedImage bQueen;
    private BufferedImage wKnight;
    private BufferedImage bKnight;
    private BufferedImage wRook;
    private BufferedImage bRook;
    private BufferedImage wPawn;
    private BufferedImage bPawn;
    private BufferedImage wBishop;
    private BufferedImage bBishop;

    // EFFECTS: constructs a Chessboard Frame with pieces in proper position
    public ChessFrame(Chessboard b) throws IOException {
        super("Chess");
        this.board = b;
        this.turnColour = "white";
        loadImages();
        initFrame();
    }

    // MODIFIES: this
    // EFFECTS: adds components to frame, do layout and make frame visible
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: adds components to frame
    private void addComponents() {
        JPanel container = new JPanel();
        chessPanel = new ChessPanel(board, turnColour, boardImgWhite, boardImgBlack,
                wKing, bKing, wQueen, bQueen, wKnight, bKnight, wRook, bRook,
                wPawn, bPawn, wBishop, bBishop);
        consoleMirror = initConsoleMirror();
        consoleMirrorScroll = new JScrollPane(consoleMirror);
        inputLine = initInputLine();
        container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
        container.add(chessPanel);
        container.add(Box.createRigidArea(new Dimension(560, 5)));
        container.add(consoleMirrorScroll);
        container.add(inputLine);
        add(container);
    }


    // EFFECTS: initialized the console redirect and returns it.
    private JTextArea initConsoleMirror() {
        JTextArea consoleMirror = new JTextArea(10, 50);
        consoleMirror.setEditable(false);
        //consoleMirror.setLineWrap(true);
        //consoleMirror.setWrapStyleWord(true);
        consoleMirror.setFont(new Font("Calibri", Font.PLAIN, 20));
        PrintStream redirect = new PrintStream(new TextOutputStream(consoleMirror));
        System.setOut(redirect);
        System.setErr(redirect);
        return consoleMirror;
    }

    private JTextField initInputLine() {
        JTextField inputLine = new JTextField(45);
        inputLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entry = inputLine.getText();
                if (conductMove(entry)) {
                    System.out.println(entry);
                    inputLine.setText("");
                    System.out.println("Please enter your move: ");
                }
                board.checkPossibleMoves();
                repaint();
                pack();
                isCheckmate();
            }

        });

        return inputLine;
    }

    private void isCheckmate() {
        if (chessPanel.isCheckmate()) {
            JOptionPane winnerDialogue = new JOptionPane();
            String message = chessPanel.getWinner() + "has won by checkmate!";
            winnerDialogue.showMessageDialog(this, message, "Winner",JOptionPane.PLAIN_MESSAGE);
        }
    }



    // MODIFIES: this
    // EFFECTS: loads images for the chess pieces plus the board
    private void loadImages() throws IOException {
        boardImgWhite = ImageIO.read(new File("assets/board-white.png"));
        boardImgBlack = ImageIO.read(new File("assets/board-black.png"));
        wKing = ImageIO.read(new File("assets/wKing.png"));
        bKing = ImageIO.read(new File("assets/bKing.png"));
        wQueen = ImageIO.read(new File("assets/wQueen.png"));
        bQueen = ImageIO.read(new File("assets/bQueen.png"));
        wKnight = ImageIO.read(new File("assets/wKnight.png"));
        bKnight = ImageIO.read(new File("assets/bKnight.png"));
        wRook = ImageIO.read(new File("assets/wRook.png"));
        bRook = ImageIO.read(new File("assets/bRook.png"));
        wPawn = ImageIO.read(new File("assets/wPawn.png"));
        bPawn = ImageIO.read(new File("assets/bPawn.png"));
        wBishop = ImageIO.read(new File("assets/wBishop.png"));
        bBishop = ImageIO.read(new File("assets/bBishop.png"));
    }

    private void changeTurnColour() {
        if (getPanelTurnColour().equals("white")) {
            updateChessPanel("black", this.board);
            turnColour = "black";
        } else {
            updateChessPanel("white", this.board);
            turnColour = "white";
        }
    }


    public void updateChessPanel(String turnColour, Chessboard b) {
        this.chessPanel.updateInternalBoard(b);
        this.chessPanel.updateTurnColour(turnColour);

    }

    public String getPanelTurnColour() {
        return this.chessPanel.getTurnColour();
    }


    public boolean conductMove(String entry) {
        String endLocation;
        String pieceMoved;
        int pieceRow;
        int pieceCol;


        pieceMoved = entry.substring(0, entry.indexOf(" "));
        //System.out.println(pieceMoved); //Test Line
        //System.out.println(Character.getNumericValue('a')); //Test Line
        endLocation = entry.substring(entry.indexOf(" ") + 1);
        pieceRow = Character.getNumericValue(pieceMoved.charAt(1)) -1 ;
        pieceCol = Character.getNumericValue(pieceMoved.charAt(0)) - 10;


        if (pieceRow < 8 && pieceRow > -1 && pieceCol < 8 && pieceCol > -1) {
            ChessPiece moved = board.board[pieceRow][pieceCol];

            if (moved instanceof EmptySquare) {
                System.out.println("Invalid Selection, square is empty.");
                return false;
            }

            if (!moved.getColour().equals(turnColour)) {
                System.out.println("Invalid selection, that piece is your opponent's colour.");
                return false;
            }

            if (!moved.validMoves.isEmpty() && moved.getColour().equals(turnColour)) {
                //Test Line
                //System.out.println(mainmoved.getPossibleMoves(mainBoard));
                //System.out.println(mainmoved.getIcon());
                //
                if(moved.move(endLocation,board)) {
                    changeTurnColour();
                    return true;
                }

                return false;
                //System.out.println("confirm"); // Test Line

            } else if (moved.validMoves.isEmpty() && moved.getColour().equals(turnColour)){
                //Test Line
                //System.out.println(mainmoved.getPossibleMoves(mainBoard));
                //System.out.println(mainmoved.getIcon());
                System.out.println("Invalid Piece, has no valid moves.");
                return false;
            }

        } else {
            System.out.println("Invalid Entry");
            return false;
        }

        return false;
    }


    public static void main(String[] args) {
        try {
            Chessboard mainBoard = new Chessboard();
            ChessFrame mainFrame = new ChessFrame(mainBoard);

//            ChessPiece whiteKing = mainBoard.board[0][3];
//            ChessPiece blackKing = mainBoard.board[7][3];
//            for (ChessPiece[] row : mainBoard.board) {
//                for (ChessPiece i : row) {
//                    if (i instanceof King) {
//                        if (i.getColour().equals("white")) {
//                            whiteKing = i;
//                        } else if (i.getColour().equals("black")) {
//                            blackKing = i;
//                        }
//                    }
//                }
//            }

            System.out.println("Welcome to my Chess game, please enter your moves in this format: \n " +
                    "[Location of piece you want to move] [Location where you want to move the piece to]");
            System.out.println("For example: a2 a3");
            System.out.println("This will move the piece in a2 to a3. \n" +
                    " (Assuming that position is a valid position for that piece to move.)");

            //mainBoard.setUpSampleBoard(); //TestCode
            //while (!((King)whiteKing).isCheckmate() || !((King)blackKing).isCheckmate()) {


                mainBoard.checkPossibleMoves();
                //System.out.println("J");
//                ((King) whiteKing).getAllOppositeAttackablePositions(mainBoard);
//                ((King) blackKing).getAllOppositeAttackablePositions(mainBoard);
                //System.out.println("I :" + ((GameSet.King) blackKing).allOppositeAttackablePositions);
                //System.out.println(((GameSet.King) blackKing).allOppositeAttackablePositions.contains(new Point(4,6)));

                mainBoard.checkPossibleMoves();
                System.out.println("Please enter your move: ");



        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to run application: cannot read image files");
        }
    }
}
