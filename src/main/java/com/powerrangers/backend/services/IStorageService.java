package com.powerrangers.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IStorageService {

    void get(String filename);

    void upload(MultipartFile file);

    void removeByFilename(String filename);
}
