package com.hitachi.epdi2.controller;

import com.hitachi.epdi2.service.PresentationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("checksheet")
public class CheckSheetController {

    private final PresentationService presentationService;

    @GetMapping("/inspection/create")
    String createInspection(Model model, @RequestParam(required = false, value = "") String modelName) {
        presentationService.setupInspectionSheet(model, modelName);
        return "checkSheet/inspection";
    }

}
