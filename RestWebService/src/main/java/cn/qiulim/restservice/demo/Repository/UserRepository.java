package cn.qiulim.restservice.demo.Repository;

import cn.qiulim.restservice.demo.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
