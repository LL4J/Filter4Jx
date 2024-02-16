package huzpsb.ll4j.nlp.test;

import huzpsb.ll4j.data.DataSet;
import huzpsb.ll4j.layer.DenseLayer;
import huzpsb.ll4j.layer.JudgeLayer;
import huzpsb.ll4j.layer.LeakyRelu;
import huzpsb.ll4j.model.Model;
import huzpsb.ll4j.nlp.token.Tokenizer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Train_4 {
    public static void main(String[] args) throws Exception {
        Scanner scanner;
        Tokenizer tokenizer = Tokenizer.loadFromFile("ts.model");
        DataSet trainSplit = new DataSet();
        scanner = new Scanner(new File("train.csv"));
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.contains(",")) {
                list.add(str);
            }
        }
        scanner.close();
        String[] strings = list.toArray(new String[0]);
        int len = strings.length;
        // 并不是多此一举：base不一定由全部数据训练而成。
        for (int i = 0; i < len; i++) {
            String[] split = strings[i].split(",");
            trainSplit.split.add(tokenizer.tokenize(Integer.parseInt(split[0]), split[1]));
        }

        Model model = new Model(
                new DenseLayer(65536, 20)
                , new LeakyRelu(20)
                , new DenseLayer(20, 100)
                , new LeakyRelu(100)
                , new DenseLayer(100, 2)
                , new JudgeLayer(2) // MSELoss
        );
        for (int i = 0; i < 128; i++) {
            model.trainOn(trainSplit, 5e-5);
        }
        System.out.println("Base model trained!");
        model.save("gen.model");
    }
}
