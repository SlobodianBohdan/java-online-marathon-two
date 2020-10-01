package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;

public interface MarathonService {

    public List<Entity> getStudents();

    public List<String> getMentors();

    public StudentScore studentResult(String studentName);

    public List<StudentScore> allStudentsResult();

    public AverageScore studentAverage(String studentName);

    public List<AverageScore> allStudentsAverage();

    public MentorStudent mentorStudents(String mentorName);

}
