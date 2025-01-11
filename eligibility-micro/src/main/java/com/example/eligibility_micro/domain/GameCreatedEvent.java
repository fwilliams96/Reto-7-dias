package com.example.eligibility_micro.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCreatedEvent {

    private Long id;

    private String name;
    private Integer userId;

}
