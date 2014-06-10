package ua.avekshin.web.optimization.ideatransfer.backend.model;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 05.01.14
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class PairEntity {
    private int id;
    private String name;

    public PairEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
