package hakan.questapp.business.service.impl;

import hakan.questapp.business.requests.CreateCommentRequest;
import hakan.questapp.business.requests.LikeDto;
import hakan.questapp.business.responses.GetAllCommentResponse;
import hakan.questapp.business.responses.GetAllLikeResponse;
import hakan.questapp.business.rules.CommentBusinessRules;
import hakan.questapp.business.rules.LikeBusinessRules;
import hakan.questapp.business.service.CommentService;
import hakan.questapp.business.service.DtoConverterService;
import hakan.questapp.business.service.LikeService;
import hakan.questapp.entities.Comment;
import hakan.questapp.entities.Like;
import hakan.questapp.repos.CommentRepository;
import hakan.questapp.repos.LikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private LikeBusinessRules likeBusinessRules;

    private DtoConverterService dtoConverterService;

    public LikeServiceImpl(LikeRepository likeRepository, LikeBusinessRules likeBusinessRules, DtoConverterService dtoConverterService) {
        this.likeRepository = likeRepository;
        this.likeBusinessRules = likeBusinessRules;
        this.dtoConverterService = dtoConverterService;
    }


    @Override
    @Transactional
    public List<GetAllLikeResponse> getAll(Optional<Long> userId, Optional<Long> postId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        if(userId.isPresent() && postId.isPresent()){
            this.likeBusinessRules.checkIfCommentPostAndUserExist(userId.get(),postId.get());
            List<Like> likes =  likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
            List<GetAllLikeResponse> listDto = likes.stream().map(like -> dtoConverterService.entityToDto(like, GetAllLikeResponse.class)).collect(Collectors.toList());
            return listDto;
        } else if (userId.isPresent()) {
            this.likeBusinessRules.checkIfCommentUserExist(userId.get());
            List<Like> likes =  likeRepository.findByUserId(userId.get(),sort);
            List<GetAllLikeResponse> listDto = likes.stream().map(comment -> dtoConverterService.entityToDto(comment, GetAllLikeResponse.class)).collect(Collectors.toList());
            return listDto;
        }
        else if (postId.isPresent()) {
            this.likeBusinessRules.checkIfCommentPostExist(postId.get());
            List<Like> likes =  likeRepository.findByPostId(postId.get(),sort);
            List<GetAllLikeResponse> listDto = likes.stream().map(comment -> dtoConverterService.entityToDto(comment, GetAllLikeResponse.class)).collect(Collectors.toList());
            return listDto;
        }

        List<Like> likes = likeRepository.findAll(sort);
        List<GetAllLikeResponse> listDto = likes.stream().map(comment -> dtoConverterService.entityToDto(comment, GetAllLikeResponse.class)).collect(Collectors.toList());
        return  listDto;
    }

    @Override
    public LikeDto getById(Long id) {
        return null;
    }

    @Override
    public void add(CreateCommentRequest createCommentRequest) {

    }

    @Override
    public void delete(Long id) {

    }
}
