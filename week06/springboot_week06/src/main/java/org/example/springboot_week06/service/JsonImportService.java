package org.example.springboot_week06.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.springboot_week06.entity.JsonImport;

public interface JsonImportService {

    Page<JsonImport> getList(Integer pageNo, Integer pageSize, String keyword);

    JsonImport getDetail(Long id);
}
