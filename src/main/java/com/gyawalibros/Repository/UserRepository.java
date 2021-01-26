package com.gyawalibros.Repository;

import com.gyawalibros.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findAll();

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);
}
