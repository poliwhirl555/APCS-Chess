package GameSet;

public class Chessboard {
    public ChessPiece[][] board = new ChessPiece[8][8];

    //Make private later

    public Chessboard() {
        ChessPiece[][] defaultBoard = {
                {new Rook("white"), new Knight("white"), new Bishop("white"),
                        new King("white"), new Queen("white"), new Bishop("white"), new Knight("white"),
                        new Rook("white")},

                {new Pawn("white"), new Pawn("white"), new Pawn("white"),  new Pawn("white"),
                        new Pawn("white"), new Pawn("white"), new Pawn("white"), new Pawn("white")},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), 
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new Pawn("black"), new Pawn("black"), new Pawn("black"),  new Pawn("black"),
                        new Pawn("black"), new Pawn("black"), new Pawn("black"), new Pawn("black")},

                {new Rook("black"), new Knight("black"), new Bishop("black"),
                        new King("black"), new Queen("black"), new Bishop("black"), new Knight("black"),
                        new Rook("black")},
        };

        board = defaultBoard;

        setPiecePositions();

    }

//    public Chessboard(Chessboard b) {
//        board = b.board;
//        setPiecePositions();
//    }

    private void setPiecePositions() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                board[row][column].setBoardPosition(row, column);
            }
        }
    }

    public void drawBoardBlackSide() {
        int count = 1;
        for (int i = 0; i < 8; i++) {
            String rowPrint = "";

            for (int k = 7; k > -1; k--) {
                rowPrint += board[i][k].getIcon();
                rowPrint += " ";
            }

            rowPrint += count;
            count++;

            System.out.println(rowPrint);
        }

        System.out.println("h  g  f  e  d  c  b  a");
    }

    public void drawBoardWhiteSide() {
        for (int i = 7; i > -1; i--) {
            String rowPrint = "";
            for (int k = 0; k < 8; k++) {
                rowPrint += board[i][k].getIcon();
                rowPrint += " ";
            }

            rowPrint += i + 1;

            System.out.println(rowPrint);
        }

        System.out.println("a  b  c  d  e  f  g  h");
    }

    public void checkPossibleMoves() {
        for (ChessPiece[] row : board) {
            for (ChessPiece i : row) {
                i.getPossibleMoves(this);
            }
        }
    }

    public boolean blocksCheckmate(int pieceRow, int pieceCol, King checkedKing, String endLocation, String startLocation) {
        char columnChar = endLocation.charAt(0);
        char rowChar = endLocation.charAt(1);
        int endCol = Character.getNumericValue(columnChar) - 10;
        int endRow = Character.getNumericValue(rowChar) - 1 ;
        board[pieceRow][pieceCol].simulateMove(endLocation,this);
        checkPossibleMoves();
        if (checkedKing.inCheck(this)) {
            board[endRow][endCol].simulateMove(startLocation,this);
            checkPossibleMoves();
            return false;
        } else {
            board[endRow][endCol].simulateMove(startLocation,this);
            checkPossibleMoves();
            return true;
        }

    }


    public void setUpSampleBoard() {
        ChessPiece[][] sampleBoard = {
                {new Rook("white"), new Knight("white"), new Bishop("white"),
                        new Queen("white"), new King("white"), new Bishop("white"), new Knight("white"),
                        new Rook("white")},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(), new EmptySquare(),
                        new EmptySquare(), new EmptySquare(), new EmptySquare()},

                {new Rook("black"), new Knight("black"), new Bishop("black"),
                        new Queen("black"), new King("black"), new Bishop("black"), new Knight("black"),
                        new Rook("black")},
        };

        board = sampleBoard;

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                board[row][column].setBoardPosition(row, column);
            }
        }
    }

}



