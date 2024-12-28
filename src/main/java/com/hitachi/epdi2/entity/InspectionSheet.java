package com.hitachi.epdi2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hitachi.epdi2.config.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Getter
@Setter
public class InspectionSheet extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String modelName;

    @Column(unique = true, nullable = false)
    private String msn;

    private boolean isFinalSubmit;

    private Date finalSubmitDate;

    private String createdBy;

    @JsonManagedReference
    @OneToMany(mappedBy = "inspectionSheet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InspectionSheetContent> sheetContents = new ArrayList<>();

    public void addSheetContent(InspectionSheetContent sheetContent) {
        sheetContents.add(sheetContent);
        sheetContent.setInspectionSheet(this);
    }

}
