package GameSet;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends ChessPiece {
    private String icon;

    public Bishop(String colour) {
        super(colour);

        if (getColour().equals("white")) {
            icon = "B+";
        } else if (getColour().equals("black")) {
            icon = "B-";
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

        validMoves.clear();
        for (Point i : possibleMoves) {
            validMoves.add(i);
        }
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
