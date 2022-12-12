package com.social.media.controllers;

import com.social.media.model.Image;
import com.social.media.model.Post;
import com.social.media.model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PostRequestReader {
    public static PostWithPhotos readPostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PostWithPhotos postWithPhotos = new PostWithPhotos();

        User user = (User) request.getSession().getAttribute("currentUser");
        Post post = new Post();
        postWithPhotos.post = post;
        post.setPostOwner(user.getId());
        post.setPostDate(new Date());

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new RuntimeException("Request is not multipart");
        }
        else{
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                items = upload.parseRequest(request);

            } catch (FileUploadException e) {
                throw new RuntimeException(e);
            }
            Iterator itr = items.iterator();
            while(itr.hasNext()){
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()){
                   String fieldName = item.getFieldName();
                   String fieldValue = item.getString();
                   switch (fieldName) {
                       case "postText":
                           post.setPost(fieldValue);
                           break;
                   }
                } else {
                        Image image = new Image();
                        image.setInputStream(item.getInputStream());
                        postWithPhotos.images.add(image);
                }

            }
        }
        return postWithPhotos;
    }

    public static class PostWithPhotos {
        public Post post;
        public List<Image> images = new LinkedList<>();
    }
}
