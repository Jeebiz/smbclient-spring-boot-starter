package net.jeebiz.ftpclient.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jcifs.smb.SmbFile;
import net.jeebiz.smbclient.SMBClientBuilder;
import net.jeebiz.smbclient.client.ISMBClient;
import net.jeebiz.smbclient.client.SMBPooledResourceClient;
import net.jeebiz.smbclient.client.SMBResourceClient;
import net.jeebiz.smbclient.pool.SMBClientPool;
import net.jeebiz.smbclient.pool.SMBPooledClientFactory;

@Configuration
@ConditionalOnClass({ ISMBClient.class, SmbFile.class })
@EnableConfigurationProperties({SMBClientProperties.class })
public class SMBClientConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public SMBClientBuilder smbClientBuilder(SMBClientProperties properties) {
		return new SMBClientBuilder(properties);
	}
	
	@Bean
	public ISMBClient smbClient(SMBClientBuilder clientBuilder, SMBClientProperties properties) {
		
		if (properties.getPool().isEnabled()) {

			SMBPooledClientFactory factory = new SMBPooledClientFactory(clientBuilder);

			SMBClientPool clientPool = new SMBClientPool(factory, properties.getPool());

			return new SMBPooledResourceClient(clientPool, properties);
		}
		
		return new SMBResourceClient(clientBuilder);
	}

}
