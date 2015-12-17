package main;

/**
 * Created by Kunzetsov Yaroslav
 */
public class Lexem {
    private int start;
    private int end;
    private String expression;

    public Lexem(int start, int end, String expression) {
        setStart(start);
        setEnd(end);
        setExpression(expression);
    }
    public Lexem(){}

    public String getExpression(){
        return this.expression;
    }
    public int getStart(){
        return this.start;
    }
    public int getEnd(){
        return this.end;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
