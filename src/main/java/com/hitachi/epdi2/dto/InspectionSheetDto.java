package com.hitachi.epdi2.dto;

import com.hitachi.epdi2.entity.InspectionSheet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InspectionSheetDto {

    Long id;
    String modelName;
    String msn;
    Boolean isFinalSubmit;

    List<InspectionSheetContentDto> sheetContents  = new ArrayList<>();

    public void bind(InspectionSheet testingSheet) {
        testingSheet.setMsn(this.getMsn());
        testingSheet.setFinalSubmit(this.getIsFinalSubmit());
        testingSheet.setUpdatedAt(new Date());
    }

}
