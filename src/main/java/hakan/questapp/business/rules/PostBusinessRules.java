package hakan.questapp.business.rules;

import hakan.questapp.exceptions.BusinessException;
import hakan.questapp.repos.PostRepository;
import hakan.questapp.repos.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PostBusinessRules {
    @Autowired
    private PostRepository postRepository;

    /*public void checkIfUserNameExists(String userName){
        if(this.postRepository.existsByUserName(userName)){
            throw new BusinessException("Username already exists");
        }
    }*/
    public void checkIfPostExist(Long id){
        if(this.postRepository.existsPostsById(id) == false){
            throw new BusinessException("Post not found");
        }
    }
    public void checkIfPostsUserExist(Long id){
        if(this.postRepository.existsPostsByUserId(id) == false){
            throw new BusinessException("User and Post not found");
        }
    }
}
