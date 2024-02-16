package huzpsb.ll4j.nlp.test;

import huzpsb.ll4j.data.DataEntry;
import huzpsb.ll4j.model.HsmLoader;
import huzpsb.ll4j.model.Model;
import huzpsb.ll4j.nlp.token.Tokenizer;

import java.util.Scanner;

public class PlayGround_7 {
    public static void main(String[] args) throws Exception {
        Tokenizer tokenizer = Tokenizer.loadFromFile("ts.model");
        Model model = HsmLoader.load("gen5x.model");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Playground Started!");
        while (true) {
            String str = scanner.nextLine();
            DataEntry dataEntry = tokenizer.tokenize(0, str);
            System.out.println(model.predict(dataEntry));
        }
    }
}
