package lt.karpovic.fileservice.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Word {

    String wordValue;

    AtomicInteger counter = new AtomicInteger(0);

    public Word(String wordValue) {
        this.wordValue = wordValue;
        incrementCounter();
    }

    public String getWordValue() {
        return wordValue;
    }

    public int getCounter() {
        return counter.get();
    }

    public void incrementCounter() {
        counter.incrementAndGet();
    }
}
