package hakan.questapp.api.controllers;

import hakan.questapp.business.requests.CreateCommentRequest;
import hakan.questapp.business.requests.CreatePostRequest;
import hakan.questapp.business.requests.UpdateCommentRequest;
import hakan.questapp.business.requests.UpdatePostRequest;
import hakan.questapp.business.service.CommentService;
import hakan.questapp.business.service.PostService;
import hakan.questapp.entities.Comment;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public ResponseEntity<?> findAllPosts(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
        return new ResponseEntity<>(commentService.getAll(userId,postId), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(commentService.getById(id), HttpStatus.OK);
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateCommentRequest add(@RequestBody @Valid CreateCommentRequest createCommentRequest){
        commentService.add(createCommentRequest);
        return createCommentRequest;
    }
    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public UpdateCommentRequest uptade(@RequestBody @Valid UpdateCommentRequest updateCommentRequest){
        commentService.update(updateCommentRequest);
        return updateCommentRequest;
    }

    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> delete(Long id){
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
