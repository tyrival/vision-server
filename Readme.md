# Vision 可视化

### 启动顺序
Config-server -> Eureka -> 其他模块

### 端口
##### 核心框架
config: 9000

eureka: 9001

feign: 9002

zuul: 9003

##### 业务模块
system: 9101

datasource: 9102

vision: 9111

elastic: 9201

### Coder代码生成
通过声明实体类属性，生成所有相关的代码文件。
生成的文件无法自动加入版本管理工具，需要手工添加。

### Common基础模块
管理所有其他模块的公用类、接口等，包括实体、枚举、工具、异常，以及通用Base接口

### Config配置中心
统一管理各模块的配置文件，从Git、Svn等远端服务器获取配置，并提供给各模块使用

### Datasource数据源管理
数据源的增删改查和动态查询

### Elastic搜索服务
基于Elasticsearch搭建的搜索服务，通过API提供数据索引和检索的功能

### Eureka服务治理
提供服务治理功能，提供服务的模块在此注册，从而可被消费者发现

### Feign服务消费者
接收Zuul转发的请求，并将请求转发到提供服务的模块，在此过程中进行负载均衡、容错保护和消费降级等

### Redis高速缓存模块
作为依赖模块，可以被引入各工程

### System系统管理服务模块
包括用户、角色、菜单、权限等

### Vision管理模块
可视化信息管理

### Zuul路由模块
对外统一发布所有服务，转发接收到的请求进行，并通过拦截器对请求进行验证和过滤
