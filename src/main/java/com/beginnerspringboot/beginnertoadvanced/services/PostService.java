package com.beginnerspringboot.beginnertoadvanced.services;

import com.beginnerspringboot.beginnertoadvanced.entites.Post;
import com.beginnerspringboot.beginnertoadvanced.entites.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    public List<Post> findPost();
    public ResponseEntity<?> findByGetIdPost(Long id);
    public ResponseEntity<?> deletePost(Long id);

    public ResponseEntity<?> addPost(Post post);

    public ResponseEntity<?> updatePost(Post post);
}
