# maven路径

mysql



```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.15</version>
</dependency>
<!--  单元测试包  -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>test</scope>
</dependency>
<!--  mybatis包  -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.5</version>
</dependency>
<!--oracle驱动包
    mvn install:install-file -Dfile=F:\app\Administrator\product\11\dbhome\jdbc\lib\ojdbc6.jar -DgroupId=oracle -DartifactId=oracle-jdbc -Dversion=12.1.0.2  -Dpackaging=jar
-->
<dependency>
    <groupId>oracle</groupId>
    <artifactId>oracle-jdbc</artifactId>
    <version>12.1.0.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>

```