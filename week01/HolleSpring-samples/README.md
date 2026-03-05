# HolleSpring-samples

基于 Spring Boot 的系统健康检查和系统信息监控项目。

## 项目简介

本项目是一个 Spring Boot 示例项目，实现了系统健康检查、系统信息查询、环境变量监控和 JVM 指标获取等功能。支持多环境配置（dev/test/prod），并提供统一的异常处理机制。

## 功能特性

- ✅ 系统健康检查接口
- ✅ 应用信息查询
- ✅ 安全环境变量返回
- ✅ JVM 指标监控（内存、线程、运行时）
- ✅ 多环境配置支持（dev/test/prod）
- ✅ 全局异常处理
- ✅ 统一响应格式

## 技术栈

- Java 18
- Spring Boot 3.5.11
- Maven
- Lombok（可选）

## 项目结构

```
HolleSpring-samples/
├── src/
│   ├── main/
│   │   ├── java/jiangyuze/example/hollespringsamples/
│   │   │   ├── HolleSpringSamplesApplication.java    # 主启动类
│   │   │   ├── controller/                             # 控制器层
│   │   │   │   ├── HelloController.java               # Hello 接口
│   │   │   │   ├── HealthController.java              # 健康检查接口
│   │   │   │   └── SystemInfoController.java          # 系统信息接口
│   │   │   ├── exception/                              # 异常处理
│   │   │   │   └── GlobalExceptionHandler.java        # 全局异常处理器
│   │   │   └── VO/                                    # 视图对象
│   │   │       ├── ResultVO.java                     # 统一响应格式
│   │   │       ├── HealthVO.java                     # 健康检查 VO
│   │   │       ├── SystemInfoVO.java                 # 系统信息 VO
│   │   │       └── MetricsVO.java                    # 指标 VO
│   │   └── resources/                                 # 资源文件
│   │       ├── application.properties                 # 主配置文件
│   │       ├── application-dev.properties             # 开发环境配置
│   │       ├── application-test.properties            # 测试环境配置
│   │       └── application-prod.properties             # 生产环境配置
│   └── test/                                          # 测试目录
├── health-spec.md                                      # 接口规格文档
└── README.md                                          # 项目说明文档
```

## 快速开始

### 环境要求

- JDK 18 或更高版本
- Maven 3.6+
- IDE（推荐 IntelliJ IDEA）

### 安装与运行

#### 方法 1：使用 IDE 运行

1. 克隆或下载项目
2. 使用 IntelliJ IDEA 打开项目
3. 等待 Maven 依赖下载完成
4. 运行 `HolleSpringSamplesApplication.java` 中的 main 方法

#### 方法 2：使用 Maven 命令运行

```bash
# 编译项目
mvn clean package

# 运行项目（默认环境）
mvn spring-boot:run

# 运行指定环境
mvn spring-boot:run -Dspring-boot.run.profiles=dev
mvn spring-boot:run -Dspring-boot.run.profiles=test
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

#### 方法 3：运行 JAR 包

```bash
# 编译打包
mvn clean package

# 运行 JAR 包
java -jar target/HolleSpring-samples-0.0.1-SNAPSHOT.jar

# 指定环境运行
java -jar target/HolleSpring-samples-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
java -jar target/HolleSpring-samples-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
java -jar target/HolleSpring-samples-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 接口文档

### 1. 健康检查接口

**接口地址：** `GET /api/health`

**功能描述：** 返回项目的基本健康状态信息

**请求示例：**
```bash
curl http://localhost:8081/api/health
```

**响应示例：**
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

### 2. 系统信息接口

**接口地址：** `GET /api/system/info`

**功能描述：** 返回应用程序和运行环境的基本信息

**请求示例：**
```bash
curl http://localhost:8081/api/system/info
```

**响应示例：**
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

### 3. 环境变量接口

**接口地址：** `GET /api/system/env`

**功能描述：** 返回部分安全的环境变量信息

**请求示例：**
```bash
curl http://localhost:8081/api/system/env
```

**响应示例：**
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

### 4. 系统指标接口

**接口地址：** `GET /api/system/metrics`

**功能描述：** 返回 JVM 内存、线程等系统指标

**请求示例：**
```bash
curl http://localhost:8081/api/system/metrics
```

**响应示例：**
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

### 5. Hello 接口

**接口地址：** `GET /api/hello`

**功能描述：** 测试接口，返回欢迎信息

**请求示例：**
```bash
curl http://localhost:8081/api/hello
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "data": "Hello Spring Boot"
}
```

## 环境配置

### 多环境说明

| 环境 | 配置文件 | 端口 | 版本后缀 | 日志级别 | 说明 |
|------|----------|------|----------|----------|------|
| 开发环境 | application-dev.properties | 8081 | -SNAPSHOT | DEBUG | 用于开发调试 |
| 测试环境 | application-test.properties | 8082 | -RC | INFO | 用于测试验证 |
| 生产环境 | application-prod.properties | 80 | -RELEASE | WARN | 用于正式生产 |

### 切换环境

在 `application.properties` 中设置：

```properties
# 激活开发环境
spring.profiles.active=dev

# 激活测试环境
# spring.profiles.active=test

# 激活生产环境
# spring.profiles.active=prod
```

或通过启动参数指定：

```bash
java -jar app.jar --spring.profiles.active=dev
```

## 异常处理

所有异常均通过 `GlobalExceptionHandler` 统一处理，返回格式如下：

```json
{
  "code": 400,
  "msg": "错误描述信息",
  "data": null
}
```

### 异常类型

| 异常类型 | HTTP 状态码 | 说明 |
|----------|-------------|------|
| IllegalArgumentException | 400 | 非法参数异常 |
| IllegalAccessException | 403 | 非法访问异常 |
| NullPointerException | 500 | 空指针异常 |
| Exception | 500 | 其他未知异常 |

## 响应格式说明

所有接口统一使用 `ResultVO` 响应格式：

```json
{
  "code": 200,        // 响应状态码（200 表示成功）
  "msg": "success",   // 响应消息
  "data": {}          // 响应数据
}
```

## 开发指南

### 添加新接口

1. 在 `controller` 包下创建新的 Controller 类
2. 使用 `@RestController` 和 `@RequestMapping` 注解
3. 返回类型使用 `ResultVO<T>` 包装数据

```java
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @GetMapping("/test")
    public ResultVO<String> test() {
        return ResultVO.success("测试成功");
    }
}
```

### 自定义异常

1. 创建自定义异常类继承 `RuntimeException`
2. 在 `GlobalExceptionHandler` 中添加对应的处理方法

```java
@ExceptionHandler(CustomException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ResultVO<String> handleCustomException(CustomException e) {
    return new ResultVO<>(400, e.getMessage(), null);
}
```

## 测试

### 运行测试

```bash
# 运行所有测试
mvn test

# 运行指定测试类
mvn test -Dtest=HolleSpringSamplesApplicationTests
```

### 接口测试

使用 curl 或 Postman 测试接口：

```bash
# 健康检查
curl http://localhost:8081/api/health

# 系统信息
curl http://localhost:8081/api/system/info

# 环境变量
curl http://localhost:8081/api/system/env

# JVM 指标
curl http://localhost:8081/api/system/metrics
```

## 常见问题

### 1. 端口被占用

**问题：** 启动时报错 `Address already in use`

**解决：**
- 修改对应环境的 `server.port` 配置
- 或者关闭占用端口的服务

### 2. 找不到主类

**问题：** 运行时报错 `找不到或无法加载主类`

**解决：**
- 检查 IDE 运行配置中的 Main class 路径是否正确
- 正确路径应为：`jiangyuze.example.hollespringsamples.HolleSpringSamplesApplication`
- 清理 IDE 缓存：File -> Invalidate Caches -> Invalidate and Restart

### 3. Maven 命令不可用

**问题：** 运行 `mvn` 命令提示找不到命令

**解决：**
- 使用 IDE 内置的 Maven 工具
- 或配置 Maven 环境变量

## 项目文档

- [接口规格文档](health-spec.md) - 详细的接口说明文档

## 贡献指南

欢迎提交 Issue 和 Pull Request！

## 许可证

本项目仅供学习交流使用。

## 联系方式

如有问题，请提交 Issue 或联系项目维护者。

---

**项目版本：** 1.0.0

**最后更新：** 2026-03-05
