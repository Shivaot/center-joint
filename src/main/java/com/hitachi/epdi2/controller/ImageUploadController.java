package com.hitachi.epdi2.controller;

import com.hitachi.epdi2.dto.ImageGeneratedDetailDto;
import com.hitachi.epdi2.dto.ResponseDto;
import com.hitachi.epdi2.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("image")
public class ImageUploadController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseDto<ImageGeneratedDetailDto>> uploadImage(@RequestParam("file") MultipartFile file,
                                                                            @RequestParam(name = "sheetType") String sheetType,
                                                                            @RequestParam(name = "uuid") String uuid) {
        return ResponseEntity.ok(imageService.uploadImage(uuid, file, sheetType));
    }

    @GetMapping("/view")
    public ResponseEntity<InputStreamResource> getImage(@RequestParam(name = "sheetType") String sheetType,
                                                        @RequestParam(name = "uuid") String uuid) {
        return imageService.getImage(uuid, sheetType);
    }

}
