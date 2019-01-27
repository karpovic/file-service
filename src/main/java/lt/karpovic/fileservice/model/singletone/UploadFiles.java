package lt.karpovic.fileservice.model.singletone;

import lt.karpovic.fileservice.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class UploadFiles {
    private static UploadFiles instance;
    private List<String> filesName = new ArrayList<>();

    private Map<String, Word> wordsMap = new ConcurrentSkipListMap<>();

    private UploadFiles() {
    }

    public static synchronized UploadFiles getInstance() {
        if (instance == null) {
            instance = new UploadFiles();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public void addFileNameToList(String fileName) {
        if (fileName != null) {
            filesName.add(fileName);
        }
    }

    public int getNumberOfFiles() {
        return filesName.size();
    }

    public Map<String, Word> getWordsMap() {
        return wordsMap;
    }

    public void addWord(Word word) {
        wordsMap.put(word.getWordValue(), word);
    }

    public int getNumberOfWords() {
        return wordsMap.size();
    }
}
