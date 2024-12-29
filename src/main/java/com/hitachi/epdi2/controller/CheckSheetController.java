package com.hitachi.epdi2.controller;

import com.hitachi.epdi2.dto.InspectionSheetDto;
import com.hitachi.epdi2.dto.ResponseDto;
import com.hitachi.epdi2.entity.InspectionSheet;
import com.hitachi.epdi2.service.InspectionSheetService;
import com.hitachi.epdi2.service.PresentationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("checksheet")
public class CheckSheetController {

    private final PresentationService presentationService;
    private final InspectionSheetService inspectionSheetService;

    @GetMapping("/inspection/create")
    String createInspection(Model model, @RequestParam(required = false, value = "") String modelName) {
        presentationService.setupInspectionSheet(model, modelName);
        return "checkSheet/inspection";
    }

    @GetMapping("/inspection/show/{id}")
    public String showPerformanceSheet(@PathVariable Long id, Model model, @RequestParam(value = "rPayload", required = false) Integer revisionNo) {
        inspectionSheetService.setupSheet(id, revisionNo, model);
        return "checkSheet/inspection";
    }

    @PostMapping("/inspection/save")
    public ResponseEntity<ResponseDto<Long>> saveInspectionSheet(@RequestBody InspectionSheetDto sheetDto) {
        ResponseDto<Long> responseDto = inspectionSheetService.save(sheetDto);
        return ResponseEntity.ok(responseDto);
    }
}
