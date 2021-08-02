package io.auth.secure.File;

import io.auth.secure.user.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service

public class FileService {



    private final FileRepository fileRepository;


    FileService(FileRepository fileRepository){

        this.fileRepository = fileRepository;
    }

    public String saveImage(File file)  {


        fileRepository.save(file);

        return "Successfully added image";
    }

    public Optional<File> getImage(User user){
      return fileRepository.findById(user.getId());
    }

}
