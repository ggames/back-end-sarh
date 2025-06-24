package com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransformationResponse {

    Long id;

    String resolutionNumber;

    Float result;
}
