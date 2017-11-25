package com.powerrangers.backend.services;

import com.powerrangers.backend.repositories.StorageRepository;
import org.springframework.web.multipart.MultipartFile;

public class StorageServiceImpl implements IStorageService {

    public StorageServiceImpl(StorageRepository storageRepository) {

    }

    @Override
    public void get(String filename) {
    }

    @Override
    public void upload(MultipartFile file) {

    }

    @Override
    public void removeByFilename(String filename) {
    }
}
