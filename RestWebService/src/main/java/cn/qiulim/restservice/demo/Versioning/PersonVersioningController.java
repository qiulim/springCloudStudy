package cn.qiulim.restservice.demo.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {


    @GetMapping(path = "v1/person")
    public PersonV1 personV1() {
        return new PersonV1("qiulim");

    }

    @GetMapping(path = "v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("qiu", "lim"));

    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("qiulim");

    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("qiu", "lim"));

    }

    @GetMapping(value = "/person/head", headers = "X-API-VERSION=1")
    public PersonV1 headV1() {
        return new PersonV1("qiulim");

    }

    @GetMapping(value = "/person/head", headers = "X-API-VERSION=2")
    public PersonV2 headV2() {
        return new PersonV2(new Name("qiu", "lim"));

    }

    @GetMapping(value = "/person/head", produces = "application/qiulim.app-v1+json")
    public PersonV1 produceV1() {
        return new PersonV1("qiulim");

    }

    @GetMapping(value = "/person/head", produces = "application/qiulim.app-v2+json")
    public PersonV2 produceV2() {
        return new PersonV2(new Name("qiu", "lim"));

    }
}
