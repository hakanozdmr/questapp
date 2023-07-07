package hakan.questapp.business.rules;

import hakan.questapp.entities.User;
import hakan.questapp.exceptions.BusinessException;
import hakan.questapp.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@NoArgsConstructor
@Service
public class UserBusinessRules {
    @Autowired
    private UserRepository userRepository;

    public void checkIfUserNameExists(String userName){
        if(this.userRepository.existsByUserName(userName)){
            throw new BusinessException("Username already exists");
        }
    }
    public void checkIfUserExist(Long id){
        if(this.userRepository.existsById(id) == false){
            throw new BusinessException("User not found");
        }
    }
}

