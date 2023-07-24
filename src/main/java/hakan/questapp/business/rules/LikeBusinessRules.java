package hakan.questapp.business.rules;

import hakan.questapp.exceptions.BusinessException;
import hakan.questapp.repos.CommentRepository;
import hakan.questapp.repos.LikeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class LikeBusinessRules {

    @Autowired
    private LikeRepository likeRepository;

    public void checkIfLikeExist(Long id){
        if(this.likeRepository.existsLikeById(id) == false){
            throw new BusinessException("Like not found");
        }
    }
    public void checkIfLikeUserExist(Long userId){
        if(this.likeRepository.existsLikeByUserId(userId) == false){
            throw new BusinessException("User's comment not found");
        }
    }
    public void checkIfLikePostExist(Long postId){
        if(this.likeRepository.existsLikeByPostId(postId) == false){
            throw new BusinessException("Post's like not found");
        }
    }
    public void checkIfLikePostAndUserExist(Long postId,Long userId) {
        if(this.likeRepository.existsLikeByPostIdAndUserId(postId,userId) == false){
            throw new BusinessException("Post's and User's like not found");
        }
    }

}
