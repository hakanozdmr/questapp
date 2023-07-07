package hakan.questapp.api.controllers;

import hakan.questapp.business.requests.CreateUserRequest;
import hakan.questapp.business.requests.UpdateUserRequest;
import hakan.questapp.business.responses.UserDto;
import hakan.questapp.business.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateUserRequest add(@RequestBody @Valid CreateUserRequest createUserRequest){
        userService.add(createUserRequest);
        return createUserRequest;
    }
    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public UpdateUserRequest uptade(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        userService.update(updateUserRequest);
        return updateUserRequest;
    }

    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> delete(Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
