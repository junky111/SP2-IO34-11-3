package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kunzetsov Yaroslav
 */
public class Main {
    public static void main(String[] args) {
        String input = "while n<>0 do begin n:=n-1; b:=b+a[n] end;";
        LexemParser lexemParser = new LexemParser(input);
        List<Lexem> expressions = lexemParser.resWordsSearch(lexemParser.lexemSplit(input, lexemParser.parseWSpace(input)));
        List<Table> symbExpressions = new ArrayList<Table>();
        for (int i=0; i < expressions.size(); i++){
            lexemParser.symbolSearch(expressions.get(i));
        }
        Lexem [] lexemList = new Lexem[lexemParser.getListRes().size()];
        for (int i = 0; i < lexemParser.getListRes().size(); i++){
            lexemList[i] =  lexemParser.getListRes().get(i);
        }
        lexemList = LexemParser.sortLexems(lexemList);
        Table [] tables = new Table[lexemList.length];

        for (int i=0; i < lexemList.length; i++){
            tables[i] = new Table(i,lexemList[i].getExpression(), i+1);
            symbExpressions.add(tables[i]);
            tables[i].toString(tables[i]);
        }
    }
}
