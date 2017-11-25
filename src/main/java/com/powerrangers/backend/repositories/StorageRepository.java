package com.powerrangers.backend.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface StorageRepository {

    void get(String filename);

    void upload(MultipartFile file);

    void removeByFilename(String filename);
}
