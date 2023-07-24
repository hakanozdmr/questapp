package hakan.questapp.repos;

import hakan.questapp.entities.Like;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByUserId(Long userId, Sort sort);

    List<Like> findByPostId(Long postId,Sort sort);

    boolean existsLikeById(Long id);

    boolean existsLikeByUserId(Long userId);

    boolean existsLikeByPostId(Long postId);

    boolean existsLikeByPostIdAndUserId( Long postId,Long userId);
}
