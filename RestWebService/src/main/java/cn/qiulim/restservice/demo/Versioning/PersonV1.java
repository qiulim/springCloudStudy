package cn.qiulim.restservice.demo.Versioning;

public class PersonV1 {

    private String Name;

    public PersonV1(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }
}
