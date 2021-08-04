package io.auth.secure.registration;

import io.auth.secure.File.File;
import io.auth.secure.File.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    private FileService fileService;

    @PostMapping
    public String register (@RequestPart("user") RegistrationRequest request, @RequestPart("file") MultipartFile multipartFile){
               return  registrationService.register(request,multipartFile);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
