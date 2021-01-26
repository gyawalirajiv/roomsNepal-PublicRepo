package com.gyawalibros.Repository;

import com.gyawalibros.domain.PhotoURL;
import com.gyawalibros.domain.Property;
import org.springframework.data.repository.CrudRepository;

public interface PhotoURLRepository extends CrudRepository<PhotoURL, Long> {
    @Override
    Iterable<PhotoURL> findAll(Iterable<Long> iterable);

    Iterable<PhotoURL> findAllByProperty(Property property);
}
