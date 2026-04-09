package org.example.springboot_week06.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springboot_week06.entity.JsonImport;
import org.example.springboot_week06.service.JsonImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JsonImportServiceImpl implements JsonImportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Page<JsonImport> getList(Integer pageNo, Integer pageSize, String keyword) {
        Page<JsonImport> page = new Page<>(pageNo, pageSize);

        List<String> columns = getMainTableColumns();
        String whereSql = "";
        List<Object> countParams = new ArrayList<>();
        if (keyword != null && !keyword.isBlank() && !columns.isEmpty()) {
            String concatExpr = columns.stream()
                    .map(column -> "CAST(`" + column + "` AS CHAR)")
                    .collect(Collectors.joining(", "));
            whereSql = " WHERE CONCAT_WS(' ', " + concatExpr + ") LIKE ?";
            countParams.add("%" + keyword.trim() + "%");
        }

        Long total = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM `main_table`" + whereSql,
                countParams.toArray(),
                Long.class
        );
        page.setTotal(total == null ? 0L : total);

        int offset = Math.max(0, (pageNo - 1) * pageSize);
        List<Object> listParams = new ArrayList<>(countParams);
        listParams.add(offset);
        listParams.add(pageSize);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT * FROM `main_table`" + whereSql + " LIMIT ?, ?",
                listParams.toArray()
        );

        List<JsonImport> records = rows.stream().map(this::toEntity).toList();
        page.setRecords(records);
        return page;
    }

    @Override
    public JsonImport getDetail(Long id) {
        if (id == null) {
            return null;
        }
        List<String> columns = getMainTableColumns();
        boolean hasId = columns.contains("id");
        boolean hasMainId = columns.contains("main_id");
        if (!hasId && !hasMainId) {
            return null;
        }

        String sql;
        List<Object> params = new ArrayList<>();
        if (hasId && hasMainId) {
            sql = "SELECT * FROM `main_table` WHERE `id` = ? OR `main_id` = ? LIMIT 1";
            params.add(id);
            params.add(id);
        } else if (hasId) {
            sql = "SELECT * FROM `main_table` WHERE `id` = ? LIMIT 1";
            params.add(id);
        } else {
            sql = "SELECT * FROM `main_table` WHERE `main_id` = ? LIMIT 1";
            params.add(id);
        }

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params.toArray());
        if (rows.isEmpty()) {
            return null;
        }
        return toEntity(rows.get(0));
    }

    private List<String> getMainTableColumns() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SHOW COLUMNS FROM `main_table`");
        return result.stream()
                .map(item -> item.get("Field"))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .toList();
    }

    private JsonImport toEntity(Map<String, Object> row) {
        JsonImport entity = new JsonImport();
        entity.setId(getLong(row, "id", "main_id"));
        entity.setJsonData(toJson(row));
        entity.setCreateTime(getDateTime(row, "create_time", "createTime", "created_at"));
        entity.setUpdateTime(getDateTime(row, "update_time", "updateTime", "updated_at"));
        return entity;
    }

    private Long getLong(Map<String, Object> row, String... keys) {
        for (String key : keys) {
            Object value = row.get(key);
            if (value instanceof Number number) {
                return number.longValue();
            }
            if (value instanceof String text) {
                try {
                    return Long.parseLong(text);
                } catch (NumberFormatException ignored) {
                    // ignore and continue to try next key
                }
            }
        }
        return null;
    }

    private LocalDateTime getDateTime(Map<String, Object> row, String... keys) {
        for (String key : keys) {
            Object value = row.get(key);
            if (value instanceof Timestamp ts) {
                return ts.toLocalDateTime();
            }
            if (value instanceof LocalDateTime dateTime) {
                return dateTime;
            }
        }
        return null;
    }

    private String toJson(Map<String, Object> row) {
        try {
            return objectMapper.writeValueAsString(row);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
