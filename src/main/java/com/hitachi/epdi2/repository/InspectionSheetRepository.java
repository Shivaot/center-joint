package com.hitachi.epdi2.repository;

import com.hitachi.epdi2.entity.InspectionSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface InspectionSheetRepository extends RevisionRepository<InspectionSheet, Long, Integer>,
        JpaRepository<InspectionSheet, Long> {

    Optional<InspectionSheet> findByMsn(String trim);
}
