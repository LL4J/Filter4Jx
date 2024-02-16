package huzpsb.ll4j.nlp.test;

import huzpsb.ll4j.data.DataSet;
import huzpsb.ll4j.model.HsmLoader;
import huzpsb.ll4j.model.Model;
import huzpsb.ll4j.nlp.token.Tokenizer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test_6 {
    public static void main(String[] args) throws Exception {
        Scanner scanner;
        Tokenizer tokenizer = Tokenizer.loadFromFile("ts.model");
        DataSet testSplit = new DataSet();
        scanner = new Scanner(new File("test.csv"));
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.contains(",")) {
                list.add(str);
            }
        }
        scanner.close();
        String[] strings = list.toArray(new String[0]);
        for (String str : strings) {
            String[] split = str.split(",");
            if (split.length == 2) {
                testSplit.split.add(tokenizer.tokenize(Integer.parseInt(split[0]), split[1]));
            }
        }

        Model model = HsmLoader.load("gen5x.model");
        model.testOn(testSplit);
    }
}
