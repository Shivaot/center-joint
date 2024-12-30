package com.hitachi.epdi2.service;

import com.hitachi.epdi2.dto.ImageGeneratedDetailDto;
import com.hitachi.epdi2.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class ImageService {

    private Environment environment;
    private static final String IMAGE_PATH = "file.upload.path";

    @SneakyThrows
    public ResponseEntity<InputStreamResource> getImage(String uuid, String sheetType) {
        File file = new File(environment.getProperty(IMAGE_PATH) + File.separator, sheetType + File.separator + uuid + ".png");
        byte[] bytes = Files.readAllBytes(Paths.get(file.getPath()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "filename=" + file.getName());
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("image/png")).body(resource);
    }

    public ResponseDto<ImageGeneratedDetailDto> uploadImage(String uuid, MultipartFile file, String sheetType) {
        if (Objects.isNull(uuid) || uuid.isEmpty() || uuid.equalsIgnoreCase("undefined")) {
            uuid = UUID.randomUUID().toString();
        }

        File newFile = new File(environment.getProperty(IMAGE_PATH) + File.separator, sheetType + File.separator + uuid + ".png");

        try {
            File parentDir = newFile.getParentFile();

            if (!parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("Directory created: " + parentDir.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + parentDir.getAbsolutePath());
                }
            }

            if (!newFile.exists()) {
                newFile.createNewFile();
            }

            file.transferTo(newFile);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return new ResponseDto<>("Image Generated Successfully", new ImageGeneratedDetailDto(uuid));
    }
}
