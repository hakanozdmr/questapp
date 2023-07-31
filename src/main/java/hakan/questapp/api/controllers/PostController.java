package hakan.questapp.api.controllers;

import hakan.questapp.business.requests.CreatePostRequest;
import hakan.questapp.business.requests.CreateUserRequest;
import hakan.questapp.business.requests.UpdatePostRequest;
import hakan.questapp.business.requests.UpdateUserRequest;
import hakan.questapp.business.responses.AuthResponse;
import hakan.questapp.business.responses.GetAllPostsResponse;
import hakan.questapp.business.service.PostService;
import hakan.questapp.business.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping()
    public ResponseEntity<?> findAllPosts(@RequestParam Optional<Long> userId){
        return new ResponseEntity<>(postService.getAll(userId), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }
    /*@GetMapping("/userposts/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable("userId") Long id){
        return new ResponseEntity<>(postService.getAll(id), HttpStatus.OK);
    }*/
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<GetAllPostsResponse> add(@RequestBody @Valid CreatePostRequest createPostRequest){
         postService.add(createPostRequest);
        GetAllPostsResponse authResponse = new GetAllPostsResponse();
        authResponse.setText(createPostRequest.getText());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public UpdatePostRequest uptade(@RequestBody @Valid UpdatePostRequest updatePostRequest){
        postService.update(updatePostRequest);
        return updatePostRequest;
    }

    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> delete(Long id){
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
