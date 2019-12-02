# smbclient-spring-boot-starter
Spring Boot Starter For jeebiz-smbclient

### 说明

 > 基于 [jeebiz-smbclient](https://github.com/Jeebiz/jeebiz-smbclient) 的 Spring Boot Starter 实现

1. 整合 smbclient

### Maven

``` xml
<dependency>
	<groupId>net.jeebiz</groupId>
	<artifactId>smbclient-spring-boot-starter</artifactId>
	<version>${project.version}</version>
</dependency>
```

### Sample

```java


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.jeebiz.smbclient.client.ISMBClient;

@EnableSMBClient
@SpringBootApplication
public class Application {
	
	@Autowired
	public ISMBClient smbClient;
	
	@PostConstruct
	private void init() {
		
		//smbClient.upload(localFile, ftpFileName)
		
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

```
