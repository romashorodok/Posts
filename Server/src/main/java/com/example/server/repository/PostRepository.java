package com.example.server.repository;

import com.example.server.model.Post;
import com.example.server.model.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p left join fetch p.tags tags where (:tag='all' or tags.name = :tag) order by p.createdAt DESC")
    List<Post> findAllRecentByTags(@Param("tag") String tag, Pageable page);
}
