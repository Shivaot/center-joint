package com.hitachi.epdi2.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InspectionSheetContentDto {

    Long id;
    String seqSerialNumber;
    String assemblyFunction;
    String checkPoint;
    String keypoint;
    String comment;
    String status;
    String uuid;
}
