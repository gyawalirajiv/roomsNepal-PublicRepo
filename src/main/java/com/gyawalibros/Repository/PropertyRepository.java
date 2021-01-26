package com.gyawalibros.Repository;

import com.gyawalibros.domain.Property;
import com.gyawalibros.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long> {
    Iterable<Property> findAllByUser(User user);

    Iterable<Property> findAllByType(String type);

    Iterable<Property> findAllByLocation(String location);
}
