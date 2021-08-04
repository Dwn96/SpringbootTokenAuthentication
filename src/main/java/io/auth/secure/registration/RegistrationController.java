package io.auth.secure.registration;

import io.auth.secure.File.File;
import io.auth.secure.File.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    private FileService fileService;



    @PostMapping
    public String register (@RequestPart(value = "user")  RegistrationRequest request, @RequestPart(value ="file") MultipartFile multipartFile){
               return  registrationService.register(request,multipartFile);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
