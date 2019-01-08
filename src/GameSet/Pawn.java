package GameSet;

import GameSet.Chessboard;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
    private String icon;

    public Pawn(String colour) {
        super(colour);

        if (getColour().equals("white")) {
            icon = "P+";
        } else if (getColour().equals("black")) {
            icon = "P-";
        }

    }

    @Override
    public ArrayList<Point> getPossibleMoves(Chessboard gameBoard) {
        checkingKing = false;
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int rowPosition = (int) boardLocation.getX();
        int columnPosition = (int) boardLocation.getY();

        //Regular moving
        if (this.getColour().equals("white") && rowPosition != 7) {
            if (gameBoard.board[rowPosition + 1][columnPosition] instanceof EmptySquare) {
                possibleMoves.add(new Point(rowPosition + 1, columnPosition));

                if (rowPosition == 1) {
                    if (gameBoard.board[rowPosition + 2][columnPosition] instanceof EmptySquare) {
                        possibleMoves.add(new Point(rowPosition + 2, columnPosition));
                    }
                }
            }
        }

        if (this.getColour().equals("black") && rowPosition != 0) {
            if (gameBoard.board[rowPosition - 1][columnPosition] instanceof EmptySquare) {
                possibleMoves.add(new Point(rowPosition - 1, columnPosition));

                if (rowPosition == 6) {
                    if (gameBoard.board[rowPosition - 2][columnPosition] instanceof EmptySquare) {
                        possibleMoves.add(new Point(rowPosition - 2, columnPosition));
                    }
                }
            }
        }

        //Getting the attacking squares, you need very unique logic for the pawns, as they can attack a certain way, but they can't move that way normally, unlike any other piece
        if (this.getColour().equals("white")) {

            if (columnPosition != 7 && rowPosition != 7) {
                ChessPiece checkedPiece = gameBoard.board[rowPosition + 1][columnPosition + 1];

                if(checkedPiece instanceof ChessPiece && !(checkedPiece instanceof EmptySquare) && checkedPiece.getColour().equals("black")) {
                    if(checkedPiece instanceof King) {
                        checkingKing = true;
                    } else {
                        possibleMoves.add(new Point(rowPosition + 1, columnPosition + 1));
                        attackableSquares.add(new Point(rowPosition + 1, columnPosition + 1));
                    }

                }
            }

            if (columnPosition != 0 && rowPosition != 7) {
                ChessPiece checkedPiece = gameBoard.board[rowPosition + 1][columnPosition - 1];

                if(checkedPiece instanceof ChessPiece && !(checkedPiece instanceof EmptySquare) && checkedPiece.getColour().equals("black")) {
                    if(checkedPiece instanceof King) {
                        checkingKing = true;
                    } else {
                        possibleMoves.add(new Point(rowPosition + 1, columnPosition - 1));
                        attackableSquares.add(new Point(rowPosition + 1, columnPosition - 1));
                    }
                }
            }


        } else if(this.getColour().equals("black")) {
            if (columnPosition != 7 && rowPosition != 0) {
                ChessPiece checkedPiece = gameBoard.board[rowPosition - 1][columnPosition + 1];
                if(checkedPiece instanceof ChessPiece && !(checkedPiece instanceof EmptySquare) && checkedPiece.getColour().equals("white")) {
                    if(checkedPiece instanceof King) {
                        checkingKing = true;
                    } else {
                        possibleMoves.add(new Point(rowPosition - 1, columnPosition + 1));
                        attackableSquares.add(new Point(rowPosition - 1, columnPosition + 1));
                    }

                }
            }

            if (columnPosition != 0 && rowPosition != 0) {
                ChessPiece checkedPiece = gameBoard.board[rowPosition - 1][columnPosition - 1];
                if(checkedPiece instanceof ChessPiece && !(checkedPiece instanceof EmptySquare) && checkedPiece.getColour().equals("white")) {
                    if(checkedPiece instanceof King) {
                        checkingKing = true;
                    } else {
                        possibleMoves.add(new Point(rowPosition - 1, columnPosition - 1));
                        attackableSquares.add(new Point(rowPosition - 1, columnPosition - 1));
                    }

                }
            }
        }

        validMoves = possibleMoves;
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
