package com.pragmafood.talentpool.square.infrastructure.output.feign.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResponse {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String role;

}
