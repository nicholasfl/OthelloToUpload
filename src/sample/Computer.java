package sample;

import szte.mi.Move;

import java.util.ArrayList;
import java.util.Random;

public class Computer implements szte.mi.Player {

    public Othello currentBoard;
    public int order;
    public Random rnd;
    Move myLastMove;
    boolean firstMove;
    int movesPlayed;
    ArrayList<Move> legalMoves;
    public long t;

    public Computer(Othello currentBoard, int order) {
        this.currentBoard = currentBoard;
        this.order = order;
    }

    @Override
    public void init(int order, long t, Random rnd) {
        this.currentBoard = new Othello();
        this.rnd = rnd;
        this.t = t;
        this.movesPlayed = 0;
        this.order = order;
        firstMove = (order == 0);
        // true if order = 1 since computer plays first
        // false if order = 0 since human plays first
    }


    @Override
    public Move nextMove(Move prevMove, long tOpponent, long t) {
        if(t < 0 || tOpponent < 0)
            return null;
        else {
            int change, max = 0, mX = 0, mY = 0, sum;
            change = 0;
            if(order == 1) {
                int numberOfMoves[] = new int[1];
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (currentBoard.gameCells[i][j].getCh() == '.') {
                            currentBoard.move(i, j, change, 'O', 'X', numberOfMoves);
                            if (max < numberOfMoves[0]) {
                                max = numberOfMoves[0];
                                mX = i;
                                mY = j;
                            }
                        }
                    }

                }
                currentBoard.computerCont = max;
                if (currentBoard.computerCont == 0) {
                    currentBoard.computerCont = -1;
                    // return -1;
                }
                if (currentBoard.computerCont != 0) {
                    change = 1;
                    currentBoard.move(mX, mY, change, 'O', 'X', numberOfMoves);
                }

                return new Move(mX, mY);
            }
            if(order == 2){
                int numberOfMoves[] = new int[1];
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (currentBoard.gameCells[i][j].getCh() == '.') {
                            currentBoard.move(i, j, change, 'X', 'O', numberOfMoves);
                            if (max < numberOfMoves[0]) {
                                max = numberOfMoves[0];
                                mX = i;
                                mY = j;
                            }
                        }
                    }

                }
                currentBoard.computerCont = max;
                if (currentBoard.computerCont == 0) {
                    currentBoard.computerCont = -1;
                    // return -1;
                }
                if (currentBoard.computerCont != 0) {
                    change = 1;
                    currentBoard.move(mX, mY, change, 'O', 'X', numberOfMoves);
                }

                return new Move(mX, mY);
            }


        }
        return new Move(-1,-1);

    }



    public Move play(Othello currentBoard, int order) {
        int change, max = 0, mX = 0, mY = 0, sum;
        change = 0;
        if(order == 1) {
            int numberOfMoves[] = new int[1];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (currentBoard.gameCells[i][j].getCh() == '.') {
                        currentBoard.move(i, j, change, 'O', 'X', numberOfMoves);
                        if (max < numberOfMoves[0]) {
                            max = numberOfMoves[0];
                            mX = i;
                            mY = j;
                        }
                    }
                }

            }
            currentBoard.computerCont = max;
            if (currentBoard.computerCont == 0) {
                currentBoard.computerCont = -1;
                // return -1;
            }
            if (currentBoard.computerCont != 0) {
                change = 1;
                currentBoard.move(mX, mY, change, 'O', 'X', numberOfMoves);
            }

            return new Move(mX, mY);
        }
        if(order == 2){
            int numberOfMoves[] = new int[1];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (currentBoard.gameCells[i][j].getCh() == '.') {
                        currentBoard.move(i, j, change, 'X', 'O', numberOfMoves);
                        if (max < numberOfMoves[0]) {
                            max = numberOfMoves[0];
                            mX = i;
                            mY = j;
                        }
                    }
                }

            }
            currentBoard.computerCont = max;
            if (currentBoard.computerCont == 0) {
                currentBoard.computerCont = -1;
                // return -1;
            }
            if (currentBoard.computerCont != 0) {
                change = 1;
                currentBoard.move(mX, mY, change, 'O', 'X', numberOfMoves);
            }

            return new Move(mX, mY);
        }

        return new Move(-1,-1);
    }
}


