package com.softserve.sprint13.service;


import com.softserve.sprint13.entity.Marathon;
        import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    User getUserById(Long id);

    User createOrUpdateUser(User user);

    List<User> getAllByRole(String role);

    boolean addUserToMarathon(User user, Marathon marathon);

    void deleteUserByIdFromMarathon(Long user_id,Long marathon_id);

    void deleteUserById(Long user_id);

    List<User> getAllByMarathon(Long marathon_id);

}
