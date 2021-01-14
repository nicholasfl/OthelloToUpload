package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    private int corY;
    private char corX;
    private char ch;


    Cell() {}
    char getCorX()
    {
        return corX;
    }
    int getCorY()
    {
        return corY;
    }
    char getCh()
    {
        return ch;
    }



    void setPosition(char x, char c, int y)
    {
        corX = x;
        corY = y;
        ch = c;
    }
}
