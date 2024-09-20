package S3D5.service;

import S3D5.entities.User;
import S3D5.exceptions.BadRequestEx;
import S3D5.exceptions.NotFoundEx;
import S3D5.payloads.NewUserDTO;
import S3D5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<User> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.userRepository.findAll(pageable);
    }

    public User findById(int id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundEx(id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundEx("Utente non trovato con username: " + username));
    }

    public void findByIdAndDelete(int id) {
        User found = this.findById(id);
        this.userRepository.delete(found);
    }

}
