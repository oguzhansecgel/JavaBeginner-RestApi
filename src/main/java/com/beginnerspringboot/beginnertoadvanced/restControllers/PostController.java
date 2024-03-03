package com.beginnerspringboot.beginnertoadvanced.restControllers;

import com.beginnerspringboot.beginnertoadvanced.entites.Post;
import com.beginnerspringboot.beginnertoadvanced.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;


    @GetMapping("/GetAllPost")
    public List<Post> getAllPost()
    {
        return postService.findPost();
    }
    @GetMapping("/GetByIdPost")
    public ResponseEntity<?> getByIdPost(@RequestParam Long id)
    {
        return postService.findByGetIdPost(id);
    }
   @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody Post post)
   {
       return postService.addPost(post);
   }
   @DeleteMapping("/deletePost")
    public ResponseEntity<?> deletePost(@RequestParam Long id)
   {
       return postService.deletePost(id);
   }
   @PutMapping("/updatePost")
    public ResponseEntity<?> updatePost(@RequestBody Post post)
   {
       return postService.updatePost(post);
   }


}
