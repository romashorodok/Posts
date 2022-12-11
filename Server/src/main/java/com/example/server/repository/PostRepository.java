package com.example.server.repository;

import com.example.server.model.Post;
import org.springframework.data.domain.Page;
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
    @Query(value = "select * from posts where id=(select p.id from posts p LEFT JOIN post_likes pl ON p.id=pl.post_id group by id order by count(like_id) DESC limit 1)", nativeQuery = true)
    Post findMostLiked();
    List<Post> findByTitleContainingIgnoreCase(String title);

   // @Query(value = "select * from posts where id=(select p.id from posts p LEFT JOIN post_likes pl ON p.id=pl.post_id group by id order by count(like_id) DESC)", nativeQuery = true)
    @Query("select p from Post p order by size(p.likes) DESC")
    List<Post> sortByLikes();
    @Query("select distinct p from Post p left join p.tags tags where (:tag='all' or tags.name = :tag)")
    List<Post> findByTag(String tag);
    Page<Post> findAllByUserId(int id, Pageable pageable);
}
