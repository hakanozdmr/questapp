package hakan.questapp.business.requests;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {

    private Long id;
    @NotNull
    @NotBlank
    @Lob
    String text;

    private Long userId;

    private Long postId;
}
