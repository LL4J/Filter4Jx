package huzpsb.ll4j.nlp.token;

import java.util.*;

public class TokenizerBuilder {
    private final int outputSize;
    private final Map<String, Integer> occurrences = new HashMap<>();


    public TokenizerBuilder(int outputSize) {
        this.outputSize = outputSize;
    }

    public void update(String text) {
        int length = text.length();
        Set<String> occur = new HashSet<>();

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (j - i > 5) break;
                occur.add(text.substring(i, j));
            }
        }

        for (String s : occur) {
            updateVocab(s);
        }
    }

    private void updateVocab(String text) {
        if (occurrences.containsKey(text)) {
            occurrences.put(text, occurrences.get(text) + 1);
        } else {
            occurrences.put(text, 1);
        }
    }

    public Tokenizer build() {
        String[] vocab = new String[outputSize];
        PriorityQueue<Entry> queue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            double weight = value * Math.log(key.length() + 10);
            queue.add(new Entry(key, weight));
        }
        for (int i = 0; i < outputSize; i++) {
            vocab[i] = queue.poll().key;
        }
        return new Tokenizer(vocab);
    }
}

class Entry implements Comparable<Entry> {
    String key;
    double value;

    public Entry(String key, double value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Entry o) {
        return Double.compare(o.value, value);
    }
}
