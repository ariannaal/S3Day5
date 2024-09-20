package S3D5.controllers;

import S3D5.payloads.UserLoginDTO;
import S3D5.payloads.UserLoginResponseDTO;
import S3D5.service.AuthService;
import S3D5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService usersService;



}
