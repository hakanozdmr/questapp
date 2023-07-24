package hakan.questapp.business.service.impl;

import hakan.questapp.business.requests.CreatePostRequest;
import hakan.questapp.business.requests.PostDto;
import hakan.questapp.business.requests.UpdatePostRequest;
import hakan.questapp.business.requests.UserDto;
import hakan.questapp.business.responses.GetAllPostsResponse;
import hakan.questapp.business.rules.PostBusinessRules;
import hakan.questapp.business.rules.UserBusinessRules;
import hakan.questapp.business.service.DtoConverterService;
import hakan.questapp.business.service.PostService;
import hakan.questapp.entities.Post;
import hakan.questapp.entities.User;
import hakan.questapp.repos.PostRepository;
import hakan.questapp.repos.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostBusinessRules postBusinessRules;


    private DtoConverterService dtoConverterService;

    public PostServiceImpl(PostRepository postRepository, PostBusinessRules postBusinessRules,DtoConverterService dtoConverterService) {
        this.postRepository = postRepository;
        this.postBusinessRules = postBusinessRules;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    @Transactional
    public List<GetAllPostsResponse> getAll(Optional<Long> userId) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        if (userId.isPresent()){
            this.postBusinessRules.checkIfPostsUserExist(userId.get());
            List<Post> posts = postRepository.findByUserId(userId.get(),sort);
            List<GetAllPostsResponse> listDto = posts.stream().map(post -> dtoConverterService.entityToDto(post, GetAllPostsResponse.class)).collect(Collectors.toList());
            return listDto;
        }else {
            List<Post> posts = postRepository.findAll(sort);
            List<GetAllPostsResponse> listDto = posts.stream().map(post -> dtoConverterService.entityToDto(post, GetAllPostsResponse.class)).collect(Collectors.toList());
            return  listDto;
        }
    }

    @Override
    public PostDto getById(Long id) {
        this.postBusinessRules.checkIfPostExist(id);
        Post post = postRepository.findById(id).orElseThrow();
        PostDto postDto = dtoConverterService.entityToDto(post, PostDto.class);
        return postDto;
    }
   /* @Override
    @Transactional
    public  List<GetAllPostsResponse> getByUserId(Long id) {
        this.postBusinessRules.checkIfPostsUserExist(id);
        List<Post> posts = postRepository.findByUserId(id);
        List<GetAllPostsResponse> listDto = posts.stream().map(post -> dtoConverterService.entityToDto(post, GetAllPostsResponse.class)).collect(Collectors.toList());
        return listDto;
    }*/


    @Override
    public void add(CreatePostRequest createPostRequest) {
        Post post = dtoConverterService.dtoToEntity(createPostRequest, Post.class);
        this.postRepository.save(post);
    }

    @Override
    public void update(UpdatePostRequest updatePostRequest) {
        this.postBusinessRules.checkIfPostExist(updatePostRequest.getId());
        Post p = postRepository.findById(updatePostRequest.getId()).get();
        Post post = dtoConverterService.dtoToEntity(updatePostRequest, Post.class);
        post.setUser(p.getUser());
        postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        this.postBusinessRules.checkIfPostExist(id);
        postRepository.deleteById(id);
    }

}
