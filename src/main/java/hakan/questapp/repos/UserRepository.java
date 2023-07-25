package hakan.questapp.repos;

import hakan.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsUserByUserName(String userName);


    User findByUserName(String userName);
}
