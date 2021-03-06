package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This controller method is called when the request pattern is of type 'image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives all the details of the comments to be stored in the database, and now the comment will be sent to the business logic to be persisted in the database
    //Set the date on which the comment is posted
    //After storing the comment, this method directs user to showImage()
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createPost(@PathVariable(name = "imageId") Integer imageId, @RequestParam("comment") String comment , HttpSession session){
        Comment newComment = new Comment();
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        newComment.setUser(user);
        newComment.setImage(image);
        newComment.setText(comment);
        newComment.setCreatedDate(new Date());
        commentService.createNewComment(newComment);
        return "redirect:/images/"+imageId+"/"+image.getTitle();
    }
}
