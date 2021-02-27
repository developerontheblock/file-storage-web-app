package com.example.demo.controller;

import com.example.demo.response.FileResponse;
import com.example.demo.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

@Controller
public class FileController {

    private StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/publisher/listAll")
    public String listAllFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().map(
                path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(path.getFileName().toString())
                        .toUriString())
                .collect(Collectors.toList()));

        return "publisherPage";
    }

    @GetMapping("/userInfo/viewAll")
    public String viewAllFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().map(
                path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(path.getFileName().toString())
                        .toUriString())
                .collect(Collectors.toList()));

        return "userInfoPage";
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload-file")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String name = storageService.store(file);

        ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return "<!DOCTYPE HTML>\n" +
                "<!-- Include _menu.html -->\n" +
                "<th:block th:include=\"/_menu\"></th:block>\n" +
                "<head>\n" +
                "    <title>Upload</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<h1>Upload Successful!</h1>\n" +
                "</body>\n" +
                "<form>\n" +
                "    <input type=\"button\" value=\"Go back!\" onclick=\"history.back()\">\n" +
                "</form>\n" +
                "</html>";
    }
}

