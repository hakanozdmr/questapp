package hakan.questapp.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {


    private Long id;
    @NotNull
    @NotBlank
    private Long userId;

    @NotNull
    @NotBlank
    private Long postId;
}
