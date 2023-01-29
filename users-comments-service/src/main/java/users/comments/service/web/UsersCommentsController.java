package users.comments.service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class UsersCommentsController
{
    @GetMapping("hi")
    public String getAllComments()
    {
        return "Hi from users comments";
    }
}
