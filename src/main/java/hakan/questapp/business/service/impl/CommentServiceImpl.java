package hakan.questapp.business.service.impl;

import hakan.questapp.business.requests.*;
import hakan.questapp.business.responses.GetAllCommentResponse;
import hakan.questapp.business.responses.GetAllPostsResponse;
import hakan.questapp.business.rules.CommentBusinessRules;
import hakan.questapp.business.rules.PostBusinessRules;
import hakan.questapp.business.service.CommentService;
import hakan.questapp.business.service.DtoConverterService;
import hakan.questapp.entities.Comment;
import hakan.questapp.entities.Post;
import hakan.questapp.repos.CommentRepository;
import hakan.questapp.repos.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentBusinessRules commentBusinessRules;

    private DtoConverterService dtoConverterService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentBusinessRules commentBusinessRules, DtoConverterService dtoConverterService) {
        this.commentRepository = commentRepository;
        this.commentBusinessRules = commentBusinessRules;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    @Transactional
    public List<GetAllCommentResponse> getAll(Optional<Long> userId, Optional<Long> postId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        if(userId.isPresent() && postId.isPresent()){
            this.commentBusinessRules.checkIfCommentPostAndUserExist(userId.get(),postId.get());
            List<Comment> comments =  commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
            List<GetAllCommentResponse> listDto = comments.stream().map(comment -> dtoConverterService.entityToDto(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
            return listDto;
        } else if (userId.isPresent()) {
            this.commentBusinessRules.checkIfCommentUserExist(userId.get());
            List<Comment> comments =  commentRepository.findByUserId(userId.get(),sort);
            List<GetAllCommentResponse> listDto = comments.stream().map(comment -> dtoConverterService.entityToDto(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
            return listDto;
        }
        else if (postId.isPresent()) {
            this.commentBusinessRules.checkIfCommentPostExist(postId.get());
            List<Comment> comments =  commentRepository.findByPostId(postId.get(),sort);
            List<GetAllCommentResponse> listDto = comments.stream().map(comment -> dtoConverterService.entityToDto(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
            return listDto;
        }

        List<Comment> comments = commentRepository.findAll(sort);
        List<GetAllCommentResponse> listDto = comments.stream().map(comment -> dtoConverterService.entityToDto(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
        return  listDto;
    }

    @Override
    public CommentDto getById(Long id) {
        this.commentBusinessRules.checkIfCommentExist(id);
        Comment comment = commentRepository.findById(id).orElseThrow();
        CommentDto commentDto = dtoConverterService.entityToDto(comment, CommentDto.class);
        return commentDto;
    }


    @Override
    public void add(CreateCommentRequest createCommentRequest) {
        Comment comment = dtoConverterService.dtoToEntity(createCommentRequest, Comment.class);
        this.commentRepository.save(comment);
    }

    @Override
    public void update(UpdateCommentRequest updateCommentRequest) {
        this.commentBusinessRules.checkIfCommentExist(updateCommentRequest.getId());
        Comment c = commentRepository.findById(updateCommentRequest.getId()).get();
        Comment comment = dtoConverterService.dtoToEntity(updateCommentRequest, Comment.class);
        comment.setUser(c.getUser());
        comment.setPost(c.getPost());
        commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        this.commentBusinessRules.checkIfCommentExist(id);
        commentRepository.deleteById(id);
    }
}
