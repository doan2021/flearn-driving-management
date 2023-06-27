package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {
}
