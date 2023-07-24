package hakan.questapp.business.requests;

import lombok.Data;

@Data
public class RefreshRequest {

    Long userId;
    String refreshToken;
}