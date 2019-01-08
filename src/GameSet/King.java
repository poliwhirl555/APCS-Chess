package GameSet;

import GameSet.Chessboard;

import java.awt.*;
import java.util.ArrayList;

public class King extends ChessPiece {
    private String icon;
    private boolean inCheck;
    private boolean checkmate; //Checkmate not implemented
    //Array containing all positions that is in the enemy's line of attack, required to stop the GameSet.King from moving into check, and also checkmate, if that is ever implemented
    public ArrayList<Point> allOppositeAttackablePositions = new ArrayList<>();


    public King(String colour) {
        super(colour);

        if (getColour().equals("white")) {
            icon = "K+";

        } else if (getColour().equals("black")) {
            icon = "K-";
        }

    }


    @Override
    public ArrayList<Point> getPossibleMoves(Chessboard gameBoard) {
        ArrayList<Point> possiblePositions = new ArrayList<>();
        ArrayList<Point> validPositions = new ArrayList<>();
        getAllOppositeAttackablePositions(gameBoard);

        //All possible moves
        possiblePositions.add(new Point((int)(boardLocation.getX() + 1), (int) (boardLocation.getY()) + 1));
        possiblePositions.add(new Point((int)(boardLocation.getX() - 1), (int) (boardLocation.getY()) - 1));
        possiblePositions.add(new Point((int)(boardLocation.getX() + 1), (int) (boardLocation.getY()) - 1));
        possiblePositions.add(new Point((int)(boardLocation.getX() - 1), (int) (boardLocation.getY()) + 1));
        possiblePositions.add(new Point((int)(boardLocation.getX()), (int) (boardLocation.getY()) - 1));
        possiblePositions.add(new Point((int)(boardLocation.getX()), (int) (boardLocation.getY()) + 1));
        possiblePositions.add(new Point((int)(boardLocation.getX() + 1), (int) (boardLocation.getY())));
        possiblePositions.add(new Point((int)(boardLocation.getX() - 1), (int) (boardLocation.getY())));

        //System.out.println("S " + allOppositeAttackablePositions); //Test Line

        //Checks every position that the enemy pieces can attack, and removes those positions from the list of possible positions
        for (Point i : allOppositeAttackablePositions) {
            if (possiblePositions.contains(i)) {
                possiblePositions.remove(i);
            }
        }

        //For every possible position, check if the square is empty or not, and if it is not empty, adds the proper positions to the proper arrays
        for (Point i : possiblePositions) {
            int row = i.x;
            int column = i.y;
            boolean onBoard = row < 8 && row > -1 && column < 8 && column > -1;

            if (onBoard && gameBoard.board[row][column] instanceof EmptySquare) {
                validPositions.add(i);
            } else if (onBoard && !(gameBoard.board[row][column] instanceof EmptySquare) && !gameBoard.board[row][column].getColour().equals(this.getColour())) {
                validPositions.add(i);
            } else if (onBoard && !(gameBoard.board[row][column] instanceof EmptySquare) && gameBoard.board[row][column].getColour().equals(this.getColour())) {
                attackableSquares.add(i);
            }

        }

        //Check for checkmate, except that isn't implemented yet
        if (validPositions.isEmpty() && inCheck ) {
            checkmate = true;
        }

        //Adds valid positions to attackable squares
        for (Point i : validPositions) {
            attackableSquares.add(i);
        }

        validMoves = validPositions;

        //System.out.println(validMoves);
        return validPositions;
    }

    public boolean checkIfInCheck(Chessboard gameBoard) {
        inCheck = false;
        for (ChessPiece[] row : gameBoard.board) {
            for (ChessPiece i : row) {
                if (i.checkingKing) {
                    inCheck = true;
                    return true;
                }
            }
        }

        return false;
    }

    public void getAllOppositeAttackablePositions(Chessboard gameBoard) {
        for (ChessPiece[] row : gameBoard.board) {
            for (ChessPiece i : row) {
                if (!i.getColour().equals(this.getColour())) {
                    for (Point s : i.attackableSquares) {
                        allOppositeAttackablePositions.add(new Point(s));
                    }
                }
            }
        }

    }

    @Override
    public String getIcon() {
        return icon;
    }

    public boolean isCheckmate() {
        return checkmate;
    }
}
