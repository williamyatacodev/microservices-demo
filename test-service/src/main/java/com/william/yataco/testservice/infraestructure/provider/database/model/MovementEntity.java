package com.william.yataco.testservice.infraestructure.provider.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="movements")
public class MovementEntity {

    @Id
    private String id;
    private Double amount;
    private String dateCreated;
    private String type;
    private String description;
    private OffsetDateTime lastUpdated;
}
