package com.social.media.service;

import com.social.media.model.Page;
import com.social.media.model.Post;
import jakarta.persistence.EntityManager;
import java.util.*;

public class PageService {

    public Page getPage(EntityManager em, int id) {
        return em.find(Page.class, id);
    }
    //create new page.
    public Page createNewPage(EntityManager em, Page page) {
        em.persist(page);
        return page;
    }
    //delete page.
    public void deletePage(EntityManager em, int id) {
        Page page = em.find(Page.class, id);
        em.remove(page);
     }
    //add post to page.
    public Post addPostToPage(EntityManager em, Post post) {
        em.persist(post);
        return post;
    }
    //show all page posts.
    public List<Post> showPagePosts(EntityManager em, int pageId) {

        List posts = em.createQuery("select p from Post p where p.page.id = :pageId order by postDate desc ").setParameter("pageId", pageId).getResultList();
        return posts;
    }
    public List<Page> allPages(EntityManager em) {
        List<Page> pages = em.createQuery("select page from Page page").getResultList();
        return pages;
    }
}
