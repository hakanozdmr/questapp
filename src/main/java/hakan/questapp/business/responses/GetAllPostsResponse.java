package hakan.questapp.business.responses;

import hakan.questapp.entities.Comment;
import hakan.questapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPostsResponse {

    private Long id;
    private String title;
    private String text;
//    private User user;
    private Long userId;
    private String  userUserName;
    private Date createdDate;

    private List<Comment> comments;
}

