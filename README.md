## BVK Backend

### Feature

- Member CRUD, search LIKE name
- Login dan Register. Note: tidak menggunakan oauth2 + google
- Image disimpan di database
- Unit test service layer

### Git Command

- git clone: mengunggah project backend ke local
- git pull: mengambil perubahan berdasarkan commmitan terbaru

### Maven Command

- mvn clean install: mengunggah dependency-dependency yang dibutuhkan pada project ini
- mvn spring-boot:run : menjalankan project backend springboot

### Note

- Project backend menggunakan database MS SQL Server dengan konfigurasi seperti :
  - databaseName = bvk
  - port: 1433
  - username: sa
  - password: admin
  - jika ingin menggunakan konfigurasi sendiri bisa diubah pada file (/bvk-test-be/src/main/resources/application.yaml)

### Swagger Path

Path: http://localhost:8080/swagger-ui/index.html
