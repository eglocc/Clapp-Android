package clappapp.club.clapp.model;

import java.io.Serializable;

public class SoftUser extends User implements Serializable {

    public SoftUser(String name) {
        super(name);
    }

    public SoftUser(String name, String email) {
        super(name, email);
    }

}
