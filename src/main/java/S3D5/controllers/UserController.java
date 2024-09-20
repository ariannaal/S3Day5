package S3D5.controllers;

import S3D5.entities.User;
import S3D5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


//    @GetMapping("/me")
//    public User getUser(@AuthenticationPrincipal User currentAuthenticatedUser) {
//        return currentAuthenticatedUser;
//    }
//
//    @DeleteMapping("/me")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteUser(@AuthenticationPrincipal User currentAuthenticatedUser) {
//        this.userService.findByIdAndDelete(currentAuthenticatedUser.getId());
//    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        this.userService.findByIdAndDelete(id);
    }

}
