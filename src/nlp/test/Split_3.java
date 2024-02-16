package huzpsb.ll4j.nlp.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Split_3 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("wash.csv"));
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.length() > 2) {
                list.add(str);
            }
        }
        sc.close();
        String[] arr = list.toArray(new String[0]);
        int len = arr.length;
        int trainLen = (int) (len * 0.8);
        PrintWriter pw = new PrintWriter("train.csv");
        for (int i = 0; i < trainLen; i++) {
            pw.println(arr[i]);
        }
        pw.close();
        pw = new PrintWriter("test.csv");
        for (int i = trainLen; i < len; i++) {
            pw.println(arr[i]);
        }
        pw.close();
    }
}
