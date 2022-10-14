package com.test.flow.repository;

import com.test.flow.domain.CustomExtension;
import com.test.flow.domain.enums.FixedEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomExRepository extends JpaRepository<CustomExtension, Long> {

    List<CustomExtension> findTop200ByFlagTrueOrderByModifiedAtDesc();
    Optional<CustomExtension> findByNames(String names);
}

