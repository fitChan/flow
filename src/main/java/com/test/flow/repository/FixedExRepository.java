package com.test.flow.repository;

import com.test.flow.domain.CustomExtension;
import com.test.flow.domain.FixedExtension;
import com.test.flow.domain.enums.FixedEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedExRepository extends JpaRepository<FixedExtension, Long> {

    List<FixedExtension> findAllByFlagTrue();

//    FixedExtension findByNames(FixedEnum names);
    FixedExtension findByNames(FixedEnum names);

}

