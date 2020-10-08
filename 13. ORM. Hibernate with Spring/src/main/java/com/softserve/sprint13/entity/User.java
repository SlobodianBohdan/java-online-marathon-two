package com.softserve.sprint13.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class User {
    public enum Role {
        MENTOR, TRAINEE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @Pattern(regexp = "[_a-zA-Z0-9\\-]+@[a-z]+.[a-z]{2,3}", message = "Please provide a valid email address")
    @Column(name = "email")
    private String email;

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    @Column(name = "first_name")
    private String firstName;


    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "marathon_user", joinColumns = {@JoinColumn(name = "user_id")},//type of relation, joinColumns with id
            inverseJoinColumns = {@JoinColumn(name = "marathon_id")})
    private List<Marathon> marathonUsers;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getPassword().equals(user.getPassword()) &&
                getRole() == user.getRole() &&
                getMarathonUsers().equals(user.getMarathonUsers());
    }

    @Override
    public int hashCode() {
        return 31 * (int)(getId() +
                + (getEmail() != null ? getEmail().hashCode() : 0) +
                + (getFirstName() != null ? getFirstName().hashCode() : 0) +
                + (getLastName() != null ? getLastName().hashCode() : 0) +
                + (getPassword() != null ? getPassword().hashCode() : 0) +
                + (getRole() != null ? getRole().hashCode() : 0) +
                + (getMarathonUsers() != null ? getMarathonUsers().hashCode() : 0));
    }
}
