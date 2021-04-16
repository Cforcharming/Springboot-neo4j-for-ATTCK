package uestc.zhanghanwen.ATTCK;

import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.SpringApplication;
import lombok.NoArgsConstructor;

/**
 * The main entrance for the Spring Applications. <br>
 * This Application uses Springboot framework, for more information about the frameworks,<br>
 * please refer to pom.xml
 *
 * @see uestc.zhanghanwen.ATTCK.RestWebControllers controllers
 * @author zhanghanwen
 * @version 1.0
 */
@Configuration
@NoArgsConstructor
@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement(proxyTargetClass = true)
public class ATTCKApplication {
    
    /**
     * All magics start from here...
     *
     * @param args {@code null}
     */
    public static void main(String[] args) {
        SpringApplication.run(ATTCKApplication.class, args);
    }
}
