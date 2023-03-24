# ShardingJDBC整合springboot

## 使用技术

1. Mybatis
2. 通用Mapper
3. Springboot
4. shardingJDBC-4.1.1

## 步骤
### 1.创建两个mysql数据库作为两个分片。

> 作为两个分片。分别在两个数据库中创建相同的表。
> 使用`jingnan_all.sql`

### 2.工程中添加ShardingJDBC起步依赖

```xml
	<dependency>
	    <groupId>org.apache.shardingsphere</groupId>
	    <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
	    <version>4.1.1</version>
	</dependency>
	<dependency>
	    <groupId>org.apache.shardingsphere</groupId>
	    <artifactId>sharding-jdbc-spring-namespace</artifactId>
	    <version>4.1.1</version>
	</dependency>
```

### 3.配置分片规则

具体分片规则参考`application-shard-xxx.yml`

## 测试

1. 分库分表
2. 读写分离
3. 表
4. 数据加密