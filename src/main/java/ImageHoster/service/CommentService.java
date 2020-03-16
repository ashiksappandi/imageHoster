package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    //The method calls the getCommentsById() method in the Repository and passes the id of the image to be fetched
    public List<Comment> getCommentsById(Integer imageId) { return commentRepository.getComments(imageId);}

    //The method calls the createNewComment() method in the Repository and passes the id of the image to be persisted in the database
    public void createNewComment(Comment newComment) { commentRepository.createNewComment(newComment); }
}
