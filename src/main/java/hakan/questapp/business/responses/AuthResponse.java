package hakan.questapp.business.responses;

import hakan.questapp.entities.User;
import lombok.Data;

@Data
public class AuthResponse {

    String message;
    Long userId;
    String accessToken;
    String refreshToken;

    User user;
}
