package kh.edu.istad.bankingapi.controller;

import kh.edu.istad.bankingapi.dto.MediaResponse;
import kh.edu.istad.bankingapi.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/single")
    public MediaResponse upload(@RequestPart MultipartFile file) {
        return mediaService.upload(file);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/multiple")
    public List<MediaResponse> upload(@RequestPart("files") List<MultipartFile> files) {
        return mediaService.upload(files);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String name) {
        mediaService.deleteByName(name);
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<Resource> downloadByName(@PathVariable String name) {
        return mediaService.downloadByName(name);
    }

}
