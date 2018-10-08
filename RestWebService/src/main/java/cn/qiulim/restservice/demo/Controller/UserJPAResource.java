package cn.qiulim.restservice.demo.Controller;

import cn.qiulim.restservice.demo.Bean.Post;
import cn.qiulim.restservice.demo.Bean.User;
import cn.qiulim.restservice.demo.Exception.UserNotFoundException;
import cn.qiulim.restservice.demo.Repository.PostRepository;
import cn.qiulim.restservice.demo.Repository.UserRepository;
import cn.qiulim.restservice.demo.Service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping("/jpa/user/{id}")
    private Resource retrieveUser(@PathVariable int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);

        }

        Resource resource = new Resource(user.get());
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/jpa/createUser")
    private ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/jpa/user/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/user/{id}")
    public void deleteById(@PathVariable int id) throws UserNotFoundException {
        userRepository.deleteById(id);

    }

    @GetMapping("/jpa/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/{id}/createPosts")
    public ResponseEntity createPost(@PathVariable int id, @RequestBody Post post) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();
        post.setUser(user);


        postRepository.save(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/jpa/{id}/posts").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
