package ui;

import GameSet.ChessPiece;
import GameSet.Chessboard;
import GameSet.EmptySquare;
import GameSet.King;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;


public class ChessPanel extends JPanel {
    private static final int SQUARE_WIDTH = 60;
    private static final int BORDER_WIDTH = 60;
    private String winner;
    private Chessboard board;
    private ChessPiece whiteKing;
    private ChessPiece blackKing;
    private BufferedImage boardImgWhite;
    private BufferedImage boardImgBlack;
    private String turnColour;
    private HashMap<String, BufferedImage> pieceImgs = new HashMap<>();


    //EFFECTS: draws the Chess Pieces onto to the panel
    public ChessPanel(Chessboard board, BufferedImage boardImgWhite,
                      BufferedImage boardImgBlack, BufferedImage wKing,
                      BufferedImage bKing, BufferedImage wQueen, BufferedImage bQueen,
                      BufferedImage wKnight, BufferedImage bKnight, BufferedImage wRook,
                      BufferedImage bRook, BufferedImage wPawn, BufferedImage bPawn,
                      BufferedImage wBishop,
                      BufferedImage bBishop) {
        super();
        this.board = board;
        setKingVariables();
        this.turnColour = "white";
        this.boardImgWhite = boardImgWhite;
        this.boardImgBlack = boardImgBlack;
        pieceImgs.put("K+", wKing);
        pieceImgs.put("K-", bKing);
        pieceImgs.put("Q+", wQueen);
        pieceImgs.put("Q-", bQueen);
        pieceImgs.put("N+", wKnight);
        pieceImgs.put("N-", bKnight);
        pieceImgs.put("R+", wRook);
        pieceImgs.put("R-", bRook);
        pieceImgs.put("B+", wBishop);
        pieceImgs.put("B-", bBishop);
        pieceImgs.put("P+", wPawn);
        pieceImgs.put("P-", bPawn);
        setPreferredSize(new Dimension(boardImgWhite.getWidth(),boardImgWhite.getHeight()));
    }

    // MODIFIES: g
    // EFFECTS: draws the board, and all the pieces in their proper current positions
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBoard(g);
        updatePieces(g);
    }

    // MODIFIES: g
    // EFFECTS: draws the empty board onto g
    private void drawBoard(Graphics g) {
        int x = (getWidth()- boardImgWhite.getWidth()) / 2;
        int y = (getHeight() - boardImgWhite.getHeight()) / 2;

        if (turnColour.equals("white")) {
            g.drawImage(boardImgWhite, x , y, this);
        } else {
            g.drawImage(boardImgBlack, x , y, this);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the panel's internal chessboard to match current board
    public void updateInternalBoard(Chessboard b) {
        this.board = b;
    }

    public void updateTurnColour(String turnColour) {
        this.turnColour = turnColour;
    }

    public String getTurnColour() {
        return this.turnColour;
    }

    public String getWinner() {
        return winner;
    }

    private void setKingVariables() {
        whiteKing = board.board[0][3];
        blackKing = board.board[7][3];
    }

    public boolean isCheckmate() {
        ((King) whiteKing).getAllOppositeAttackablePositions(board);
        ((King) blackKing).getAllOppositeAttackablePositions(board);

        if (((King)whiteKing).isCheckmate()) {
            winner = "White";
            return true;
        } else if (((King)blackKing).isCheckmate()) {
            winner = "Black";
            return true;
        }

        return false;
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

            // TODO: Create function to check if the move inputted blocks the check.
            if (!(moved instanceof King) && moved.getColour().equals(turnColour)) {
                if (moved.getColour().equals("white") && ((King)whiteKing).inCheck(board)) {
                    if (board.blocksCheckmate(pieceRow, pieceCol, (King)whiteKing, endLocation,  pieceMoved)) {
                        moved.move(endLocation,board);
                        ((King) whiteKing).inCheck(board);
                        changeTurnColour();
                        return true;
                    } else {
                        System.out.println("Invalid move, your king is in check!");
                        return false;
                    }
                } else if (moved.getColour().equals("black") && ((King)blackKing).inCheck(board)) {
                    if (board.blocksCheckmate(pieceRow, pieceCol, (King)blackKing, endLocation,  pieceMoved)) {
                        moved.move(endLocation,board);
                        ((King) blackKing).inCheck(board);
                        changeTurnColour();
                        return true;
                    } else {
                        System.out.println("Invalid move, your king is in check!");
                        return false;
                    }

                }
            }

            if (!moved.getColour().equals(turnColour)) {
                System.out.println("Invalid selection, that piece is your opponent's colour.");
                return false;
            }

            if (!moved.validMoves.isEmpty() && moved.getColour().equals(turnColour)) {
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

    private void changeTurnColour() {
        if (turnColour.equals("white")) {
            turnColour = "black";
        } else {
            turnColour = "white";
        }
    }

    // MODIFIES: g
    // EFFECTS: draws all the pieces from this.board onto g
    private void updatePieces(Graphics g) {
        for (int r = 0; r <  8; r++) {
            for (int c = 0; c < 8; c++) {
                ChessPiece currentSquare = board.board[r][c];
                if (!(currentSquare.getIcon().equals("[]"))) {
                    if (turnColour.equals("black")) {
                        int x = (getWidth()- boardImgWhite.getWidth()) / 2 + SQUARE_WIDTH * (7 - c) + BORDER_WIDTH;
                        int y = (getHeight() - boardImgWhite.getHeight()) / 2 + SQUARE_WIDTH * r + BORDER_WIDTH;
                        g.drawImage(pieceImgs.get(currentSquare.getIcon()), x, y, this);
                    } else {
                        int x = (getWidth()- boardImgBlack.getWidth()) / 2 + SQUARE_WIDTH * c + BORDER_WIDTH;
                        int y = (getHeight() - boardImgBlack.getHeight()) / 2 + SQUARE_WIDTH * (7 - r)+ BORDER_WIDTH;
                        g.drawImage(pieceImgs.get(currentSquare.getIcon()), x, y, this);
                    }

                }
            }
        }
    }





}
