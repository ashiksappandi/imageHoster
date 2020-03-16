package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch the image from the database with corresponding id
    //Returns the comments fetched from the database
    public List<Comment> getComments(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c where c.image.id =:imageId", Comment.class).setParameter("imageId", imageId);
        List<Comment> comments = new ArrayList<>();
        comments = typedQuery.getResultList();
        return comments;
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to persist the comment into the database with corresponding imageId
    public void createNewComment(Comment newComment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }
}
