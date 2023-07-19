package hakan.questapp.repos;

import hakan.questapp.business.responses.GetAllPostsResponse;
import hakan.questapp.entities.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId, Sort sort);
    List<Comment> findByPostId(Long postId,Sort sort);

    boolean existsCommentById(Long id);

    boolean existsCommentByUserId(Long userId);

    boolean existsCommentByPostId(Long postId);

    boolean existsCommentByPostIdAndUserId(Long userId , Long postId);
}
