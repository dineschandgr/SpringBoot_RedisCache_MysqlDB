# SpringBoot_RedisCache_MysqlDB
Spring Boot Application connecting to mysql DB for CRUD operations with Redis cache in between them to store transient data

Redis is used as a cache while mysql is used a database for CRUD operations in this application. Spring Data JPA is used for Mysql DB.

#Preqequisites

1. Download and install Redis in local machine
2. After unzipping the redis jar, open redis-server.exe file to start the redis server. Redis runs on port 6379 on localhost
3. Then start the redis-cli.exe to open the client
4. Run MySQL RDS DB in AWS and provide configuration in application.properties file

There are 2 types of Java Redis clients namely

1. Jedis (Synchronous and single threaded. Easy to use
2. Lettuce ( uses asynchronous and reactive API. Multithreaded. difficult to use)

#Replication

- Redis Sentinel provides high availability for Redis
- Uses Master Slave concept.
- Only master can take writes

#Cache Annotations

- Spring boot will automatically connect to any cache using @EnableCaching based on the configuration in the property file 

1. @EnableCaching (to enable caching in Spring Boot)
1. @Cacheable (used in retrieve method. This annotation will enable Spring Boot to look for the value in Redis cache before retrieving from the MySQL DB)
2. @CachePut (used in update method when a value is updated in DB)
3. @CacheEvict (Used to remove value from cache when the value is deleted in DB)

Duration can be set for the cache entry with a Bean called CacheManager

#Steps

1. Create a Jedis Connection Fcatory and set it to the RedisTemplate
2. Provide redis configuration in application.properties file
3. Use hashOperations provided by Spring Boot in the Repository
4. Use HashOperations to perform the CRUD operation
5. Go to postman and enter the url http://localhost:8099/redis/addUser and enter the payload for the post method
6. For update, use http://localhost:8099/redis/update/1 with the payload
7. FindAll users, http://localhost:8099/redis/findAll
8. Get a user, http://localhost:8099/redis/getUser/1
9. Delete a user, http://localhost:8099/redis/delete/1

#Verify

1. Check the redis-cli with the command keys *
2. Open another cli with the command monitor
3. This cli will display all the operations done in the redis server
4. First get operation will retrieve from DB
5. Subsequent get operations will not hit the DB and get from cache
