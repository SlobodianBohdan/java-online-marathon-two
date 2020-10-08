package com.softserve.sprint13.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "progress")
public class Progress {

    public enum TaskStatus {
        PASS, FAIL, PENDING;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "started")
    private LocalDate started;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDate updated;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User trainee;

   @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", started=" + started +
                ", status=" + status +
                ", updated=" + updated +
                ", taskId=" + task.getId() +
                ", traineeId=" + trainee.getId() +
                '}';
    }

}
