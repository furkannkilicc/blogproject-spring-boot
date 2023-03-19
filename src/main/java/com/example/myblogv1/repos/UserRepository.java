package com.example.myblogv1.repos;

import com.example.myblogv1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
//    User findByName(String name);
    //Todo: FINDBY KALIPTI UNUTMA O YÜZDEN HATA ALMIŞ OLABİLİRSİN UserDetails Service kısmında


//    Optional<User> findByUsername(String username);

}
