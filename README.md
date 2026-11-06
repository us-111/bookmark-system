# 多用户书签管理系统 (Multi-user Bookmark Management System)

这是一个基于 Spring Boot 3 开发的轻量级多用户后台 API 项目。旨在解决个人书签分类管理及多用户数据隔离的问题。

##  技术栈
*   **核心框架**：Java 17, Spring Boot 3
*   **持久层**：MyBatis-Plus, MySQL
*   **安全与鉴权**：JWT (无状态登录), BCrypt (密码加密)
*   **其他**：Spring AOP (操作日志), 全局异常处理 (@RestControllerAdvice)

##  核心功能
1.  **用户模块**：注册、登录，密码采用 BCrypt 密文存储。
2.  **权限校验**：自定义 JWT 拦截器，校验请求头中的 Token。
3.  **数据隔离（防越权）**：重写 MyBatis-Plus 的 CRUD 逻辑，强制在 SQL 层拼接 `user_id` 条件，确保用户只能操作自己的数据。
4.  **操作日志**：通过自定义 `@Log` 注解与 Spring AOP 切面，无侵入式记录用户的增删改操作。
5.  **统一规范**：封装 `Result` 统一返回对象，并使用全局异常处理器拦截参数校验及业务异常。

##  快速启动
1. 克隆项目到本地：
   `git clone https://github.com/你的用户名/bookmark-system.git`
2. 导入数据库脚本：执行项目根目录下的 `Query.sql`。
3. 修改配置：在 `src/main/resources/application.properties` 中配置你自己的 MySQL 账号密码。
4. 运行 `BookMarkApplication.java` 启动项目（默认端口 8080）。

##  接口说明 (部分)
*   `POST /api/users/login` : 用户登录，返回 JWT Token。
*   `POST /api/users/register` : 用户注册。
*   `GET /api/bookmarks/page` : 分页查询当前用户的书签（需在 Header 携带 Token）。

---
*本项目为个人独立开发，用于学习与求职展示。*
