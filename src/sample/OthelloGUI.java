package sample;
import szte.mi.Move;


import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class OthelloGUI implements EventHandler<ActionEvent> {
    static Othello board;
    static ArrayList<Othello> arrOthello = new ArrayList<>();
    static ArrayList<OthelloButton> arrButton = new ArrayList<>();
    public static int SIZE = 800;
    public static final int TILE_SIZE = SIZE / 8;
    public static int order = -1;

    public static Circle drawO(Color color) {
        Circle c = new Circle(30);
        c.setFill(color);
        c.setStroke(Color.BLACK);
        return c;
    }


    public static Parent createStartScreen(Stage stage) {
        Button btn1 = new Button();
        btn1.setText("CLICK HERE TO PLAY AS BLACK");


        btn1.setLayoutX(0);
        Button btn2 = new Button();
        btn2.setText("CLICK HERE TO PLAY AS WHITE");


        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                order = 0;
                System.out.println("Human goes first!");
                try {
                    stage.setScene(new Scene(createContent(order)));
                } catch (InterruptedException | IllegalNullMoveException e) {
                    e.printStackTrace();
                }
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                order = 1;
                System.out.println("Computer goes first!");
                try {
                    stage.setScene(new Scene(createContent(order)));
                } catch (InterruptedException | IllegalNullMoveException e) {
                    e.printStackTrace();
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(new Label("Welcome to Othello by Nicholas Lee:"), 0, 0);
        grid.add(btn1, 0, 1);
        grid.add(btn2, 0, 2);
        grid.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, white, gray)");

        return grid;
    }

    public static Parent createStartScreen(Stage stage, Computer computer) {

        Button btn1 = new Button();
        btn1.setText("CLICK HERE TO PLAY AS BLACK");


        btn1.setLayoutX(0);
        Button btn2 = new Button();
        btn2.setText("CLICK HERE TO PLAY AS WHITE");


        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                order = 0;
                System.out.println("Human goes first!");
                try {
                    stage.setScene(new Scene(createContent(order)));
                } catch (InterruptedException | IllegalNullMoveException e) {
                    e.printStackTrace();
                }
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                order = 1;
                System.out.println("Computer goes first!");
                try {
                    stage.setScene(new Scene(createContent(order)));
                } catch (InterruptedException | IllegalNullMoveException e) {
                    e.printStackTrace();
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(new Label("Welcome to Othello by Nicholas Lee:"), 0, 0);
        grid.add(btn1, 0, 1);
        grid.add(btn2, 0, 2);
        grid.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, white, gray)");

        return grid;
    }

    public static Parent createContent(int order) throws InterruptedException, IllegalNullMoveException {

        GridPane root = new GridPane();
        Othello board = new Othello();


        if (order == 0) {
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    OthelloButton button = new OthelloButton(column, row);
                    arrButton.add(button);


                    if ((row + column) % 2 == 0) {
                        button.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #8FBC8F;");
                    } else {
                        button.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #228B22;");
                    }

                    if (order == 0) {
                        board.setCurrentPlayer('X');
                        if (board.gameCells[button.r][button.c].getCh() == 'X') {
                            try {
                                button.setGraphic(drawO(Color.BLACK));
                            } catch (Exception e) {
                            }
                        }
                        if (board.gameCells[button.r][button.c].getCh() == 'O') {
                            try {
                                button.setGraphic(drawO(Color.WHITE));
                            } catch (Exception e) {
                            }
                        }
                        /*
                        if (button.r == 2 && button.c == 4 || button.r == 3 && button.c == 5 ||
                                button.r == 4 && button.c == 2 || button.r == 5 && button.c == 3) {
                            try {
                                button.setGraphic(drawO(Color.GRAY));
                            } catch (Exception e) {
                            }
                        }

                         */
                    }


                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            System.out.println("You pressed the button at (" + (button.r + 1) + "," + (button.c + 1) + ")");
                            int ct = -100;
                            int arr[] = new int[3];
                            Move move = new Move(button.r, button.c);


                            if (order == 0) {
                                char currentPlayer = 'X';
                                ct = board.play(move, order);
                                board.switchPlayer();
                                if (ct == 0) {
                                    arrOthello.add(new Othello(board));


                                    updateBoard(arrOthello);

                                    board.controlElements(arr);
                                    int playerScore = arr[0];
                                    int pcScore = arr[1];
                                    System.out.println("Player Score is: " + playerScore);
                                    System.out.println("PC Score is: " + pcScore);

                                }

                                if (ct == 0 || ct == -1) {

                                    Move computerMove = board.computerPlay(order);
                                    try {
                                        board.playComputerMoveOnBoard(computerMove, order);
                                    } catch (IllegalNullMoveException e) {
                                        e.printStackTrace();
                                    }
                                    board.switchPlayer();


                                    arrOthello.add(new Othello(board));
                                    ArrayList<Integer> arrList = new ArrayList<>();
                                    updateBoard(arrOthello);

                                    board.findLegalMove(arrList, currentPlayer);
                                    for (int j = 0; j < arrList.size(); j += 2) {
                                        try {
                                            arrButton.get(transformNum(arrList.get(j) * 8 + arrList.get(j + 1))).setGraphic(drawO(Color.GRAY));
                                        } catch (Exception ex) {
                                        }
                                    }
                                    board.controlElements(arr);
                                    int playerScore = arr[0];
                                    int pcScore = arr[1];
                                    System.out.println("Player Score: " + playerScore);
                                    System.out.println("PC Score: " + pcScore);
                                } else {
                                    System.out.println("MOVE NOT ALLOWED");

                                }
                                StackPane notedPane = new StackPane();

                                Text displayText = new Text("Player Score: " + arr[0] + "\n" + "PC Score: " + arr[1] + "\n\n" +
                                        "Board Eval: +" + board.evaluateBoard());
                                if (board.evaluateBoard() < 0) {
                                    displayText = new Text("Player Score: " + arr[0] + "\n" + "PC Score: " + arr[1] + "\n\n" +
                                            "Board Eval: " + board.evaluateBoard());
                                }


                                int stop = board.endOfGame();
                                if (stop == 0) {
                                    System.out.println("No legal moves available, Game Over!");
                                } else if (stop == 1 || stop == 3) {
                                    System.out.println("Computer Wins! Game Over!");
                                } else if (stop == 2 || stop == 4) {
                                    System.out.println("You win! Game Over!");
                                } else if (stop == 3) {
                                    System.out.println("Scoreless!");
                                }
                            }
                        }


                    }); //setOnActionEvent end


                    root.add(button, row, column);

                }

            }
        }
        //
        if (order == 1) {

            board.setCurrentPlayer('O');
            char currentPlayer = 'O';
            Move computerMove = board.computerPlay(order);
            board.playComputerMoveOnBoard(computerMove, order);
            board.switchPlayer();
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    OthelloButton button = new OthelloButton(column, row);
                    arrButton.add(button);


                    if ((row + column) % 2 == 0) {
                        button.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #8FBC8F;");
                    } else {
                        button.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #228B22;");
                    }


                    if (board.gameCells[button.r][button.c].getCh() == 'X') {
                        try {
                            button.setGraphic(drawO(Color.BLACK));
                        } catch (Exception e) {
                        }
                    }
                    if (board.gameCells[button.r][button.c].getCh() == 'O') {
                        try {
                            button.setGraphic(drawO(Color.WHITE));
                        } catch (Exception e) {
                        }
                    }

                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            System.out.println("You pressed the button at (" + (button.r + 1) + "," + (button.c + 1) + ")");
                            int ct = -100;
                            int arr[] = new int[3];
                            Move move = new Move(button.r, button.c);

                            ct = board.play(move, order);
                            board.switchPlayer();
                            if (ct == 0) {
                                arrOthello.add(new Othello(board));


                                updateBoard(arrOthello);

                                board.controlElements(arr);
                                int playerScore = arr[0];
                                int pcScore = arr[1];
                                System.out.println("Player Score is: " + playerScore);
                                System.out.println("PC Score is: " + pcScore);

                            }

                            if (ct == 0 || ct == -1) {

                                Move computerMove = board.computerPlay(order);
                                try {
                                    board.playComputerMoveOnBoard(computerMove, order);
                                } catch (IllegalNullMoveException e) {
                                    e.printStackTrace();
                                }
                                board.switchPlayer();

                                arrOthello.add(new Othello(board));
                                ArrayList<Integer> arrList = new ArrayList<>();
                                updateBoard(arrOthello);

                                board.findLegalMove(arrList, currentPlayer);
                                for (int j = 0; j < arrList.size(); j += 2) {
                                    try {
                                        arrButton.get(transformNum(arrList.get(j) * 8 + arrList.get(j + 1))).setGraphic(drawO(Color.GRAY));
                                    } catch (Exception ex) {
                                    }
                                }


                                board.controlElements(arr);
                                int playerScore = arr[0];
                                int pcScore = arr[1];
                                System.out.println("Player Score: " + playerScore);
                                System.out.println("PC Score: " + pcScore);
                            } else {
                                System.out.println("MOVE NOT ALLOWED");

                            }
                            StackPane notedPane = new StackPane();

                            Text displayText = new Text("Player Score: " + arr[1] + "\n" + "PC Score: " + arr[1] + "\n\n" +
                                    "Board Eval: +" + board.evaluateBoard());
                            if (board.evaluateBoard() < 0) {
                                displayText = new Text("Player Score: " + arr[1] + "\n" + "PC Score: " + arr[1] + "\n\n" +
                                        "Board Eval: " + board.evaluateBoard());
                            }


                            int stop = board.endOfGame();
                            if (stop == 0) {
                                System.out.println("No legal moves available, Game Over!");
                            } else if (stop == 1 || stop == 3) {
                                System.out.println("Computer Wins! Game Over!");
                            } else if (stop == 2 || stop == 4) {
                                System.out.println("You win! Game Over!");
                            } else if (stop == 3) {
                                System.out.println("Scoreless!");
                            }

                        }


                    }); //setOnActionEvent end


                    root.add(button, row, column);

                }

            }
        }

        return root;
    }

    private static void updateBoard(ArrayList<Othello> arrOthello) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (arrOthello.get(arrOthello.size() - 1).gameCells[i][j].getCh() == 'X') {
                    try {
                        arrButton.get((j * 8 + i)).setGraphic(drawO(Color.BLACK));
                    } catch (Exception e) {
                    }
                } else if (arrOthello.get(arrOthello.size() - 1).gameCells[i][j].getCh() == 'O') {
                    try {
                        arrButton.get((j * 8 + i)).setGraphic(drawO(Color.WHITE));
                    } catch (Exception e) {
                    }
                } else if (arrOthello.get(arrOthello.size() - 1).gameCells[i][j].getCh() == '.') {
                    try {
                        arrButton.get(j * 8 + i).setGraphic(null);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    public static int transformNum(int num) {
        if (num > 63 || num < 0)
            return -1;
        int divisor, remainder;
        divisor = num / 8;
        remainder = num % 8;
        return remainder * 8 + divisor;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
/*
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Othello by Nicholas Lee");


        board = new Othello();
        arrOthello.add(new Othello(board));

        Scene scene1 = new Scene(createContent(), SIZE, SIZE);

        window.setScene(scene1);
        window.show();
    }

 */

    static class OthelloButton extends Button {
        public int r;
        public int c;
        public int offset = 5;

        public OthelloButton(int r, int c) {
            super();
            this.r = r;
            this.c = c;
            setPrefSize(TILE_SIZE, TILE_SIZE);
        }
    }
}
