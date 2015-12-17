package main;

/**
 * Created by Kuznetsov Yaroslav
 */

public class Table {
    private int id;
    private String str;
    private int nextId;

    Table(int id, String str, int nextId) {
        this.id = id;
        this.str = str;
        this.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public String getStr() {
        return str;
    }

    public int getNextId() {
        return nextId;
    }
    public void setStr (String str){
        this.str = str;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public void setId (int id){
        this.id = id;
    }
    public void toString (Table table){
        System.out.println(String.format("%5d",table.id) + String.format("%14s",table.str) + String.format("%8d",table.nextId));
    }
}
