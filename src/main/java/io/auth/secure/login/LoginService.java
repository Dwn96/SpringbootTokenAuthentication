package io.auth.secure.login;

import io.auth.secure.registration.EmailValidator;
import io.auth.secure.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LoginService {
    EmailValidator emailValidator;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public String login (LoginRequest loginRequest){
        boolean isEmailValid  = emailValidator.test(loginRequest.getEmail());
        if(!isEmailValid){
            throw new IllegalStateException("Invalid email");
        }
        boolean userExists = userRepository.findByEmail(loginRequest.getEmail()).isPresent();

        if(userExists){
            String encodedPassword = userRepository.findPasswordbyEmail(loginRequest.getEmail());



            boolean passwordMatch = bCryptPasswordEncoder.matches(loginRequest.getPassword(),encodedPassword);

            if (passwordMatch){
                return "All good";
            }
            else return  "Bad password";


        }
        else
            return "User Doesn't Exist";



    }

}
