package com.beginnerspringboot.beginnertoadvanced.services.servicesimpl;

import com.beginnerspringboot.beginnertoadvanced.entites.Post;
import com.beginnerspringboot.beginnertoadvanced.entites.User;
import com.beginnerspringboot.beginnertoadvanced.enums.PEnum;
import com.beginnerspringboot.beginnertoadvanced.repositories.PostRepository;
import com.beginnerspringboot.beginnertoadvanced.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public List<Post> findPost() {
        return postRepository.findAll();
    }

    @Override
    public ResponseEntity<?> findByGetIdPost(Long id) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        Post post = postRepository.findById(id).orElse(null);
        hashMap.put(PEnum.result,post);
        return new ResponseEntity<>(hashMap,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deletePost(Long id) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        Optional<Post> hasPost =  postRepository.findById(id);
        if(hasPost != null)
        {
            hashMap.put(PEnum.status,HttpStatus.OK);
            hashMap.put(PEnum.message,"Başarıyla Silindi");
            postRepository.deleteById(id);
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(PEnum.message,"Post Bulunamadı.");
        return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> addPost(Post post) {
        HashMap<PEnum,Object> hashMap = new HashMap();
        hashMap.put(PEnum.message,"Başarıyla Eklendi");
        hashMap.put(PEnum.status, HttpStatusCode.valueOf(200));
        postRepository.save(post);

        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updatePost(Post post) {
       HashMap<PEnum,Object> hashMap = new HashMap<>();
       Optional<Post> hasUser = postRepository.findById(post.getId());
       if(hasUser.isPresent())
       {
           postRepository.saveAndFlush(post);
           hashMap.put(PEnum.message,"Başarıyla Güncellendi");
           hashMap.put(PEnum.status,HttpStatus.OK);
           return new ResponseEntity<>(hashMap,HttpStatus.OK);
       }
       hashMap.put(PEnum.status,false);
       hashMap.put(PEnum.message,"Güncellenemedi");

        return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
    }
}
