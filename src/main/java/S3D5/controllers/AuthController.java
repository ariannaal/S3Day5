package S3D5.controllers;

import S3D5.exceptions.BadRequestEx;
import S3D5.payloads.NewUserDTO;
import S3D5.payloads.NewUserResponseDTO;
import S3D5.payloads.UserLoginDTO;
import S3D5.payloads.UserLoginResponseDTO;
import S3D5.service.AuthService;
import S3D5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new UserLoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO save(@RequestBody @Validated NewUserDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            // Se ci sono stati errori lanciamo un'eccezione custom
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestEx("Ci sono stati errori nel payload. " + messages);
        } else {

            return new NewUserResponseDTO(this.usersService.save(body).getId());
        }

    }

}
