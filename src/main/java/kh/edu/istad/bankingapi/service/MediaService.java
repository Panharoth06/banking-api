package kh.edu.istad.bankingapi.service;

import kh.edu.istad.bankingapi.domain.Media;
import kh.edu.istad.bankingapi.dto.MediaResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    MediaResponse upload(MultipartFile file);

    List<MediaResponse> upload(List<MultipartFile> files);

    void deleteByName(String fileName);

    ResponseEntity<Resource> downloadByName(String fileName);

}
