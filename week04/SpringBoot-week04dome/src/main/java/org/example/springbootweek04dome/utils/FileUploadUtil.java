package org.example.springbootweek04dome.utils;

import lombok.extern.slf4j.Slf4j;
import org.example.springbootweek04dome.commom.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class FileUploadUtil {

    private static String UPLOAD_DIR;
    private static boolean dirInited = false;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
            ".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp",
            ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx",
            ".txt", ".md", ".csv",
            ".zip", ".rar", ".7z",
            ".json", ".xml"
    );

    // 核心修复：Windows 路径兼容
    private static void initUploadDir() {
        if (dirInited)
        {
            return;
        }

        try {
            // 使用相对路径 ./upload，与 WebMvcConfig 中的资源路径对应
            Path uploadPath = Paths.get("upload");
            Files.createDirectories(uploadPath);

            UPLOAD_DIR = uploadPath.toString() + File.separator;
            log.info("上传目录初始化成功: {}", UPLOAD_DIR);
            dirInited = true;
        } catch (Exception e) {
            log.error("上传目录初始化失败", e);
            throw new RuntimeException("创建上传目录失败", e);
        }
    }

    public static String upload(MultipartFile file) throws IOException {
        initUploadDir();

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new BusinessException(400, "文件名不能为空");
        }

        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(suffix)) {
            throw new BusinessException(400, "不支持的文件类型: " + suffix);
        }

        String fileName = UUID.randomUUID() + suffix;
        File dest = new File(UPLOAD_DIR + fileName);
        file.transferTo(dest);
        return fileName;
    }
}