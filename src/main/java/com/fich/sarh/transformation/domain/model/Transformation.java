package com.fich.sarh.transformation.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transformation {

    Long id;

    String resolutionNumber;

    Float result;
}
