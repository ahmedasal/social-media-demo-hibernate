package com.social.media.controllers;

import com.social.media.model.Image;
import com.social.media.model.Post;
import com.social.media.service.PostService;
import com.social.media.util.ConnectionHelper;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class FileUploadServlet extends HttpServlet {
    PostService postService = new PostService();
    WallServlet wallServlet = new WallServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = null;
        InputStream inputStream = null;
        Part filePart = req.getPart("photo");
        inputStream = filePart.getInputStream();
        //TODO i edited it to blob but i didn't try it.
        Blob imgBlob = null;
        try {
            imgBlob = new SerialBlob(inputStream.readAllBytes());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Image img = new Image();
        Post post = new Post();
        img.setPost(post);
        img.setImg(imgBlob);
        img.getPost().setId(Integer.parseInt(req.getParameter("id")));

        //just for debugging
        System.out.println(filePart.getName());
        System.out.println(filePart.getSize());
        System.out.println(filePart.getContentType());
        System.out.println(inputStream.available());

        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            if (inputStream.readAllBytes().length != 0) {
                postService.saveImage(em, img);
                req.setAttribute("upload", "message has been uploaded");
                req.setAttribute("pId", img.getPost().getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
          em.close();
        }


        wallServlet.doGet(req, resp);


    }
}
