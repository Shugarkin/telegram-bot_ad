package com.example.telegrambot.dao;

import com.example.telegrambot.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeVaultRepository extends JpaRepository<Like, Long> {
    void deleteByLikerIdAndLikeOwnerId(long id, long id1);

    boolean existsByLikerIdAndLikeOwnerId(long liker, long likeOwner);

    List<Like> findAllByLikeOwnerId(long userId);

    long countByLikeOwnerId(long likeOwner);
}
