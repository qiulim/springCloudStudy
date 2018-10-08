package cn.qiulim.restservice.demo.Repository;


import cn.qiulim.restservice.demo.Bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
