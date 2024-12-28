package com.hitachi.epdi2.repository;

import com.hitachi.epdi2.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Transactional(readOnly = true)
    long countByModelName(String modelName);
}
