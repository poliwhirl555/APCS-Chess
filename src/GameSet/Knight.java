package GameSet;

import GameSet.Chessboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends ChessPiece {
    private String icon;

    public Knight(String colour) {
        super(colour);

        if (getColour().equals("white")) {
            icon = "N+";
        } else if (getColour().equals("black")) {
            icon = "N-";
        }

    }

    @Override
    public ArrayList<Point> getPossibleMoves(Chessboard gameBoard) {
        checkingKing = false;
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int rowPosition = (int) boardLocation.getX();
        int columnPosition = (int) boardLocation.getY();

        ArrayList<Point> possiblePositions = new ArrayList<>(Arrays.asList(new Point(rowPosition + 2, columnPosition + 1), new Point(rowPosition + 1, columnPosition + 2),
                new Point(rowPosition - 1,columnPosition - 2), new Point(rowPosition - 1, columnPosition + 2), new Point(rowPosition + 1, columnPosition - 2),
                new Point(rowPosition + 2, columnPosition - 1), new Point(rowPosition - 2, columnPosition -1), new Point(rowPosition - 2, columnPosition + 1)));

        for (Point i : possiblePositions) {
            int row = (int) i.getX();
            int col = (int) i.getY();

            if (row < 8 && row > -1 && col < 8 && col > -1) {
                ChessPiece checkedSquare = gameBoard.board[row][col];
                if (checkedSquare instanceof EmptySquare) {
                    possibleMoves.add(new Point(row,col));
                } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                    checkingKing = true;
                } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())) {
                    possibleMoves.add(new Point(row, col));
                } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                    attackableSquares.add(new Point(row,col));
                }
            }
        }
        validMoves = possibleMoves;
        for (Point i : validMoves) {
            attackableSquares.add(i);
        }
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
