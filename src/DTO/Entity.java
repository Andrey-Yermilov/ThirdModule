package DTO;

import java.io.Serializable;

public abstract class Entity implements Cloneable, Serializable {
    private int id;

    public Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
