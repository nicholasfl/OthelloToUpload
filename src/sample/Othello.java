package sample;

import szte.mi.Move;

import java.util.ArrayList;

public class Othello {
    public final int rows = 8;
    public final int cols = 8;
    public int userCont = 0;
    public int computerCont = 0;
    public Cell gameCells[][];
    public static char currentPlayer;
    public Move lastMove;
    public ArrayList<Move> logMoves;
    public boolean isRunning;


    public Othello() {
        initOthello();
        initStartPosition();
        this.lastMove = null;

    }

    public Othello(Othello obje) {
        int y;
        char c, x;
        gameCells = new Cell[8][8];
        for (int i = 0; i < rows; ++i)
            gameCells[i] = new Cell[8];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                gameCells[i][j] = new Cell();

                c = obje.gameCells[i][j].getCh();
                y = obje.gameCells[i][j].getCorY();
                x = obje.gameCells[i][j].getCorX();
                gameCells[i][j].setPosition(x, c, y);
            }
        }
    }

    public void initOthello(){
        System.out.println("NEW OTHELLO MADE");
        this.logMoves = new ArrayList<>();
        this.isRunning = true;
    }

    public void initStartPosition(){
        int mid;
        mid = rows / 2;
        gameCells = new Cell[8][8];
        for (int i = 0; i < rows; ++i)
            gameCells[i] = new Cell[8];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                gameCells[i][j] = new Cell();
                char c = (char) (97 + j);
                if ((i == mid - 1) && (j == mid - 1)) {
                    gameCells[i][j].setPosition(c, 'X', i + 1);
                } else if ((i == mid - 1) && (j == mid)) {
                    gameCells[i][j].setPosition(c, 'O', i + 1);
                } else if ((i == mid) && (j == mid - 1)) {
                    gameCells[i][j].setPosition(c, 'O', i + 1);
                } else if ((i == mid) && (j == mid)) {
                    gameCells[i][j].setPosition(c, 'X', i + 1);
                } else {
                    gameCells[i][j].setPosition(c, '.', i + 1);
                }
            }
        }
    }
    public void switchPlayer() {
        String initialPlayer = "";
        String otherPlayer = "";
        if(getCurrentPlayer() == 'X') {
            initialPlayer = "X";
            otherPlayer = "O";
        }
        if(getCurrentPlayer() == 'O')
        {
            initialPlayer = "O";
            otherPlayer = "X";
        }
        else{
            ;
        }
        if (getCurrentPlayer() == 'X')
            setCurrentPlayer('O');
        if (getCurrentPlayer() == 'O')
            setCurrentPlayer('X');


        System.out.println("PLAYER SWITCHED!");
        System.out.println("Current Player switched from : " + initialPlayer + " to " + otherPlayer);
    }

    public char getCurrentPlayer(){
        return currentPlayer;
    }

    public void setCurrentPlayer(char c){
        currentPlayer = c;
    }

    public int evaluateBoard() {
        return evaluateBoard(this.gameCells);
    }

    public int evaluateBoard(Cell[][] gameCells) {
        int blackPieces = 0;
        int whitePieces = 0;

        for (Cell[] arr : gameCells) {
            for (Cell cell : arr) {
                if (cell.getCh() == 'X')
                    blackPieces++;
                else if (cell.getCh() == 'O')
                    whitePieces++;
            }
        }

        int cornerBonus = 5;
        int edgeBonus = 1;
        if (gameCells[0][0].getCh() == 'X')
            blackPieces += cornerBonus;
        if (gameCells[0][7].getCh() == 'X')
            blackPieces += cornerBonus;
        if (gameCells[7][0].getCh() == 'X')
            blackPieces += cornerBonus;
        if (gameCells[7][7].getCh() == 'X')
            blackPieces += cornerBonus;

        for (int i = 1; i < 7; i++)
            if (gameCells[i][0].getCh() == 'X') {
                blackPieces += edgeBonus;
            }
        for (int i = 1; i < 7; i++)
            if (gameCells[i][7].getCh() == 'X') {
                blackPieces += edgeBonus;
            }
        for (int i = 1; i < 7; i++)
            if (gameCells[0][i].getCh() == 'X') {
                blackPieces += edgeBonus;
            }
        for (int i = 1; i < 7; i++)
            if (gameCells[7][i].getCh() == 'X') {
                blackPieces += edgeBonus;
            }

        if (gameCells[0][0].getCh() == 'O')
            whitePieces += cornerBonus;
        if (gameCells[0][7].getCh() == 'O')
            whitePieces += cornerBonus;
        if (gameCells[7][0].getCh() == 'O')
            whitePieces += cornerBonus;
        if (gameCells[7][7].getCh() == 'O')
            whitePieces += cornerBonus;

        for (int i = 1; i < 7; i++)
            if (gameCells[i][0].getCh() == 'O') {
                whitePieces += edgeBonus;
            }
        for (int i = 1; i < 7; i++)
            if (gameCells[i][7].getCh() == 'O') {
                whitePieces += edgeBonus;
            }
        for (int i = 1; i < 7; i++)
            if (gameCells[0][i].getCh() == 'O') {
                whitePieces += edgeBonus;
            }
        for (int i = 1; i < 7; i++)
            if (gameCells[7][i].getCh() == 'O') {
                whitePieces += edgeBonus;
            }

        return whitePieces - blackPieces;
    }

    public void findLegalMove(ArrayList<Integer> arr, char currentPlayer) {
        int status;
        int change, max = 0;
        change = 0;

        if(currentPlayer == 'X'){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (gameCells[i][j].getCh() == '.') {
                        int numberOfMoves[] = new int[1];
                        move(i, j, change, 'X', 'O', numberOfMoves);
                        if (numberOfMoves[0] != 0) {
                            arr.add(i);
                            arr.add(j);
                        }
                    }
                }
            }
        }
        if(currentPlayer == 'O'){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (gameCells[i][j].getCh() == '.') {
                        int numberOfMoves[] = new int[1];
                        move(i, j, change, 'O', 'X', numberOfMoves);
                        if (numberOfMoves[0] != 0) {
                            arr.add(i);
                            arr.add(j);
                        }
                    }
                }
            }
        }
    }


    public void playComputerMoveOnBoard(Move move, int order) throws IllegalNullMoveException {
        int[] numberOfMoves = new int[1];
        if (order == 0) {
            move(move, 1, 'O', 'X', numberOfMoves);
        }
        if (order == 1) {
            move(move, 1, 'X', 'O', numberOfMoves);
        }

    }

    public int makeMove(Move move, int change, char char1, char char2, int[] numberOfMoves) {
        return move(move.x, move.y, change, char1, char2, numberOfMoves);
    }

    public Move computerPlay(int order) // play function for computer
    {
        Computer computer = new Computer(this, order);
        int change, max = 0, mX = 0, mY = 0, sum;
        ;
        change = 0;
        int numberOfMoves[] = new int[1];

        if (order == 0) {
            for (int i = 0; i < rows; ++i) // finds best move
            {
                for (int j = 0; j < cols; ++j) {
                    if (gameCells[i][j].getCh() == '.') {
                        move(i, j, change, 'O', 'X', numberOfMoves);
                        if (max < numberOfMoves[0]) {
                            max = numberOfMoves[0];
                            mX = i;
                            mY = j;
                        }
                    }
                }
            }
            computerCont = max;
            if (computerCont == 0) {
                //cout << endl;
                //cout << "Computer Passes(no move possible)\n";
                computerCont = -1;
                return new Move(-1, -1);
            }
            if (computerCont != 0) {
                change = 1;
                //cout << "numberOfMoves : " << sum << endl;

                //move(mX, mY, change, 'O', 'X', numberOfMoves);
            }
            System.out.println("COMPUTER PLAYS:");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.printf("%c", gameCells[i][j].getCh());
                }
                System.out.println("");
            }
            System.out.println("board evaluation: " + evaluateBoard(gameCells));
            //switchPlayer();
            return computer.play(this, 1);
        }

        if (order == 1) {
            for (int i = 0; i < rows; ++i) // finds best move
            {
                for (int j = 0; j < cols; ++j) {
                    if (gameCells[i][j].getCh() == '.') {
                        move(i, j, change, 'X', 'O', numberOfMoves);
                        if (max < numberOfMoves[0]) {
                            max = numberOfMoves[0];
                            mX = i;
                            mY = j;
                        }
                    }
                }
            }
            computerCont = max;
            if (computerCont == 0) {
                //cout << endl;
                //cout << "Computer Passes(no move possible)\n";
                computerCont = -1;
                return new Move(-1, -1);
            }
            if (computerCont != 0) {
                change = 1;
                //cout << "numberOfMoves : " << sum << endl;
                //move(mX, mY, change, 'X', 'O', numberOfMoves);
            }
            System.out.println("COMPUTER PLAYS:");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.printf("%c", gameCells[i][j].getCh());
                }
                System.out.println("");
            }
            System.out.println("board evaluation: " + evaluateBoard(gameCells));

        }
        // switchPlayer();
        return computer.play(this, 2);
    }


    public int play(Move move, int order) {
        int xCor = move.x;
        int yCor = move.y;
        int status;
        int change, max = 0;
        int numberOfMoves[] = new int[1];
        change = 0;

        if (order == 0) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (gameCells[i][j].getCh() == '.') {
                        move(i, j, change, 'X', 'O', numberOfMoves);
                        if (max < numberOfMoves[0])
                            max = numberOfMoves[0];
                    }
                }
            }
            userCont = max;
            if (userCont == 0) {
                userCont = -1;
                return -1;
            }
            if (userCont != 0) {
                change = 1;
                if (!(gameCells[xCor][yCor].getCh() == '.'))
                    return 1;

                status = move(xCor, yCor, change, 'X', 'O', numberOfMoves);

                if (status == -1)
                    return 1;
            }
            System.out.println("YOU PLAYED:");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.printf("%c", gameCells[i][j].getCh());
                }
                System.out.println("");
            }
            System.out.println("board evaluation: " + evaluateBoard(gameCells));
            // switchPlayer();
        }

        if (order == 1) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (gameCells[i][j].getCh() == '.') {
                        move(i, j, change, 'O', 'X', numberOfMoves);
                        if (max < numberOfMoves[0])
                            max = numberOfMoves[0];
                    }
                }
            }
            userCont = max;
            if (userCont == 0) {
                userCont = -1;
                return -1;
            }
            if (userCont != 0) {
                change = 1;
                if (!(gameCells[xCor][yCor].getCh() == '.'))
                    return 1;

                status = move(xCor, yCor, change, 'O', 'X', numberOfMoves);

                if (status == -1)
                    return 1;
            }
            System.out.println("YOU PLAYED:");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.printf("%c", gameCells[i][j].getCh());
                }
                System.out.println("");
            }
            System.out.println("board evaluation: " + evaluateBoard(gameCells));
            //  switchPlayer();
        }
        return 0;
    }

    public boolean isGameOver() {
        return endOfGame() == 0;
    }

    public boolean doesThisPieceExist(char ch, int xCor, int yCor) {
        return gameCells[xCor][yCor].getCh() == ch;

    }

    public int endOfGame() {
        int[] arr = new int[3];
        int cross, circular, point;
        controlElements(arr);
        cross = arr[0];
        circular = arr[1];
        point = arr[2];

        if ((userCont == -1 && computerCont == -1) || point == 0) {
            if (userCont == -1 && computerCont == -1) //No legal move
                return 0;
            if (circular > cross)
                return 1;
            else if (cross > circular)
                return 2;
            else if (cross == 0)
                return 3;
            else if (circular == 0)
                return 4;
            else // scoreless
                return 5;
        }
        return -1;
    }

    public void controlElements(int arr[]) {
        int cross = 0, point = 0, circular = 0;

        int max = 0, numberOfMoves = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (gameCells[i][j].getCh() == 'X')
                    cross++;
                else if (gameCells[i][j].getCh() == 'O')
                    circular++;
                else if (gameCells[i][j].getCh() == '.')
                    point++;
            }
        }
        arr[0] = cross;
        arr[1] = circular;
        arr[2] = point;
    }

    public void reset() {
        int mid = rows / 2;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                char c = (char) (97 + j);
                if ((i == mid - 1) && (j == mid - 1)) {
                    gameCells[i][j].setPosition(c, 'X', i + 1);
                } else if ((i == mid - 1) && (j == mid)) {
                    gameCells[i][j].setPosition(c, 'O', i + 1);
                } else if ((i == mid) && (j == mid - 1)) {
                    gameCells[i][j].setPosition(c, 'O', i + 1);
                } else if ((i == mid) && (j == mid)) {
                    gameCells[i][j].setPosition(c, 'X', i + 1);
                } else {
                    gameCells[i][j].setPosition(c, '.', i + 1);
                }
                System.out.printf("i : %d, j : %d, c : %c\n", i, j, gameCells[i][j].getCh());
            }
        }
    }


    int move(Move move, int change, char char1, char char2, int[] numberOfMoves) throws IllegalNullMoveException {
        if(move.x < 0 || move.y < 0 || move.x > 7 || move.y > 7)
            throw new IllegalNullMoveException();
        int cont, st2 = 0, st = 0;
        int status = -1, corX, corY, temp;
        char str;
        int ix, y, x;

        x = move.x;
        y = move.y;
        numberOfMoves[0] = 0;
        //cout << "x : " << x << ", y: " << y << endl;
        if ((x + 1 < rows) && (gameCells[x + 1][y].getCh() == char2)) //asagi
        {
            //cout << "deneme\n";
            cont = x;
            while ((cont < rows) && (st2 != -1) && (st != 2)) {
                cont++;
                if (cont < rows) {
                    if (gameCells[cont][y].getCh() == char2)
                        st = 1;
                    else if (gameCells[cont][y].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = cont - x - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = x; i < cont; ++i) {
                    str = gameCells[i][y].getCorX();
                    ix = gameCells[i][y].getCorY();
                    gameCells[i][y].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x - 1 >= 0) && (gameCells[x - 1][y].getCh() == char2)) //yukari
        {
            //cout << "deneme2\n";
            cont = x;
            while ((cont >= 0) && (st2 != -1) && (st != 2)) {
                cont--;
                if (cont >= 0) {
                    if (gameCells[cont][y].getCh() == char2)
                        st = 1;
                    else if (gameCells[cont][y].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = x - cont - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = cont; i <= x; ++i) {
                    str = gameCells[i][y].getCorX();
                    ix = gameCells[i][y].getCorY();
                    gameCells[i][y].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((y + 1 < cols) && (gameCells[x][y + 1].getCh() == char2)) //sag
        {
            //cout << "deneme3\n";
            cont = y;
            while ((cont < cols) && (st2 != -1) && (st != 2)) {
                cont++;
                if (cont < cols) {
                    if (gameCells[x][cont].getCh() == char2)
                        st = 1;
                    else if (gameCells[x][cont].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = cont - y - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = y; i < cont; ++i) {
                    str = gameCells[x][i].getCorX();
                    ix = gameCells[x][i].getCorY();
                    gameCells[x][i].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((y - 1 >= 0) && (gameCells[x][y - 1].getCh() == char2)) //sol
        {
            //cout << "deneme4\n";
            cont = y;
            while ((cont >= 0) && (st2 != -1) && (st != 2)) {
                cont--;
                if (cont >= 0) {
                    if (gameCells[x][cont].getCh() == char2)
                        st = 1;
                    else if (gameCells[x][cont].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = y - cont - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = cont; i <= y; ++i) {
                    str = gameCells[x][i].getCorX();
                    ix = gameCells[x][i].getCorY();
                    gameCells[x][i].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x - 1 >= 0) && (y + 1 < cols) && (gameCells[x - 1][y + 1].getCh() == char2)) //sag yukari
        {
            //cout << "deneme5\n";
            corY = y;
            corX = x;
            while ((corX >= 0) && (corY < cols) && (st2 != -1) && (st != 2)) {
                corX--;
                corY++;
                if ((corX >= 0) && (corY < cols)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = x - corX - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                while ((corX <= x) && (y < corY)) {
                    corX++;
                    corY--;
                    //cout << "corX : " << corX << ", corY : " << corY << endl << endl;
                    if ((corX <= x) && (y <= corY)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x - 1 >= 0) && (y - 1 >= 0) && (gameCells[x - 1][y - 1].getCh() == char2)) //sol yukari
        {
            //cout << "deneme6\n";
            corY = y;
            corX = x;
            while ((corX >= 0) && (corY >= 0) && (st2 != -1) && (st != 2)) {
                corX--;
                corY--;
                if ((corX >= 0) && (corY >= 0)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = x - corX - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                //cout << "corX : " << corX << ", corY : " << corY << endl << endl;
                while ((corX <= x) && (corY <= y)) {
                    corX++;
                    corY++;
                    if ((corX <= x) && (corY <= y)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x + 1 < rows) && (y + 1 < cols) && (gameCells[x + 1][y + 1].getCh() == char2)) //sag asagi
        {
            //cout << "deneme7\n";
            corY = y;
            corX = x;
            while ((corX < rows) && (corY < cols) && (st2 != -1) && (st != 2)) {
                corX++;
                corY++;
                if ((corX < rows) && (corY < cols)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = corX - x - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                while ((corX >= x) && (corY >= y)) {
                    corX--;
                    corY--;
                    //cout << "corX : " << corX << ", corY : " << corY << endl << endl;
                    if ((corX >= x) && (corY >= y)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x + 1 < rows) && (y - 1 >= 0) && (gameCells[x + 1][y - 1].getCh() == char2)) //sol asagi
        {
            //cout << "deneme8\n";
            corY = y;
            corX = x;
            while ((corX < rows) && (corY >= 0) && (st2 != -1) && (st != 2)) {
                corX++;
                corY--;
                if ((corX < rows) && (corY >= 0)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = corX - x - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                while ((corX >= x) && (corY <= y)) {
                    corX--;
                    corY++;
                    //cout << "corX : " << corX << ", corY : " << corY << endl << endl;
                    if ((corX >= x) && (corY <= y)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if (status == 0)
            return 0;
        else
            return -1;
    }

    int move(int xCor, int yCor, int change, char char1, char char2, int[] numberOfMoves) {
        int cont, st2 = 0, st = 0;
        int status = -1, corX, corY, temp;
        char str;
        int ix, y, x;

        x = xCor;
        y = yCor;
        numberOfMoves[0] = 0;

        if ((x + 1 < rows) && (gameCells[x + 1][y].getCh() == char2)) {

            cont = x;
            while ((cont < rows) && (st2 != -1) && (st != 2)) {
                cont++;
                if (cont < rows) {
                    if (gameCells[cont][y].getCh() == char2)
                        st = 1;
                    else if (gameCells[cont][y].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = cont - x - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = x; i < cont; ++i) {
                    str = gameCells[i][y].getCorX();
                    ix = gameCells[i][y].getCorY();
                    gameCells[i][y].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x - 1 >= 0) && (gameCells[x - 1][y].getCh() == char2)) {

            cont = x;
            while ((cont >= 0) && (st2 != -1) && (st != 2)) {
                cont--;
                if (cont >= 0) {
                    if (gameCells[cont][y].getCh() == char2)
                        st = 1;
                    else if (gameCells[cont][y].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = x - cont - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = cont; i <= x; ++i) {
                    str = gameCells[i][y].getCorX();
                    ix = gameCells[i][y].getCorY();
                    gameCells[i][y].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((y + 1 < cols) && (gameCells[x][y + 1].getCh() == char2)) //sag
        {

            cont = y;
            while ((cont < cols) && (st2 != -1) && (st != 2)) {
                cont++;
                if (cont < cols) {
                    if (gameCells[x][cont].getCh() == char2)
                        st = 1;
                    else if (gameCells[x][cont].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = cont - y - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = y; i < cont; ++i) {
                    str = gameCells[x][i].getCorX();
                    ix = gameCells[x][i].getCorY();
                    gameCells[x][i].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((y - 1 >= 0) && (gameCells[x][y - 1].getCh() == char2)) {

            cont = y;
            while ((cont >= 0) && (st2 != -1) && (st != 2)) {
                cont--;
                if (cont >= 0) {
                    if (gameCells[x][cont].getCh() == char2)
                        st = 1;
                    else if (gameCells[x][cont].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = y - cont - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                for (int i = cont; i <= y; ++i) {
                    str = gameCells[x][i].getCorX();
                    ix = gameCells[x][i].getCorY();
                    gameCells[x][i].setPosition(str, char1, ix);
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x - 1 >= 0) && (y + 1 < cols) && (gameCells[x - 1][y + 1].getCh() == char2)) //sag yukari
        {

            corY = y;
            corX = x;
            while ((corX >= 0) && (corY < cols) && (st2 != -1) && (st != 2)) {
                corX--;
                corY++;
                if ((corX >= 0) && (corY < cols)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = x - corX - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                while ((corX <= x) && (y < corY)) {
                    corX++;
                    corY--;
                    //cout << "corX : " << corX << ", corY : " << corY << endl << endl;
                    if ((corX <= x) && (y <= corY)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x - 1 >= 0) && (y - 1 >= 0) && (gameCells[x - 1][y - 1].getCh() == char2)) {

            corY = y;
            corX = x;
            while ((corX >= 0) && (corY >= 0) && (st2 != -1) && (st != 2)) {
                corX--;
                corY--;
                if ((corX >= 0) && (corY >= 0)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = x - corX - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {

                while ((corX <= x) && (corY <= y)) {
                    corX++;
                    corY++;
                    if ((corX <= x) && (corY <= y)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x + 1 < rows) && (y + 1 < cols) && (gameCells[x + 1][y + 1].getCh() == char2)) {

            corY = y;
            corX = x;
            while ((corX < rows) && (corY < cols) && (st2 != -1) && (st != 2)) {
                corX++;
                corY++;
                if ((corX < rows) && (corY < cols)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = corX - x - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                while ((corX >= x) && (corY >= y)) {
                    corX--;
                    corY--;

                    if ((corX >= x) && (corY >= y)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if ((x + 1 < rows) && (y - 1 >= 0) && (gameCells[x + 1][y - 1].getCh() == char2)) //sol asagi
        {

            corY = y;
            corX = x;
            while ((corX < rows) && (corY >= 0) && (st2 != -1) && (st != 2)) {
                corX++;
                corY--;
                if ((corX < rows) && (corY >= 0)) {
                    if (gameCells[corX][corY].getCh() == char2)
                        st = 1;
                    else if (gameCells[corX][corY].getCh() == char1)
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2) {
                temp = corX - x - 1;
                numberOfMoves[0] += temp;
            }
            if (st == 2 && change == 1) {
                while ((corX >= x) && (corY <= y)) {
                    corX--;
                    corY++;

                    if ((corX >= x) && (corY <= y)) {
                        str = gameCells[corX][corY].getCorX();
                        ix = gameCells[corX][corY].getCorY();
                        gameCells[corX][corY].setPosition(str, char1, ix);
                    }
                }
                status = 0;
            }
            st = 0;
            st2 = 0;
        }
        if (status == 0)
            return 0;
        else
            return -1;
    }
}


