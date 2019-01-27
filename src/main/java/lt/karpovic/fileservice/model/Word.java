package lt.karpovic.fileservice.model;

public class Word {

    String wordValue;

    int counter = 0;

    public Word(String wordValue) {
        this.wordValue = wordValue;
        incrementCounter();
    }

    public String getWordValue() {
        return wordValue;
    }

    public void setWordValue(String wordValue) {
        this.wordValue = wordValue;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter++;
    }
}
