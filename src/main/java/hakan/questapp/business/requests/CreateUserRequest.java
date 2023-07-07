package hakan.questapp.business.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotNull
    @NotBlank
    @Size(min = 3,max = 20)
    private String userName;
    @NotNull
    @NotBlank
    @Size(min = 6,max = 20)
    private String  password;
}
