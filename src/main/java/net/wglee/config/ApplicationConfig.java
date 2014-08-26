package net.wglee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author wangeun.lee@sk.com
 */
@Configuration
@EnableAsync
@Import(value = {PersistenceConfig.class})
public class ApplicationConfig {
}
