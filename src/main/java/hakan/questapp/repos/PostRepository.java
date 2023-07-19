package hakan.questapp.repos;

import hakan.questapp.entities.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    boolean existsPostsById(Long id);

    boolean existsPostsByUserId(Long id);

    List<Post> findByUserId(Long userId, Sort sort);
}
