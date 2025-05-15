package com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransformationRequest {

    String resolutionNumber;

    Float result;
}
