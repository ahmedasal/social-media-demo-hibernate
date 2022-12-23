package com.social.media.service;


import com.social.media.model.Comment;
import com.social.media.model.Post;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;

public class CommentService {

    // add comment
    public Comment addComment(EntityManager em, Comment comment)  {
        em.persist(comment);
        // then>> update posts set comments_count=comments_count+1 where id=?
        em.createQuery("update Post post set post.commentsCount = post.commentsCount + 1 where post.id = :id").setParameter("id", comment.getPost().getId()).executeUpdate();
        return comment;
    }

    //remove comment
    public int removeComment(EntityManager em, int id) throws SQLException {
        Comment comment = em.find(Comment.class, id);
        Post post = em.find(Post.class, comment.getPost().getId());
        int count = em.createQuery("update Post post set post.commentsCount = post.commentsCount - 1 where post.id= : postId ")
                .setParameter("postId", post.getId()).executeUpdate();
        em.remove(comment);
        return count;
    }


//    public ArrayList<Comment> getCommentchildren(Connection connection, int commentId) throws SQLException {
//        ArrayList<Comment> comments = new ArrayList<>();
//        Comment comment = new Comment();
//        PreparedStatement preparedStatement = connection.prepareStatement("select c.comment_text , c.id, p.id as parent_id, c.user_id  ,p.comment_text , c.create_date , c.update_date from comments p , comments c  where c.comment_parent_id = p.id and p.id = 26 ;");
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()) {
//            User user = new User();
//            comment.setId(resultSet.getInt("id"));
//            comment.setCommentText(resultSet.getString("comment_text"));
//            user.setId(resultSet.getInt("user_id"));
//            comment.setUser(user);
//            comment.setParentCommentId(resultSet.getInt("parent_id"));
//            comment.setCreatedDate(resultSet.getString("create_date"));
//            comment.setUpdatedDate(resultSet.getString("update_date"));
//
//
//            comments.add(comment);
//
//        }
//
//
//        return comments;
//    }
}





