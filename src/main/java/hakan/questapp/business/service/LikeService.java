package hakan.questapp.business.service;

import hakan.questapp.business.requests.*;
import hakan.questapp.business.responses.GetAllLikeResponse;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    List<GetAllLikeResponse> getAll(Optional<Long> userId, Optional<Long> postId);

    LikeDto getById(Long id);
    void add(CreateLikeRequest createLikeRequest);
    void delete(Long id);
}
