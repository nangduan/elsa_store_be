package com.example.elsa_store.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String uploadHotelImage(MultipartFile file);
}
