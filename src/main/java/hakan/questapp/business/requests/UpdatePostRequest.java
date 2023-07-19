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
public class UpdatePostRequest {

    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String text;
}
