package com.start.springBoot.dto;

//lombok

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
//@Data

public class TestDTO {
    private Integer id;
    private String name;
    private Integer age;
}
