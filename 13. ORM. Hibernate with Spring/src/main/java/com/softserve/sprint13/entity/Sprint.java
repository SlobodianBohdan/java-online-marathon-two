package com.softserve.sprint13.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @CreationTimestamp
    @Column(name = "finish")
    private LocalDate finish;

    @NotNull
    @CreationTimestamp
    @Column(name = "startDate")
    private LocalDate startDate;

    @NotNull
    @Column(name = "title")
    private String title;

    @ManyToOne
    private Marathon marathon;

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", finish=" + finish +
                ", start_date=" + startDate +
                ", title='" + title + '\'' +
                ", marathonId=" + marathon.getId() +
                '}';
    }

}
