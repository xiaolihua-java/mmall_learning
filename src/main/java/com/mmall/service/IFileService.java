package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by MAIBENBEN on 2018-07-21.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
