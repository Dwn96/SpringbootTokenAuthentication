package io.auth.secure.File;

import io.auth.secure.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service

public class FileService {



    private final FileRepository fileRepository;
    private File file;

    FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public void saveImage(MultipartFile multipartFile) throws IOException {
        file.setName(StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        file.setContentType(multipartFile.getContentType());
        file.setData(multipartFile.getBytes());
        file.setSize(multipartFile.getSize());

        fileRepository.save(file);
    }

    public Optional<File> getImage(User user){
      return fileRepository.findById(user.getId());
    }

}
