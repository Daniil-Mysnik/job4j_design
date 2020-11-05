package ru.job4j.concurrent.nonblockingcache;

public class Base {
    private int id;
    private int version;

    public Base(int id) {
        this.id = id;
        this.version = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
