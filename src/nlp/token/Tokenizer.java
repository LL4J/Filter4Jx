package huzpsb.ll4j.nlp.token;

import huzpsb.ll4j.data.DataEntry;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Tokenizer {
    private final String[] vocab;

    public Tokenizer(String[] vocab) {
        this.vocab = vocab;
    }

    public static Tokenizer loadFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            int size = Integer.parseInt(scanner.nextLine());
            String[] vocab = new String[size];
            for (int i = 0; i < size; i++) {
                vocab[i] = scanner.nextLine();
            }
            return new Tokenizer(vocab);
        } catch (Exception ignored) {
        }
        return null;
    }

    public DataEntry tokenize(int type, String text) {
        double[] values = new double[vocab.length];
        for (int i = 0; i < values.length; i++) {
            if (text.contains(vocab[i])) {
                values[i] = 1;
            }
        }
        return new DataEntry(type, values);
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(vocab.length);
            for (String word : vocab) {
                writer.println(word);
            }
        } catch (Exception ignored) {
        }
    }
}
