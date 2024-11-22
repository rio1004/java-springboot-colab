# java-springboot-colab


## How To Run dev Mode

### requirements
- maven:3.9.0
- openjdk:17-jdk-slim
- mysql (use in docker optional)
- docker optional


#### option 1 mvn with docker support
```
- run mysql
docker run --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=defaultpassword \
  -e MYSQL_DATABASE=sql12740074 \
  -p 3306:3306 \
  -d mysql:latest
  
- run mongoDb
docker run --name spring-mongodb -d -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=root \
  -e MONGO_INITDB_ROOT_PASSWORD=defaultpassword \
  mongo

- run springboot
mvn clean install
mvn spring-boot:run
```

# REPO TECH STACK
### Data Base
- type: mysql
- user: root
- passwword: defaultpassword
- dbname: sql12740074
- host: https://www.freesqldatabase.com/
- [Database Migration Documentation](src/main/resources/db/README.md)
- migration_tool: https://docs.liquibase.com/home.html 


### Server
- location: Render 
- location_url: https://dashboard.render.com/web/srv-csctgrdds78s73be8pg0
- url_docs: https://java-springboot-colab-lgwc.onrender.com/v3/api-docs
- Swagger: https://java-springboot-colab-lgwc.onrender.com/swagger-ui.html

## Contributors

Thanks to these amazing contributors:

| ![davebarasona](https://avatars.githubusercontent.com/u/78859307?v=4&s=80) | ![rio1004](https://avatars.githubusercontent.com/u/79251162?v=4&s=80) | ![tavs](https://avatars.githubusercontent.com/u/82376053?v=4&s=80) | ![JianGdt](https://avatars.githubusercontent.com/u/93214298?v=4&s=80) | ![ellej16](https://avatars.githubusercontent.com/u/6106749?v=4&s=80) | ![jhonpabz](https://avatars.githubusercontent.com/u/44497942?v=4&s=80) |
|:---:|:---:|:---:|:---:|:---:|:---:|
| [davebarasona](https://github.com/davebarasona) | [rio1004](https://github.com/rio1004) | [tavs](https://github.com/7iquid) | [JianGdt](https://github.com/JianGdt) | [ellej16](https://github.com/ellej16) | [jhonpabz](https://github.com/jhonpabz) |
