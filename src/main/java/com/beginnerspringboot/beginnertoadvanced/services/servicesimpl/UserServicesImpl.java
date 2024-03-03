package com.beginnerspringboot.beginnertoadvanced.services.servicesimpl;

import com.beginnerspringboot.beginnertoadvanced.entites.User;
import com.beginnerspringboot.beginnertoadvanced.enums.PEnum;
import com.beginnerspringboot.beginnertoadvanced.repositories.UserRepository;
import com.beginnerspringboot.beginnertoadvanced.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.Period;
import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServicesImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> addUser(User user) {

        HashMap<PEnum,Object> hashMap = new HashMap<>();

        boolean hasUserName = userRepository.existsByUserName(user.getUserName());
        if(hasUserName)
        {
            hashMap.put(PEnum.status,false);
            hashMap.put(PEnum.message,"Kullanıcı adı kullanılıyor");
            hashMap.put(PEnum.username,user.getUserName());
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);

        }
        hashMap.put(PEnum.status,true);
        hashMap.put(PEnum.message,"Successful");
        hashMap.put(PEnum.username,user.getUserName());
        return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findUser(Long id) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        User user = userRepository.findById(id).orElse(null);
        if(user != null)
        {
            hashMap.put(PEnum.status,true);
            hashMap.put(PEnum.result,user);
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(PEnum.status,false);
        hashMap.put(PEnum.error,"Idye sahip user bulunamadı : "+id);
        return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<?> updateUser(User user) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        Optional<User> optionalUser = userRepository.findById(user.getId());
        boolean hasUserName = userRepository.existsByUserName(user.getUserName());
        if(optionalUser.isPresent())
        {
            if (hasUserName)
            {
                userRepository.saveAndFlush(user);
                hashMap.put(PEnum.status,true);
                hashMap.put(PEnum.message,"Güncelleme Başarılı");
                hashMap.put(PEnum.username,user.getUserName());
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }

        }
        hashMap.put(PEnum.status,false);
        hashMap.put(PEnum.error,"Kullanıcı bulunamadı");
        hashMap.put(PEnum.username,user.getUserName());

        return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        Optional<User> hasUser = userRepository.findById(id);
        if(hasUser != null)
        {
            userRepository.deleteById(id);
            hashMap.put(PEnum.message,"Başarıyla Silindi");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(PEnum.message,"Kullanıcı Bulunamadı");
        return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
    }
}
