package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.exception.EntityNotFoundException;
import com.softserve.sprint13.repository.MarathonRepository;
import com.softserve.sprint13.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {

    SprintRepository sprintRepository;

    MarathonRepository marathonRepository;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository, MarathonRepository marathonRepository) {
        this.sprintRepository = sprintRepository;
        this.marathonRepository = marathonRepository;
    }

    @Override
    public List<Sprint> getSprintsByMarathonId(Long idMarathon) {
        return sprintRepository.findAllByMarathonId(idMarathon);
    }

    @Override
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {
        try {
            findByIdOrThrowException(marathonRepository, marathon.getId());
            sprint.setMarathon(marathon);
            sprintRepository.save(sprint);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean updateSprint(Sprint sprint) {
        try {
            findByIdOrThrowException(sprintRepository, sprint.getId());
            sprintRepository.save(sprint);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    @Override
    public Sprint getSprintById(Long idSprint) {
        return findByIdOrThrowException(sprintRepository, idSprint);
    }

    @Override
    public Sprint create(Sprint sprint) {
        Sprint newSprint = sprintRepository.save(sprint);
        return newSprint;
    }

    private <T> T findByIdOrThrowException(JpaRepository<T, Long> repository, Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity is not found!"));
    }
}
