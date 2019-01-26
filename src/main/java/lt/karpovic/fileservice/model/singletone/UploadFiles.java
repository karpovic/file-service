package lt.karpovic.fileservice.model.singletone;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class UploadFiles {
    private static UploadFiles instance;
    private List<MultipartFile> files = new ArrayList<>();

    private UploadFiles() {
    }

    public static synchronized UploadFiles getInstance() {
        if (instance == null) {
            instance = new UploadFiles();
        }
        return instance;
    }

    public void addFile(MultipartFile file) {
        if (file != null) {
            files.add(file);
        }
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public int getNumberOfFiles() {
        return files.size();
    }
}
