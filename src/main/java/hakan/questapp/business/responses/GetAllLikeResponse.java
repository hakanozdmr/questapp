package hakan.questapp.business.responses;

import hakan.questapp.entities.Post;
import hakan.questapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllLikeResponse {


    private Long id;
    private User user;

    private Post post;
}

