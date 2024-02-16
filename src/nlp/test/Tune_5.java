package huzpsb.ll4j.nlp.test;

import huzpsb.ll4j.data.DataSet;
import huzpsb.ll4j.model.HsmLoader;
import huzpsb.ll4j.model.Model;
import huzpsb.ll4j.nlp.token.Tokenizer;

import java.io.File;
import java.util.Scanner;

public class Tune_5 {
    public static void main(String[] args) throws Exception {
//        Scanner scanner;
        Tokenizer tokenizer = Tokenizer.loadFromFile("ts.model");
//        scanner = new Scanner(new File("train.csv"));
//        List<String> list = new ArrayList<>();
//        while (scanner.hasNextLine()) {
//            String str = scanner.nextLine();
//            if (str.contains(",")) {
//                list.add(str);
//            }
//        }
//        scanner.close();
//        String[] strings = list.toArray(new String[0]);
//        for (int i = 5000; i < 40000; i++) {
//            String[] split = strings[i].split(",");
//            trainSplit.split.add(tokenizer.tokenize(Integer.parseInt(split[0]), split[1]));
//        }
        Scanner sc = new Scanner(new File("out.txt"));
        Model model = HsmLoader.load("gen5.model");
        int i = 0;
        while (sc.hasNextLine()) {
            i++;
            DataSet trainSplit = new DataSet();
            trainSplit.split.add(tokenizer.tokenize(0, sc.nextLine()));
            model.trainOn(trainSplit, 1e-7);
            if (i % 1000 == 0) {
                int t = i / 1000;
                System.out.println("t: " + t);
                if (t == 100) {
                    break;
                }
            }
        }
        System.out.println("Tuned!");
        model.save("gen5x.model");
    }
}
