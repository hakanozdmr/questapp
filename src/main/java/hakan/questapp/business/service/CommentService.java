package hakan.questapp.business.service;

import hakan.questapp.business.requests.*;
import hakan.questapp.business.responses.GetAllCommentResponse;
import hakan.questapp.business.responses.GetAllPostsResponse;
import hakan.questapp.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<GetAllCommentResponse> getAll(Optional<Long> userId, Optional<Long> postId);

    CommentDto getById(Long id);
    void add(CreateCommentRequest createCommentRequest);

    void update(UpdateCommentRequest updateCommentRequest);

    void delete(Long id);
}
