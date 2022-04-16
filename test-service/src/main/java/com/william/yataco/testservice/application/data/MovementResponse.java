package com.william.yataco.testservice.application.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementResponse {

    private String id;
    private Double amount;
    private String dateCreated;
    private String type;
    private String description;
}
