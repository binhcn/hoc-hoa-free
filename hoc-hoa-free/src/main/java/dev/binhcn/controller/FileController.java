package dev.binhcn.controller;

import dev.binhcn.service.ImageResourceHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
public class FileController {

    @Resource
    private ImageResourceHandler imageResourceHandler;

    @GetMapping("/download/{fileName}")
    public void download(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, @PathVariable String fileName)
            throws ServletException, IOException {
        File file = new File(
            "/Users/lap14178/Documents/hoc-hoa-free/hoc-hoa-free/images/" + fileName);
        httpServletRequest.setAttribute(ImageResourceHandler.ATTRIBUTE_FILE, file);
        imageResourceHandler.handleRequest(httpServletRequest, httpServletResponse);
    }

    @GetMapping(value = "/image/{imageName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> downloadLog(@PathVariable String imageName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(
            "/Users/lap14178/Documents/hoc-hoa-free/hoc-hoa-free/images/" + imageName);
        StreamingResponseBody body = outputStream -> FileCopyUtils.copy(inputStream, outputStream);
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment;filename=" + imageName)
            .body(body);
    }
}