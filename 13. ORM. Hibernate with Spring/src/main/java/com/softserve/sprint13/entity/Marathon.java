package com.softserve.sprint13.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "marathon")
public class Marathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @ManyToMany(mappedBy = "marathonUsers")
    @Fetch(value = FetchMode.SUBSELECT)
    List<User> users;

    @Override
    public String toString() {
        return "Marathon{" +
                "title='" + title +
                "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marathon)) return false;
        Marathon marathon = (Marathon) o;
        return getTitle().equals(marathon.getTitle());
    }

    @Override
    public int hashCode() {
        return 31 * (getTitle() != null ? getTitle().hashCode() : 0);
    }
}
