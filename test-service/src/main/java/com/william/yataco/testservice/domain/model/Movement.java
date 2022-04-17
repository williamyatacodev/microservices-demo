package com.william.yataco.testservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement {

    private String id;
    private Double amount;
    private Double balance;
    private String customDate;
    private String customDescription;
    private String date;
    private String dateCreated;
    private boolean deleted;
    private String description;
    private boolean duplicated;
    private boolean hasConcepts;
    private boolean inResume;
    private String lastUpdated;
    private String type;
    private Account account;
    private Account category;

}
