package hakan.questapp.business.requests;

import hakan.questapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

   private String title;
    private String text;
    private User user;
}
