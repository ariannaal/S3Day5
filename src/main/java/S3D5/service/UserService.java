package S3D5.service;

import S3D5.entities.User;
import S3D5.exceptions.BadRequestEx;
import S3D5.payloads.NewUserDTO;
import S3D5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public User save(NewUserDTO body) {

        this.userRepository.findByUsername(body.username()).ifPresent(

                user -> {
                    throw new BadRequestEx("Lo username " + body.username() + " e' gia' in uso!");
                }
        );

        User newUser = new User(body.username(), bcrypt.encode(body.password()), body.role());

        User savedUser = this.userRepository.save(newUser);

        System.out.println("Lo user " + savedUser + " e' stato salvato con successo!");

        return savedUser;

    }
}
