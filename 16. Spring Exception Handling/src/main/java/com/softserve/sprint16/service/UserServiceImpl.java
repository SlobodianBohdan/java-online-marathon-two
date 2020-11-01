package com.softserve.sprint16.service;

import com.softserve.sprint16.entity.Marathon;
import com.softserve.sprint16.entity.User;
import com.softserve.sprint16.exception.CreateException;
import com.softserve.sprint16.exception.EntityNotFoundException;
import com.softserve.sprint16.repository.MarathonRepository;
import com.softserve.sprint16.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private MarathonRepository marathonRepository;

    @Autowired
    public UserServiceImpl(MarathonRepository marathonRepository, UserRepository userRepository) {
        this.marathonRepository = marathonRepository;
        this.userRepository = userRepository;

    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(("No user exist for given id" + id)));

    }

    @Override
    public void deleteUserByIdFromMarathon(Long user_id, Long marathon_id) {
        Optional<Marathon> marathonFromBd = marathonRepository.findById(marathon_id);
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent() && marathonFromBd.isPresent()) {
            marathonFromBd.get().getUsers().remove(user.get());
            marathonRepository.save(marathonFromBd.get());
            user.get().getMarathons().remove(marathonFromBd.get());
            userRepository.save(user.get());
        } else throw new EntityNotFoundException("No user exist for given id");
    }

    @Override
    public void deleteUserById(Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            user.get().getMarathons().forEach(marathon -> {
                marathon.getUsers().remove(user.get());
                marathonRepository.save(marathon);
            });
            userRepository.delete(user.get());
        } else throw new EntityNotFoundException("No user exist for given id");
    }

    @Override
    public List<User> getAllByMarathon(Long marathon_id) {
        Optional<Marathon> marathonFromBd = marathonRepository.findById(marathon_id);
        if (marathonFromBd.isPresent()) {
            return userRepository.getAllByMarathonId(marathon_id);
        } else throw new EntityNotFoundException("No user exist for given id");
    }

    @Override
    public User createOrUpdateUser(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<User> getAllByRole(String role) {
        return userRepository.getAllByRole(User.Role.valueOf(role.toUpperCase()));

    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        Optional<Marathon> marathonFromBd = marathonRepository.findById(marathon.getId());
        if (marathonFromBd.isPresent()) {
            marathonFromBd.get().getUsers().add(user);
            marathonRepository.save(marathonFromBd.get());
            return true;
        }
        return false;
    }

}
