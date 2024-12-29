package com.hitachi.epdi2.service;

import com.hitachi.epdi2.constant.Sheets;
import com.hitachi.epdi2.dto.InspectionSheetDto;
import com.hitachi.epdi2.dto.ResponseDto;
import com.hitachi.epdi2.entity.InspectionSheet;
import com.hitachi.epdi2.entity.InspectionSheetContent;
import com.hitachi.epdi2.exception.ResourceNotFoundException;
import com.hitachi.epdi2.repository.InspectionSheetRepository;
import com.hitachi.epdi2.util.SheetUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.hitachi.epdi2.constant.Constant.APIMessage.CHECKSHEET_SAVE_MESSAGE;
import static com.hitachi.epdi2.constant.Constant.PresentationConstant.*;

@Service
@RequiredArgsConstructor
public class InspectionSheetService {

    private final InspectionSheetRepository inspectionSheetRepository;

    public ResponseDto<Long> save(InspectionSheetDto sheetDto) {
        InspectionSheet inspectionSheet;
        ModelMapper modelMapper = new ModelMapper();
        if (sheetDto.getId() != null) {
            inspectionSheet = inspectionSheetRepository.findById(sheetDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("CheckSheet not found with id: " + sheetDto.getId()));
            sheetDto.bind(inspectionSheet);
            updateSheetContent(sheetDto, inspectionSheet);
            inspectionSheetRepository.save(inspectionSheet);
        } else {
            inspectionSheet = new InspectionSheet();
            inspectionSheet.setModelName(sheetDto.getModelName());
            sheetDto.bind(inspectionSheet);
            sheetDto.getSheetContents().stream()
                    .map(content -> {
                        InspectionSheetContent sheetContent = modelMapper.map(content, InspectionSheetContent.class);
                        sheetContent.setUuid(UUID.randomUUID().toString());
                        return sheetContent;
                    }).forEach(inspectionSheet::addSheetContent);
            inspectionSheetRepository.save(inspectionSheet);
        }
        return new ResponseDto<>(CHECKSHEET_SAVE_MESSAGE, inspectionSheet.getId());
    }

    public void updateSheetContent(InspectionSheetDto sheetDto, InspectionSheet sheet) {
        sheetDto.getSheetContents().forEach(content -> {
            InspectionSheetContent inspectionSheetContent = sheet.getSheetContents().stream()
                    .filter(savedContent -> content.getId().equals(savedContent.getId()))
                    .findFirst().orElse(null);
            if (Objects.nonNull(inspectionSheetContent)) {
                inspectionSheetContent.setSeqSerialNumber(content.getSeqSerialNumber());
                inspectionSheetContent.setAssemblyFunction(content.getAssemblyFunction());
                inspectionSheetContent.setCheckPoint(content.getCheckPoint());
                inspectionSheetContent.setKeypoint(content.getKeypoint());
                inspectionSheetContent.setStatus(content.getStatus());
                inspectionSheetContent.setComment(content.getComment());
            }
        });
    }

    public void setupSheet(Long sheetId, Integer revisionNo, Model model) {
        InspectionSheet sheet;
        boolean isRevision = Objects.nonNull(revisionNo);
        long revNo = 0;
        if (isRevision) {
            sheet = inspectionSheetRepository.findRevision(sheetId, revisionNo)
                    .orElseThrow(() -> new ResourceNotFoundException("CheckSheet Not Found with id " + sheetId + " and revision " + revisionNo)).getEntity();
            revNo = computeRevisionNumber(sheetId, revisionNo, revNo);
        } else {
            sheet = inspectionSheetRepository.findById(sheetId)
                    .orElseThrow(() -> new ResourceNotFoundException("CheckSheet Not Found with id " + sheetId));
            model.addAttribute("revisions", inspectionSheetRepository.findRevisions(sheetId));
            model.addAttribute("isRevisionSheet", false);
            revNo = inspectionSheetRepository.findRevisions(sheetId).stream().count() + 1;
        }
        model.addAttribute(CHECK_SHEET_TITLE, VIEW_INSPECTION_SHEET);
        model.addAttribute("sheet", sheet);
        model.addAttribute(SHEET_TYPE, Sheets.INSPECTION_SHEET.getSheetName());
        model.addAttribute("isFinalSubmit", sheet.isFinalSubmit());
        model.addAttribute("revNo", revNo);
        model.addAttribute("context", "UPDATE");
        model.addAttribute("sheetId", sheetId);
        model.addAttribute("modelName", sheet.getModelName());
        model.addAttribute("displayModelName", SheetUtils.getDisplayName(sheet.getModelName()));
        model.addAttribute(SHEET_HEADING, Sheets.INSPECTION_SHEET.getSheetNameView());
    }

    private long computeRevisionNumber(Long sheetId, Integer revisionNo, long revNo) {
        List<Revision> sheetList = new ArrayList<>();
        inspectionSheetRepository.findRevisions(sheetId).forEach(sheetList::add);
        for (int i = 0; i < sheetList.size(); i++) {
            if (sheetList.get(i).getRevisionNumber().get() == revisionNo) {
                revNo = i + 1;
            }
        }
        return revNo;
    }
}
