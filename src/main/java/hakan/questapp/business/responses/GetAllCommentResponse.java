package hakan.questapp.business.responses;

import hakan.questapp.entities.Post;
import hakan.questapp.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentResponse {


    private Long id;
    String text;
    /*@NotNull
    @NotBlank
    private Long userId;

    @NotNull
    @NotBlank
    private Long postId;*/

    private User user;

    private Post post;
}
