package ui;

import GameSet.ChessPiece;
import GameSet.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;


public class ChessPanel extends JPanel {
    private static final int SQUARE_WIDTH = 52;
    private Chessboard board;
    private BufferedImage boardImg;
    private HashMap<String, BufferedImage> pieceImgs = new HashMap<>();


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
        setPreferredSize(new Dimension(500,500));
    }

    // MODIFIES: g
    // EFFECTS: draws the board, and all the pieces in their proper current positions
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawEmptyBoard(g);
        updatePieces(g);

    }

    // MODIFIES: g
    // EFFECTS: draws the empty board onto g
    private void drawEmptyBoard(Graphics g) {
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
                    int x = SQUARE_WIDTH * r + SQUARE_WIDTH / 2;
                    int y = SQUARE_WIDTH * c + SQUARE_WIDTH / 2;
                    g.drawImage(pieceImgs.get(currentSquare.getIcon()), x, y, this);
                }
            }
        }
    }





}