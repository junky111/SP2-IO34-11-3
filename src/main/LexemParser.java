package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuznetsov Yaroslav
 */
public class LexemParser {
    private String parseInput;
    private String [] reservedWords ={"and","asm", "array", "begin", "case", "const", "constructor", "destructor", "div", "do", "downto", "else", "end", "exports", "file", "for", "function", "goto", "if", "implementation", "inherited", "inline", "interface", "label", "library", "mod", "nil", "not", "object", "of", "or", "packed", "procedure", "program", "record", "repeat", "set", "shl", "shr", "string", "then", "to", "type", "unit", "until", "uses", "var", "while", "with", "xor"};
    private String [] reservedSymbols = {":=", "=", "-", "+", "/", "*", "<>"};
    private List<Lexem> listRes = new ArrayList<Lexem>();

    public LexemParser(String parseInput) {
        this.parseInput = parseInput;
    }


    public List<Lexem> resWordsSearch (Lexem [] parseInput){
        List<Lexem> list = new ArrayList<Lexem>();
        boolean reserved = false;
        for (int i = 0; i < parseInput.length; i++){
            list.add(parseInput[i]);
            for (int j = 0; j < getReservedWords().length; j++){
                reserved = list.get(list.size()-1).getExpression().equalsIgnoreCase(getReservedWords()[j]);
                if (reserved == true){
                    getListRes().add(list.get(list.size() -1));
                    list.remove(list.size()-1);
                    j = getReservedWords().length;
                }
            }
        }
        return list;
    }


    public Lexem[] lexemSplit (String parsStr, List<Integer> list){
        Lexem[] arr = new Lexem[list.size()+1];
        int lastPosition = list.get(list.size()-1);
        int startPosition = 0;
        int i = 0;
        while (startPosition < lastPosition){
            int end = list.get(i);
            String str = parsStr.substring(startPosition, end);
            arr [i] = new Lexem(startPosition,end,str);
            startPosition = end + 1; i ++;
        }
        String str = parsStr.substring(startPosition, parsStr.length());
        arr [list.size()] = new Lexem(startPosition, parsStr.length(), str);
        this.expressionsToString(arr);
        return arr;
    }


    public List<Integer> parseWSpace (String parseInput){
        char space = ' ';
        int fromIndex = 0;
        int lastSpacePosition = parseInput.lastIndexOf(space);
        List<Integer> spaceList = new ArrayList<Integer>();
        while (fromIndex < lastSpacePosition) {
            spaceList.add(parseInput.indexOf(space, fromIndex));
            fromIndex = parseInput.indexOf(space, fromIndex) + 1;
        }
        return spaceList;
    }

    public void symbolSearch (Lexem expression) {
        List<Integer> listStr = new ArrayList<Integer>();
        int startPosition = 0;
        for (int i = 0; i < getReservedSymbols().length; i++) {
            startPosition = expression.getExpression().indexOf(getReservedSymbols()[i], startPosition);
            if (startPosition != -1) {
                listStr.add(startPosition);
                startPosition += getReservedSymbols()[i].length();
            }
        }
        startPosition = 0;
        int lastPosition = 0;
        if (!listStr.isEmpty()) {
            lastPosition = listStr.get(0);
        }
        getListRes().add(new Lexem(startPosition + expression.getStart(), lastPosition + expression.getStart() + 1, expression.getExpression().substring(startPosition, lastPosition)));
        for (int i = 0; i < listStr.size() - 1; i++) {
            startPosition = listStr.get(i);
            getListRes().add(new Lexem(listStr.get(i) + expression.getStart(), listStr.get(i) + expression.getStart() + 1, expression.getExpression().substring(listStr.get(i), listStr.get(i) + 1)));
            startPosition = startPosition + 1;
            lastPosition = listStr.get(i + 1);
            getListRes().add(new Lexem(startPosition + expression.getStart(), lastPosition + expression.getStart() + 1, expression.getExpression().substring(startPosition, lastPosition)));
        }
        if (lastPosition != 0) {
            getListRes().add(new Lexem(startPosition + expression.getStart(), lastPosition + expression.getStart() + 1, expression.getExpression().substring(listStr.get(listStr.size() - 1), expression.getExpression().length())));
        }
    }

    public void expressionsToString(Lexem[] array){
        for (Lexem  expression : array ){
            System.out.println("______" +  expression.getExpression().toString());
        }
    }
    public static Lexem [] sortLexems(Lexem[] list){
        Lexem template  =  new Lexem();
        for (int i = 0; i < list.length; i++) {
            int minElement = list[i].getStart();  int minIndex = i;
            for (int j = i+1; j < list.length; j++) {
                if (list[j].getStart() < minElement)
                    minElement = list[j].getStart();    minIndex = j;
            }
            if (i != minIndex) {
                template = list[i];
                list[i] = list[minIndex];
                list[minIndex] = template;
            }
        }
        return list;
    }

    public void setParseInput(String parseInput) {
        this.parseInput = parseInput;
    }

    public String getParseInput() {
        return parseInput;
    }

    public String[] getReservedWords() {
        return reservedWords;
    }

    public void setReservedWords(String[] reservedWords) {
        this.reservedWords = reservedWords;
    }

    public String[] getReservedSymbols() {
        return reservedSymbols;
    }

    public void setReservedSymbols(String[] reservedSymbols) {
        this.reservedSymbols = reservedSymbols;
    }

    public List<Lexem> getListRes() {
        return listRes;
    }

    public void setListRes(List<Lexem> listRes) {
        this.listRes = listRes;
    }
}
