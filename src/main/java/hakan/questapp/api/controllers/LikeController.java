package hakan.questapp.api.controllers;

import hakan.questapp.business.requests.CreateCommentRequest;
import hakan.questapp.business.requests.UpdateCommentRequest;
import hakan.questapp.business.service.CommentService;
import hakan.questapp.business.service.LikeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/likes")
@CrossOrigin
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping()
    public ResponseEntity<?> findAllPosts(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return new ResponseEntity<>(likeService.getAll(userId,postId), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(likeService.getById(id), HttpStatus.OK);
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateCommentRequest add(@RequestBody @Valid CreateCommentRequest createCommentRequest){
        likeService.add(createCommentRequest);
        return createCommentRequest;
    }
    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> delete(Long id){
        likeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
