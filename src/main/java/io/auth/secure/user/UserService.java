package io.auth.secure.user;

import io.auth.secure.File.File;
import io.auth.secure.File.FileRepository;
import io.auth.secure.File.FileService;
import io.auth.secure.registration.token.ConfirmationToken;
import io.auth.secure.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final FileService fileService;
    private final FileRepository fileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(User user, MultipartFile multipartFile){

        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException("Email Already Taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        //TODO: Send confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15), user
        );

        try {
            File file = new File(multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getSize(), multipartFile.getBytes(),
                    user);
            fileService.saveImage(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        confirmationTokenService.saveConfirmationToken(confirmationToken);


        //TODO: Send Email

        return token;
    }

    public int enableUser(String email){
        return userRepository.enableUser(email);
    }

}
