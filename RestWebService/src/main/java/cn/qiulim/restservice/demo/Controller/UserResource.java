package cn.qiulim.restservice.demo.Controller;

import cn.qiulim.restservice.demo.Bean.User;
import cn.qiulim.restservice.demo.Exception.UserNotFoundException;
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

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/user/{id}")
    private Resource retrieveUser(@PathVariable int id) throws UserNotFoundException {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);

        }

        Resource resource = new Resource(user);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/createUser")
    private ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable int id) throws UserNotFoundException {
        User usr = service.deleteById(id);

        if (usr == null) {
            throw new UserNotFoundException("id--" + id);
        }
    }
}
