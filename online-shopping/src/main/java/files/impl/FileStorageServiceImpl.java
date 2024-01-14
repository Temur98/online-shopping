package files.impl;

import com.nimbusds.jose.util.Resource;
import files.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Annotation;

public class FileStorageServiceImpl implements FileStorageService {

    @Override
    public String storeFile(MultipartFile file) {
        return null;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        return null;
    }
}
