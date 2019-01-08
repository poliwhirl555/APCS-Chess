package GameSet;

import GameSet.Chessboard;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends ChessPiece {
    private String icon;



    public Rook(String colour) {
        super(colour);

        if (getColour().equals("white")) {
            icon = "R+";
        } else if (getColour().equals("black")) {
            icon = "R-";
        }

    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public ArrayList<Point> getPossibleMoves(Chessboard gameBoard) {
        checkingKing = false;
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int rowPosition = (int) boardLocation.getX();
        int columnPosition = (int) boardLocation.getY();


        for (int i = rowPosition + 1; i < 8; i++) {

            ChessPiece checkedSquare = gameBoard.board[i][columnPosition];
            if (checkedSquare instanceof EmptySquare) {
                possibleMoves.add(new Point(i, columnPosition));
            } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                checkingKing = true;
                break;
            } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                possibleMoves.add(new Point(i, columnPosition));
                break;
            } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                attackableSquares.add(new Point(i, columnPosition));
                break;
            }
        }


        for (int i = rowPosition - 1; i > -1; i--) {

            ChessPiece checkedSquare = gameBoard.board[i][columnPosition];
            if (checkedSquare instanceof EmptySquare) {
                possibleMoves.add(new Point(i, columnPosition));
            } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                checkingKing = true;
                break;
            } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                possibleMoves.add(new Point(i, columnPosition));
                break;
            } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                attackableSquares.add(new Point(i, columnPosition));
                break;
            }
        }

        for (int i = columnPosition + 1; i < 8; i++) {

            ChessPiece checkedSquare = gameBoard.board[rowPosition][i];
            if (checkedSquare instanceof EmptySquare) {
                possibleMoves.add(new Point(rowPosition, i));
            } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                checkingKing = true;
                break;
            } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                possibleMoves.add(new Point(i, columnPosition));
                break;
            } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                attackableSquares.add(new Point(i, columnPosition));
                break;
            }
        }

        for (int i = columnPosition - 1; i > -1; i--) {

            ChessPiece checkedSquare = gameBoard.board[rowPosition][i];
            if (checkedSquare instanceof EmptySquare) {
                possibleMoves.add(new Point(rowPosition, i));
            } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                checkingKing = true;
                break;
            } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                possibleMoves.add(new Point(i, columnPosition));
                break;
            } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                attackableSquares.add(new Point(i, columnPosition));
                break;
            }
        }

        validMoves.clear();
        for (Point i : possibleMoves) {
            validMoves.add(i);
        }
        for (Point i : validMoves) {
            attackableSquares.add(i);
        }

        return possibleMoves;
    }



}
