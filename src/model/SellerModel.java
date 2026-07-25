package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SellerModel implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double baseSalary;

    private DepartmentModel dm;

    public SellerModel(){}

    public SellerModel(Integer id, String name, String email, Date birthDate, Double baseSalary, DepartmentModel dm) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.dm = dm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public DepartmentModel getDm() {
        return dm;
    }

    public void setDm(DepartmentModel dm) {
        this.dm = dm;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SellerModel that = (SellerModel) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getBaseSalary(), that.getBaseSalary()) && Objects.equals(getDm(), that.getDm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getBirthDate(), getBaseSalary(), getDm());
    }

    @Override
    public String toString() {
        return "SellerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", baseSalary=" + baseSalary +
                ", dm=" + dm +
                '}';
    }
}
