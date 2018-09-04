package com.itemconfiguration.utils;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class WebUtils {
    public static final String HEADER_ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String HEADER_FILENAME = "Filename";

    public static ResponseEntity<ByteArrayResource> stringToDownloadFile(String data, String fileName) {
        if (data == null) {
            throw new IllegalArgumentException("[data] parameter should no be null");
        }
        ByteArrayResource resource = new ByteArrayResource(data.getBytes());
        return ResponseEntity.ok()
                .header(WebUtils.HEADER_ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION, WebUtils.HEADER_FILENAME)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + fileName)
                .header(WebUtils.HEADER_FILENAME, fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }

}
