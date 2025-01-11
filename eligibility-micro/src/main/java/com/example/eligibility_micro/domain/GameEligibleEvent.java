package com.example.eligibility_micro.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameEligibleEvent {
    private Long id;

    private String name;
    private Integer userId;
    private boolean isEligible;
}
