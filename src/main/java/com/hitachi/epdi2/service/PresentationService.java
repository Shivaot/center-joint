package com.hitachi.epdi2.service;

import com.hitachi.epdi2.constant.Sheets;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;

import static com.hitachi.epdi2.constant.Constant.PresentationConstant.*;
import static com.hitachi.epdi2.util.SheetUtils.getDisplayName;

@Service
public class PresentationService {

    private final ModelService modelService;

    public PresentationService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setupInspectionSheet(Model model, String modelName) {
        model.addAttribute(CHECK_SHEET_TITLE, "Create Inspection Sheet");
        model.addAttribute(SHEET_TYPE, Sheets.INSPECTION_SHEET.getSheetName());
        model.addAttribute(SHEET_HEADING, Sheets.INSPECTION_SHEET.getSheetNameView());
        model.addAttribute(IS_INSPECTION_SHEET, true);
        model.addAttribute(CONTEXT, "CREATE");
        model.addAttribute(MODEL_LIST, modelService.getAllModels());
        model.addAttribute("isRevisionSheet", false);
        model.addAttribute("modelName", Objects.nonNull(modelName) ? modelName.trim() : null);
        if (Objects.nonNull(modelName)) model.addAttribute("displayModelName", getDisplayName(modelName));
    }
}
