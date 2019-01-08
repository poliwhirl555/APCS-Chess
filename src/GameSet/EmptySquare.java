package GameSet;

import GameSet.ChessPiece;
import GameSet.Chessboard;

import java.awt.*;
import java.util.ArrayList;

public class EmptySquare extends ChessPiece {
    public EmptySquare() {

    }

    @Override
    public ArrayList<Point> getPossibleMoves(Chessboard gameBoard) {
        return null;
    }

    @Override
    public String getIcon() {
        return "[]";
    }
}
