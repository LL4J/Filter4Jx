package huzpsb.ll4j.nlp.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Wash_DMSC {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("DMSC.csv"));
        PrintWriter content = new PrintWriter("out.txt");
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split(",");
            if (split.length == 10) {
                content.println(split[8]);
            }
        }
        content.close();
    }
}
