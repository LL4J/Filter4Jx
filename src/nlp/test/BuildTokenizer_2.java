package huzpsb.ll4j.nlp.test;

import huzpsb.ll4j.nlp.token.Tokenizer;
import huzpsb.ll4j.nlp.token.TokenizerBuilder;

import java.io.File;
import java.util.Scanner;

public class BuildTokenizer_2 {
    public static void main(String[] args) throws Exception {
        TokenizerBuilder tb = new TokenizerBuilder(65536);
        Scanner scanner = new Scanner(new File("wash.csv"));
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(",");
            if (split.length == 2) {
                tb.update(split[1]);
            }
        }
        Tokenizer t1 = tb.build();
        t1.saveToFile("ts.model");
    }
}
