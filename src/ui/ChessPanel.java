package ui;

import GameSet.ChessPiece;
import GameSet.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ChessPanel extends JPanel {
    private static final int SQUARE_WIDTH = 52;
    private Chessboard board;
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


    //EFFECTS: draws the Chess Pieces onto to the panel
    public ChessPanel(Chessboard board, BufferedImage boardImg, BufferedImage wKing,
                      BufferedImage bKing, BufferedImage wQueen, BufferedImage bQueen,
                      BufferedImage wKnight, BufferedImage bKnight, BufferedImage wRook,
                      BufferedImage bRook, BufferedImage wPawn, BufferedImage bPawn,
                      BufferedImage wBishop,
                      BufferedImage bBishop) {
        super();
        this.board = board;
        this.boardImg = boardImg;
        this.wKing = wKing;
        this.bKing = bKing;
        this.wQueen = wQueen;
        this.bQueen = bQueen;
        this.wKnight = wKnight;
        this.bKnight = bKnight;
        this.wRook = wRook;
        this.bRook = bRook;
        this.wPawn = wPawn;
        this.bPawn = bPawn;
        this.wBishop = wBishop;
        this.bBishop = bBishop;
        setPreferredSize(new Dimension(500,500));
    }

    // MODIFIES: g
    // EFFECTS: draws the board, and all the pieces in their proper current positions
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBoard(g);

    }

    // MODIFIES: g
    // EFFECTS: draws the empty board onto g
    private void drawBoard(Graphics g) {
        int x = (getWidth()- boardImg.getWidth()) / 2;
        int y = (getHeight() - boardImg.getHeight()) / 2;
        g.drawImage(boardImg, x , y, this);
    }

    // MODIFIES: this
    // EFFECTS: updates the panel's internal chessboard to match current board
    private void updateInternalBoard(Chessboard b) {
        this.board = b;
    }

    // MODIFIES: g
    // EFFECTS: draws all the pieces from this.board onto g
    private void updatePieces(Graphics g) {
        for (int r = 0; r <  8; r++) {
            for (int c = 0; c < 8; c++) {
                ChessPiece currentSquare = board.board[r][c];
                if (!(currentSquare.getIcon().equals("[]"))) {
                    
                }
            }
        }

    }





}
