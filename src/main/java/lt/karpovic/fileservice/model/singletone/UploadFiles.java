package lt.karpovic.fileservice.model.singletone;

import lt.karpovic.fileservice.common.AppConfig;
import lt.karpovic.fileservice.model.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class UploadFiles {
    private static UploadFiles instance;
    private List<String> filesName = Collections.synchronizedList(new ArrayList<>());

    private Map<String, Map<String, Word>> wordsMap = new ConcurrentSkipListMap<>();

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


    public void processWord(String w) {
        if (w != null && !w.isEmpty()) {
            char firstLetter = w.charAt(0);
            if (firstLetter >= AppConfig.START_1 && firstLetter <= AppConfig.END_1) {
                putWordToMap(w, AppConfig.WORDS_1);
            } else if (firstLetter >= AppConfig.START_2 && firstLetter <= AppConfig.END_2) {
                putWordToMap(w, AppConfig.WORDS_2);
            } else if (firstLetter >= AppConfig.START_3 && firstLetter <= AppConfig.END_3) {
                putWordToMap(w, AppConfig.WORDS_3);
            } else if (firstLetter >= AppConfig.START_4 && firstLetter <= AppConfig.END_4) {
                putWordToMap(w, AppConfig.WORDS_4);
            }
        }
    }

    private void putWordToMap(String w, String mapKey) {
        if (wordsMap.get(mapKey) != null) {
            if (wordsMap.get(mapKey).get(w) != null) {
                wordsMap.get(mapKey).get(w).incrementCounter();
            } else {
                putNewWordToMap(w, mapKey);
            }
        } else {
            wordsMap.put(mapKey, new ConcurrentSkipListMap<>());
            putNewWordToMap(w, mapKey);
        }
    }

    private void putNewWordToMap(String w, String mapKey) {
        wordsMap.get(mapKey).put(w, new Word(w));
    }


    public int getNumberOfWords() {
        int size = 0;
        for (Map<String, Word> partOfWordsMap : wordsMap.values()) {
            size += partOfWordsMap.size();
        }
        return size;
    }

    public Map<String, Map<String, Word>> getWordsMap() {
        return wordsMap;
    }

    public Map<String, Word> getPartWordsMap(String wordPart) {
        if (wordsMap.get(wordPart) == null) {
            return Collections.emptyMap();
        } else {
            return wordsMap.get(wordPart);
        }
    }
}
