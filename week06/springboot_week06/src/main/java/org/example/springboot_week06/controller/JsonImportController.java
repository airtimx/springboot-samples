package org.example.springboot_week06.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.springboot_week06.entity.JsonImport;
import org.example.springboot_week06.service.JsonImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonImportController {

    @Autowired
    private JsonImportService jsonImportService;

    @GetMapping("/json-import/list")
    public Page<JsonImport> getList(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {
        return jsonImportService.getList(pageNo, pageSize, keyword);
    }

    @GetMapping("/json-import/detail")
    public JsonImport getDetail(@RequestParam Long id) {
        return jsonImportService.getDetail(id);
    }
}
