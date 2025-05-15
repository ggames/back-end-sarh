package com.fich.sarh.Planthistory.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantHistoryResponse {

    Long id;

    PlantOfPosition plantOfPosition;

    PlantStatus plantStatus;

    String startResolution;

    Date startDate;

    String endResolution;

    Date endDate;
}
