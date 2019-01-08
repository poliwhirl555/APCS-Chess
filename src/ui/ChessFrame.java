package ui;

import GameSet.ChessPiece;
import GameSet.Chessboard;
import GameSet.EmptySquare;
import GameSet.King;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ChessFrame extends JFrame {
    private ChessPanel chessPanel;
    private Chessboard board;
    private String turnColour;
    private BufferedImage boardImg;
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
        chessPanel = new ChessPanel(board, turnColour, boardImg, wKing, bKing, wQueen, bQueen, wKnight, bKnight, wRook, bRook,
                wPawn, bPawn,wBishop, bBishop);
        container.add(chessPanel);
        add(container);
    }

    // MODIFIES: this
    // EFFECTS: loads images for the chess pieces plus the board
    private void loadImages() throws IOException {
        boardImg = ImageIO.read(new File("assets/board2.png"));
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

    public void updateChessPanel(String turnColour, Chessboard b) {
        this.chessPanel.updateInternalBoard(b);
        this.chessPanel.updateTurnColour(turnColour);

    }

    public static void main(String[] args) {
        try {
            Chessboard mainBoard = new Chessboard();
            ChessFrame mainFrame = new ChessFrame(mainBoard);

            ChessPiece whiteKing = mainBoard.board[0][3];
            ChessPiece blackKing = mainBoard.board[7][3];
            for (ChessPiece[] row : mainBoard.board) {
                for (ChessPiece i : row) {
                    if (i instanceof King) {
                        if (i.getColour().equals("white")) {
                            whiteKing = i;
                        } else if (i.getColour().equals("black")) {
                            blackKing = i;
                        }
                    }
                }
            }

            Scanner input = new Scanner(System.in);
            String currentColour = "white";
            System.out.println("Welcome to my Chess game, please enter your moves in this format: [Location of piece you want to move] [Location where you want to move the piece to]");
            System.out.println("For example: a2 a3");
            System.out.println("This will move the piece in a2 to a3, assuming that position is a valid position for that piece to move");

            //mainBoard.setUpSampleBoard(); //TestCode
            while (!((King)whiteKing).isCheckmate() || !((King)blackKing).isCheckmate()) {


                mainBoard.checkPossibleMoves();
                //System.out.println("J");
                ((King) whiteKing).getAllOppositeAttackablePositions(mainBoard);
                ((King) blackKing).getAllOppositeAttackablePositions(mainBoard);
                //System.out.println("I :" + ((GameSet.King) blackKing).allOppositeAttackablePositions);
                //System.out.println(((GameSet.King) blackKing).allOppositeAttackablePositions.contains(new Point(4,6)));

                mainBoard.checkPossibleMoves();



                if (currentColour.equals("white")) {
                    mainBoard.drawBoardWhiteSide();
                } else {
                    mainBoard.drawBoardBlackSide();
                }

                String endLocation;
                String pieceMoved;
                int pieceRow;
                int pieceCol;

                //The only possible way to break this loop is to enter a valid position
                while(true){
                    mainBoard.checkPossibleMoves();
                    System.out.println("Please enter your move: ");
                    String entry = input.nextLine();
                    pieceMoved = entry.substring(0, entry.indexOf(" "));
                    //System.out.println(pieceMoved); //Test Line
                    //System.out.println(Character.getNumericValue('a')); //Test Line
                    endLocation = entry.substring(entry.indexOf(" ") + 1);
                    pieceRow = Character.getNumericValue(pieceMoved.charAt(1)) -1 ;
                    pieceCol = Character.getNumericValue(pieceMoved.charAt(0)) - 10;
                    //Test Line
                    //System.out.println("T " + mainBoard.board[pieceRow][pieceCol].getPossibleMoves(mainBoard));
                    //System.out.println("T " + mainBoard.board[pieceRow][pieceCol].validMoves);
                    //System.out.println(mainBoard.board[pieceRow][pieceCol].getPossibleMoves(mainBoard).isEmpty());


                    if (pieceRow < 8 && pieceRow > -1 && pieceCol < 8 && pieceCol > -1) {

                        if (mainBoard.board[pieceRow][pieceCol] instanceof EmptySquare) {
                            System.out.println("Invalid Selection, square is empty.");
                        }

                        if (!mainBoard.board[pieceRow][pieceCol].getColour().equals(currentColour)) {
                            System.out.println("Invalid selection, that piece is your opponent's colour.");
                        }

                        if (!mainBoard.board[pieceRow][pieceCol].validMoves.isEmpty() && mainBoard.board[pieceRow][pieceCol].getColour().equals(currentColour)) {
                            //Test Line
                            //System.out.println(mainBoard.board[pieceRow][pieceCol].getPossibleMoves(mainBoard));
                            //System.out.println(mainBoard.board[pieceRow][pieceCol].getIcon());
                            //
                            if(mainBoard.board[pieceRow][pieceCol].move(endLocation,mainBoard)) {
                                break;
                            }
                            //System.out.println("confirm"); // Test Line

                        } else if (mainBoard.board[pieceRow][pieceCol].validMoves.isEmpty() && mainBoard.board[pieceRow][pieceCol].getColour().equals(currentColour)){
                            //Test Line
                            //System.out.println(mainBoard.board[pieceRow][pieceCol].getPossibleMoves(mainBoard));
                            //System.out.println(mainBoard.board[pieceRow][pieceCol].getIcon());
                            System.out.println("Invalid Piece, has no valid moves.");
                        }

                    } else {
                        System.out.println("Invalid Entry");
                    }
                }

                //System.out.println("testLine");

                //Keeps track of which colour the current turn is, so that the board is printed correctly, and so that you cannot move the opponenent's pieces
                if (currentColour.equals("white")) {
                    currentColour = "black";
                } else {
                    currentColour = "white";
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to run application: cannot read image files");
        }
    }
}
