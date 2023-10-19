package com.example.telegrambot.dao;

import com.example.telegrambot.model.User;
import com.example.telegrambot.model.UserWithLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new com.example.telegrambot.model.UserWithLikes(u.id, u.nickName, u.firstName, u.lastName, u.email, count(l.likeOwner.id)) " +
            "from User as u join Like as l on u.id=l.likeOwner.id " +
            "where u.id=?1 and l.likeOwner.id=?1 ")
    Optional<UserWithLikes> findUserwithLikes(long userId);

    User findByNickName(String text);

    void deleteByNickName(String nickName);

    boolean existsByNickName(String nickName);
}
