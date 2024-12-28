package com.hitachi.epdi2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hitachi.epdi2.bootstrap.ApplicationStartupConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.File;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Getter
@Setter
public class InspectionSheetContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seqSerialNumber;
    private String assemblyFunction;
    @Lob
    private String checkPoint;
    private String keypoint;
    private String comment;
    private String status;
    private String uuid;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sheet_id")
    private InspectionSheet inspectionSheet;

    public boolean isImageUploaded() {
        File file = new File(ApplicationStartupConfig.FILE_UPLOAD_PATH + File.separator
                + "INSPECTION_SHEET" + File.separator + uuid + ".png");
        return file.exists();
    }

}
