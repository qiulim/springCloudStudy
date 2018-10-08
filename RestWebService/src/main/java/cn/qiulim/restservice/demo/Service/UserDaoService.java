package cn.qiulim.restservice.demo.Service;

import cn.qiulim.restservice.demo.Bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "qiulim", new Date()));
        users.add(new User(2, "qiuhx", new Date()));
        users.add(new User(3, "qiusunli", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User usr : users) {
            if (usr.getId() == id) {
                return users.get(id);
            }
        }
        return null;
    }


    public User deleteById(int id) {

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User usr = iterator.next();
            if (usr.getId() == id) {
                iterator.remove();
                return usr;
            }
        }

        return null;
    }
}
