package hakan.questapp.business.rules;

import hakan.questapp.exceptions.BusinessException;
import hakan.questapp.repos.CommentRepository;
import hakan.questapp.repos.PostRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@Service
public class CommentBusinessRules {

    @Autowired
    private CommentRepository commentRepository;

    public void checkIfCommentExist(Long id){
        if(this.commentRepository.existsCommentById(id) == false){
            throw new BusinessException("Comment not found");
        }
    }
    public void checkIfCommentUserExist(Long userId){
        if(this.commentRepository.existsCommentByUserId(userId) == false){
            throw new BusinessException("User's comment not found");
        }
    }
    public void checkIfCommentPostExist(Long postId){
        if(this.commentRepository.existsCommentByPostId(postId) == false){
            throw new BusinessException("Post's comment not found");
        }
    }
    public void checkIfCommentPostAndUserExist(Long userId , Long postId){
        if(this.commentRepository.existsCommentByPostIdAndUserId(userId,postId) == false){
            throw new BusinessException("Post's and User's comment not found");
        }
    }
   /* public void checkIfCommentPostOrUserExist(Long userId , Long postId){
        if(this.commentRepository.existsCommentByPostIdAndUserId(userId,postId) == false){
            throw new BusinessException("Post and User not found");
        } else if (this.commentRepository.existsCommentByUserId(userId) == false) {
            throw new BusinessException("User not found");
        }
        else if (this.commentRepository.existsCommentByPostId(postId) == false) {
            throw new BusinessException("Post not found");
        }
    }*/
}
