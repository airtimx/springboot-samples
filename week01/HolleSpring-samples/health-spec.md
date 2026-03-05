# 系统健康检查和系统信息接口规格文档

## 1. 概述

本文档描述了系统健康检查和系统信息相关接口的规格说明，包括健康检查、系统信息、环境变量和系统指标等接口。

## 2. 健康检查接口

### 2.1 接口信息

- **接口名称**：健康检查接口
- **接口路径**：`GET /api/health`
- **描述**：返回项目的基本健康状态信息

### 2.2 请求示例

```bash
curl http://localhost:8081/api/health
```

### 2.3 响应示例

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "projectName": "HolleSpring-samples",
    "version": "1.0.0",
    "serverTime": "2026-03-05 14:30:00",
    "status": "UP"
  }
}
```

### 2.4 响应字段说明

| 字段名 | 类型 | 说明 |
|--------|------|------|
| projectName | String | 项目名称 |
| version | String | 项目版本号 |
| serverTime | String | 服务器当前时间（格式：yyyy-MM-dd HH:mm:ss） |
| status | String | 运行状态（UP: 正常，DOWN: 异常） |

---

## 3. 系统信息接口

### 3.1 接口信息

- **接口名称**：系统信息接口
- **接口路径**：`GET /api/system/info`
- **描述**：返回应用程序和运行环境的基本信息

### 3.2 请求示例

```bash
curl http://localhost:8081/api/system/info
```

### 3.3 响应示例

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "appName": "HolleSpring-samples",
    "javaVersion": "18.0.1",
    "osName": "Windows 11",
    "osVersion": "10.0",
    "profiles": "dev",
    "serverPort": 8081
  }
}
```

### 3.4 响应字段说明

| 字段名 | 类型 | 说明 |
|--------|------|------|
| appName | String | 应用名称 |
| javaVersion | String | Java 版本 |
| osName | String | 操作系统名称 |
| osVersion | String | 操作系统版本 |
| profiles | String | 当前激活的环境配置（多个用逗号分隔） |
| serverPort | Integer | 服务器端口 |

---

## 4. 环境变量接口

### 4.1 接口信息

- **接口名称**：环境变量接口
- **接口路径**：`GET /api/system/env`
- **描述**：返回部分安全的环境变量信息

### 4.2 请求示例

```bash
curl http://localhost:8081/api/system/env
```

### 4.3 响应示例

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "JAVA_HOME": "C:\\Program Files\\Java\\jdk-18",
    "JAVA_VERSION": "18.0.1",
    "OS_NAME": "Windows 11",
    "OS_ARCH": "amd64",
    "USER_NAME": "user",
    "USER_DIR": "F:\\SPRING BOOT\\springboot-samples\\week01\\HolleSpring-samples",
    "TEMP_DIR": "C:\\Users\\user\\AppData\\Local\\Temp",
    "APP_NAME": "HolleSpring-samples",
    "SERVER_PORT": "8081",
    "ACTIVE_PROFILES": "dev"
  }
}
```

### 4.4 响应字段说明

| 字段名 | 说明 |
|--------|------|
| JAVA_HOME | Java 安装目录 |
| JAVA_VERSION | Java 版本 |
| OS_NAME | 操作系统名称 |
| OS_ARCH | 操作系统架构 |
| USER_NAME | 用户名 |
| USER_DIR | 用户工作目录 |
| TEMP_DIR | 临时文件目录 |
| APP_NAME | 应用名称 |
| SERVER_PORT | 服务器端口 |
| ACTIVE_PROFILES | 当前激活的环境配置 |

---

## 5. 系统指标接口

### 5.1 接口信息

- **接口名称**：系统指标接口
- **接口路径**：``GET /api/system/metrics``
- **描述**：返回 JVM 内存、线程等系统指标

### 5.2 请求示例

```bash
curl http://localhost:8081/api/system/metrics
```

### 5.3 响应示例

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "memory": {
      "maxMemory": 4294967296,
      "totalMemory": 268435456,
      "freeMemory": 134217728,
      "usedMemory": 134217728
    },
    "threads": {
      "threadCount": 12,
      "peakThreadCount": 15
    },
    "runtime": {
      "javaVersion": "18.0.1",
      "javaVendor": "Oracle Corporation",
      "uptime": 120000
    }
  }
}
```

### 5.4 响应字段说明

#### memory（内存信息）

| 字段名 | 类型 | 单位 | 说明 |
|--------|------|------|------|
| maxMemory | Long | 字节 | JVM 最大可用内存 |
| totalMemory | Long | 字节 | JVM 当前分配的内存 |
| freeMemory | Long | 字节 | JVM 当前空闲内存 |
| usedMemory | Long | 字节 | JVM 当前已使用内存 |

#### threads（线程信息）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| threadCount | Integer | 当前活跃线程数 |
| peakThreadCount | Integer | 历史峰值线程数 |

#### runtime（运行时信息）

| 字段名 | 类型 | 单位 | 说明 |
|--------|------|------|------|
| javaVersion | String | - | Java 版本 |
| javaVendor | String | - | Java 供应商 |
| uptime | Long | 毫秒 | JVM 运行时长 |

---

## 6. 多环境配置

### 6.1 环境说明

| 环境 | 配置文件 | 端口 | 版本后缀 | 日志级别 |
|------|----------|------|----------|----------|
| 开发环境 | application-dev.properties | 8081 | -SNAPSHOT | DEBUG |
| 测试环境 | application-test.properties | 8082 | -RC | INFO |
| 生产环境 | application-prod.properties | 80 | -RELEASE | WARN |

### 6.2 激活环境

在 `application.properties` 中添加以下配置来激活对应环境：

```properties
# 激活开发环境
spring.profiles.active=dev

# 激活测试环境
# spring.profiles.active=test

# 激活生产环境
# spring.profiles.active=prod
```

### 6.3 启动命令

```bash
# 开发环境
java -jar app.jar --spring.profiles.active=dev

# 测试环境
java -jar app.jar --spring.profiles.active=test

# 生产环境
java -jar app.jar --spring.profiles.active=prod
```

---

## 7. 异常处理

### 7.1 异常响应格式

所有异常统一返回以下格式：

```json
{
  "code": 400,
  "msg": "错误描述信息",
  "data": null
}
```

### 7.2 异常类型

| 异常类型 | HTTP 状态码 | 说明 |
|----------|-------------|------|
| IllegalArgumentException | 400 | 非法参数异常 |
| IllegalAccessException | 403 | 非法访问异常 |
| NullPointerException | 500 | 空指针异常 |
| Exception | 500 | 其他未知异常 |

### 7.3 异常响应示例

```json
{
  "code": 400,
  "msg": "非法参数: 参数不能为空",
  "data": null
}
```

---

## 8. 测试用例

### 8.1 健康检查测试

```bash
# 测试健康检查接口
curl http://localhost:8081/api/health

# 预期结果：返回项目名称、版本、时间和状态
```

### 8.2 系统信息测试

```bash
# 测试系统信息接口
curl http://localhost:8081/api/system/info

# 预期结果：返回应用和系统基本信息
```

### 8.3 环境变量测试

```bash
# 测试环境变量接口
curl http://localhost:8081/api/system/env

# 预期结果：返回安全的环境变量
```

### 8.4 系统指标测试

```bash
# 测试系统指标接口
curl http://localhost:8081/api/system/metrics

# 预期结果：返回内存、线程和运行时指标
```

---

## 9. 版本历史

| 版本 | 日期 | 说明 |
|------|------|------|
| 1.0.0 | 2026-03-05 | 初始版本，实现基础接口 |

---

## 10. 注意事项

1. 所有接口均使用统一的响应格式 `ResultVO`
2. 环境变量接口仅返回安全的环境变量，不包含敏感信息
3. 系统指标接口返回的内存单位为字节
4. 建议在生产环境中使用 HTTPS 协议
5. 生产环境建议配置适当的访问控制和日志审计
