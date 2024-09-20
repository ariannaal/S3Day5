package S3D5.service;

import S3D5.entities.User;
import S3D5.exceptions.UnauthorizedEx;
import S3D5.payloads.UserLoginDTO;
import S3D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {

        User found = this.userService.findByUsername(body.username());
        if (bcrypt.matches(body.password(), found.getPassword())) {

            return jwtTools.createToken(found);
        } else {

            throw new UnauthorizedEx("Credenziali errate!");
        }

    }
}
