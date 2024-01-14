package files;


import com.nimbusds.jose.util.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);

}
