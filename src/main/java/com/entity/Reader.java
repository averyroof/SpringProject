package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private Integer age;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issuing;

    public Reader() {
    }

    public Reader(String fullName, Integer age) {
        this.fullName = fullName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Issue> getIssuing() {
        return issuing;
    }

    public void setIssuing(List<Issue> issuing) {
        this.issuing = issuing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id) &&
                Objects.equals(fullName, reader.fullName) &&
                Objects.equals(age, reader.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, age);
    }
}
