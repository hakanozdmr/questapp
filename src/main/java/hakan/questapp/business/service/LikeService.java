package hakan.questapp.business.service;

import hakan.questapp.business.requests.CommentDto;
import hakan.questapp.business.requests.CreateCommentRequest;
import hakan.questapp.business.requests.LikeDto;
import hakan.questapp.business.requests.UpdateCommentRequest;
import hakan.questapp.business.responses.GetAllLikeResponse;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    List<GetAllLikeResponse> getAll(Optional<Long> userId, Optional<Long> postId);

    LikeDto getById(Long id);
    void add(CreateCommentRequest createCommentRequest);
    void delete(Long id);
}
