#### 相关介绍

这是一个实现了通过聊天自动操作挂号的微服务架构demo

其中ai聊天功能和后台挂号功能分为了两个子项目

通过naocs注册、openFeign远程调用、loadblancer负载均衡

其中后台挂号并没有去深入的写crud，crud在另一个项目中有所体现，这里就重心放在了ai方面

为了能运行这个项目，你必须要准备：

- jdk17+
- mysql
- mongoDB
- 符合openai标准的api-key（如chatgpt、deepseek）
- nacos
- pom.xml中的所有依赖
