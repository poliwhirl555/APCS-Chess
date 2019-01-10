package ui;

import GameSet.ChessPiece;
import GameSet.Chessboard;
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
    public ChessPanel(Chessboard board, String turnColour, BufferedImage boardImgWhite,
                      BufferedImage boardImgBlack, BufferedImage wKing,
                      BufferedImage bKing, BufferedImage wQueen, BufferedImage bQueen,
                      BufferedImage wKnight, BufferedImage bKnight, BufferedImage wRook,
                      BufferedImage bRook, BufferedImage wPawn, BufferedImage bPawn,
                      BufferedImage wBishop,
                      BufferedImage bBishop) {
        super();
        this.board = board;
        setKingPositions();
        this.turnColour = turnColour;
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

    private void setKingPositions() {
        whiteKing = board.board[0][3];
        blackKing = board.board[7][3];
    }

    public boolean isCheckmate() {
        ((King) whiteKing).getAllOppositeAttackablePositions(board);
        ((King) blackKing).getAllOppositeAttackablePositions(board);
        boolean isCheckmate = !((King)whiteKing).isCheckmate() || !((King)blackKing).isCheckmate();

        if (((King)whiteKing).isCheckmate()) {
            winner = "White";
            return true;
        } else if (((King)blackKing).isCheckmate()) {
            winner = "Black";
            return true;
        }

        return false;
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
