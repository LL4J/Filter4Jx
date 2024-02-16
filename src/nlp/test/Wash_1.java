package huzpsb.ll4j.nlp.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Wash_1 {
    // 对数据进行清洗标注。0-合规，1-违规。

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("COLD.csv"));
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split(",");
            try {
                if (split.length == 2) {
                    int type = Integer.parseInt(split[0]);
                    if (type == 0 || type == 1) {
                        list.add(type + "," + split[1]);
                    }
                }
            } catch (Exception e) {
                System.out.println("error line: " + split[0]);
            }
        }
        sc.close();
        sc = new Scanner(new File("keywords.txt"));
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.length() > 1 && !next.contains(",")) {
                list.add("1," + next);
            }
        }
        sc.close();
        Collections.shuffle(list);
        PrintWriter pw = new PrintWriter("wash.csv");
        for (String s : list) {
            pw.println(s);
        }
        pw.close();
    }
}
