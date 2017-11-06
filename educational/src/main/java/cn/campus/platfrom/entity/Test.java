package cn.campus.platfrom.entity;

public class Test implements java.io.Serializable {
    private static final long serialVersionUID = -4036820812058123257L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
