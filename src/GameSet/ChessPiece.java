package GameSet;

import GameSet.Chessboard;

import java.awt.*;
import java.util.ArrayList;

public abstract class ChessPiece {
    private String colour = "";
    public boolean checkingKing;
    public Point boardLocation = new Point(); //X is row, Y is column
    public ArrayList<Point> validMoves = new ArrayList<>();
    public ArrayList<Point> attackableSquares = new ArrayList<>();

    public ChessPiece() {

    }

    public ChessPiece(String enteredColour) {
        colour = enteredColour;
    }

    public abstract String getIcon();
    public abstract ArrayList<Point> getPossibleMoves(Chessboard gameBoard);

    public String getColour() {
        return colour;
    }

    public int getRow() {
        return (int)boardLocation.getX();
    }

    public int getColumn() {
        return(int)boardLocation.getY();
    }

    public void setBoardPosition(int row, int column) {
        boardLocation.move(row,column);
    }

    public boolean move(String enteredMove, Chessboard gameBoard) {
        gameBoard.checkPossibleMoves();
        char columnChar = enteredMove.charAt(0);
        char rowChar = enteredMove.charAt(1);
        int endCol = Character.getNumericValue(columnChar) - 10;
        int endRow = Character.getNumericValue(rowChar) - 1 ;
        Point movePoint = new Point(endRow, endCol);
        Point currentPoint = new Point(boardLocation);

        if (validMoves.contains(movePoint)) {

            //Add materiel point system here later
            boardLocation.move(endRow, endCol);
            syncBoardLocation(gameBoard);
            gameBoard.checkPossibleMoves();
            gameBoard.board[currentPoint.x][currentPoint.y] = new EmptySquare();
            return true;
        } else {
            System.out.println("Invalid move, please try again!");
            return false;
        }
    }

    public boolean simulateMove(String enteredMove, Chessboard gameBoard) {
        gameBoard.checkPossibleMoves();
        char columnChar = enteredMove.charAt(0);
        char rowChar = enteredMove.charAt(1);
        int endCol = Character.getNumericValue(columnChar) - 10;
        int endRow = Character.getNumericValue(rowChar) - 1 ;

        Point currentPoint = new Point(boardLocation);

        //Add materiel point system here later
        boardLocation.move(endRow, endCol);
        syncBoardLocation(gameBoard);
        gameBoard.board[currentPoint.x][currentPoint.y] = new EmptySquare();
        gameBoard.checkPossibleMoves();
        return true;

    }

    public void printPossibleMoves() {
        for (Point i: validMoves) {
            System.out.println(i);
        }
    }

    public void syncBoardLocation(Chessboard gameBoard) {
        gameBoard.board[boardLocation.x][boardLocation.y] = this;
    }



}
