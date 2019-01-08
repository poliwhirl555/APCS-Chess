package GameSet;

import GameSet.Chessboard;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends ChessPiece {
    private String icon;

    public Queen(String colour) {
        super(colour);

        if (getColour().equals("white")) {
            icon = "Q+";
        } else if (getColour().equals("black")) {
            icon = "Q-";
        }

    }

    @Override
    public ArrayList<Point> getPossibleMoves(Chessboard gameBoard) {
        checkingKing = false;
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int rowPosition = (int) boardLocation.getX();
        int columnPosition = (int) boardLocation.getY();

        if (rowPosition != 7 && columnPosition != 7) {
            for (int r = rowPosition + 1, c = columnPosition + 1; r < 8 && c < 8 && r > -1 && c > -1; r++,c++) {
                ChessPiece checkedSquare = gameBoard.board[r][c];
                if (checkedSquare instanceof EmptySquare) {
                    possibleMoves.add(new Point(r,c));
                } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                    checkingKing = true;
                    break;
                } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                    possibleMoves.add(new Point(r,c));
                    break;
                } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                    attackableSquares.add(new Point(r,c));
                    break;
                }
            }
        }

        if (rowPosition != 0 && columnPosition != 0) {
            for (int r = rowPosition - 1, c = columnPosition - 1; r < 8 && c < 8 && r > -1 && c > -1; r--,c--) {
                ChessPiece checkedSquare = gameBoard.board[r][c];
                if (checkedSquare instanceof EmptySquare) {
                    possibleMoves.add(new Point(r,c));
                } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                    checkingKing = true;
                    break;
                } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                    possibleMoves.add(new Point(r,c));
                    break;
                } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                    attackableSquares.add(new Point(r,c));
                    break;
                }
            }
        }

        if (rowPosition != 0 && columnPosition != 7) {
            for (int r = rowPosition - 1, c = columnPosition + 1; r < 8 && c < 8 && r > -1 && c > -1; r--,c++) {
                ChessPiece checkedSquare = gameBoard.board[r][c];
                if (checkedSquare instanceof EmptySquare) {
                    possibleMoves.add(new Point(r,c));
                } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                    checkingKing = true;
                    break;
                } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                    possibleMoves.add(new Point(r,c));
                    break;
                } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                    attackableSquares.add(new Point(r,c));
                    break;
                }
            }
        }

        if (rowPosition != 7 && columnPosition != 0) {
            for (int r = rowPosition + 1, c = columnPosition - 1; r < 8 && c < 8 && r > -1 && c > -1; r++,c--) {
                ChessPiece checkedSquare = gameBoard.board[r][c];
                if (checkedSquare instanceof EmptySquare) {
                    possibleMoves.add(new Point(r,c));
                } else if (checkedSquare instanceof King && !(checkedSquare.getColour().equals(getColour()))) {
                    checkingKing = true;
                    break;
                } else if (checkedSquare instanceof ChessPiece && !checkedSquare.getColour().equals(getColour())){
                    possibleMoves.add(new Point(r,c));
                    break;
                } else if (checkedSquare instanceof ChessPiece && checkedSquare.getColour().equals(getColour())) {
                    attackableSquares.add(new Point(r,c));
                    break;
                }
            }
        }

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

        for (int i = columnPosition - 1; i < -1; i--) {

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

        validMoves = possibleMoves;
        for (Point i : validMoves) {
            attackableSquares.add(i);
        }
        //System.out.println("Q" + attackableSquares); //Test Code
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
