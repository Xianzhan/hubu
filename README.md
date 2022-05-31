# hubu

Spring Cloud 开发模板，主要模块有：

1. hubu-base
2. hubu-component-gateway
3. hubu-component-register
4. hubu-service-base

其余模块则为集成测试使用。

# 技术栈

Spring Cloud Eureka + Spring Cloud Gateway + Spring Cloud OpenFeign + Mybatis-plus + Druid + ShardingSphere-JDBC

# 启动

1. 先启动本地的 MySQL 和 Redis 服务，当前配置为单机版
2. 启动 hubu-component-register 的 Eureka 注册服务
3. 启动 hubu-service-corp 或 hubu-service-bill 服务
4. 启动 hubu-component-gateway 开启对外 80 端口
5. 浏览器访问 http://127.0.0.1/bill/echo/xianzhan，将返回以下数据

```json
{
    "code": 0,
    "message": null,
    "data": "xianzhan"
}
```