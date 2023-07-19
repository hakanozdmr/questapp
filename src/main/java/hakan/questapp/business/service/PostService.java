package hakan.questapp.business.service;

import hakan.questapp.business.requests.*;
import hakan.questapp.business.responses.GetAllPostsResponse;
import hakan.questapp.entities.Post;
import hakan.questapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<GetAllPostsResponse> getAll(Optional<Long> userId);

    PostDto getById(Long id);
    /*List<GetAllPostsResponse> getByUserId(Long id);*/
    void add(CreatePostRequest createPostRequest);

    void update(UpdatePostRequest updatePostRequest);

    void delete(Long id);

}
