package com.hitachi.epdi2.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Shiva Created on 29/12/24
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationStartupConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment env;

    public static String FILE_UPLOAD_PATH;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        FILE_UPLOAD_PATH = env.getProperty("file.upload.path");
    }
}
