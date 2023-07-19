package hakan.questapp.business.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {


    private Long id;
    String text;
    @NotNull
    @NotBlank
    private Long userId;

    @NotNull
    @NotBlank
    private Long postId;
}
