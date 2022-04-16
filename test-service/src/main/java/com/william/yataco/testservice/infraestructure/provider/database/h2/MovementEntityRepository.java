package com.william.yataco.testservice.infraestructure.provider.database.h2;

import com.william.yataco.testservice.infraestructure.provider.database.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementEntityRepository extends JpaRepository<MovementEntity,Integer> {
}
