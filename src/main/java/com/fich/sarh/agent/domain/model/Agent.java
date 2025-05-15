package com.fich.sarh.agent.domain.model;

import com.fich.sarh.common.DocumentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Agent {

    private Long id;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.ORDINAL)
    private DocumentType documenttype;

    private String document;

    private LocalDate birthdate;

    private LocalDate leavingdate;

    private boolean isDeceased;

    private String file;

    private String address;

    private String phone;

    private String email;
    
}
