package com.softserve.sprint13.repository;

import com.softserve.sprint13.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getAllByRole(User.Role role);

    @Query(value =
            "select u.* from users as u\n" +
                    "                    inner join marathon_user as mu\n" +
                    "                    on mu.marathon_id = :marathonId\n" +
                    "                    and u.id = mu.user_id ",
            nativeQuery = true)
    List<User> getAllByMarathonId(Long marathonId);
}
