package com.softserve.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.service.DataService;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    private List<Entity> students;
    private List<Entity> mentors;
    private List<Entity> sprints;
    private List<Communication> communication;
    private List<Solution> solution;

    public void addStudent(String studentName) {
        if (!studentName.isEmpty()) {
            students.add(new Entity(studentName));
        }
    }

    public DataServiceImpl() {
        students = new ArrayList<>();
        mentors = new ArrayList<>();
        sprints = new ArrayList<>();
        communication = new ArrayList<>();
        solution = new ArrayList<>();
    }

    public void addMentor(String mentorName) {
        // TODO for mentors
    }

    public void addSprint(String sprintName) {
        // TODO for sprints
    }

    public void addCommunication(String studentName, String mentorName) {
        // TODO for communication
    }

    public void addSolution(String studentName, String sprintName, int score) {
        // TODO for solution
    }

    @Override
    public List<Entity> getStudents() {
        students.add(new Entity("Mari"));
        return students;
    }

    public Entity getStudent(int id) {
        return students.stream().filter(student->student.getId() == id).findFirst().orElse(null);
    }


    // TODO
}
