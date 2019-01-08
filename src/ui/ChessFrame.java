package ui;

import GameSet.Chessboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessFrame extends JFrame {
    private ChessPanel chessPanel;
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

    // EFFECTS: constructs a Chessboard Frame with pieces in proper position
    public ChessFrame(Chessboard b) throws IOException {
        super("Chess");
        this.board = b;
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
        chessPanel = new ChessPanel(board, boardImg, wKing, bKing, wQueen, bQueen, wKnight, bKnight, wRook, bRook,
                wPawn, bPawn,wBishop, bBishop);
        container.add(chessPanel);
        add(container);
    }

    // MODIFIES: this
    // EFFECTS: loads images for the chess pieces plus the board
    private void loadImages() throws IOException {
        boardImg = ImageIO.read(new File("assets/board.png"));
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

    public static void main(String[] args) {
        try {
            new ChessFrame(new Chessboard());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to run application: cannot read image files");
        }
    }
}
