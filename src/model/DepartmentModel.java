package model;

import java.io.Serializable;
import java.util.Objects;

public class DepartmentModel implements Serializable {

    private Integer id;
    private String name;

    public DepartmentModel() {}

    public DepartmentModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentModel that = (DepartmentModel) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DepartmentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
