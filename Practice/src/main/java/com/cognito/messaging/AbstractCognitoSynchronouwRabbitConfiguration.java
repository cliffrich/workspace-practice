package com.cognito.messaging;

        import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:applicationMessagingContext.xml")
public abstract class AbstractCognitoSynchronouwRabbitConfiguration {
    @Value("${messaging.host}")
    String host;
    @Value("${messaging.port}")
    String port;
    @Value("${messaging.user}")
    String user;
    @Value("${messaging.password}")
    String password;
    @Value("${messaging.virtual.host}")
    String virtualHost;
    @Value("${messaging.exchange}")
    String cognitoExchange;
    @Value("${messaging.request.queue}")
    String cognitoRequestQueue;
    @Value("${messaging.response.queue}")
    String cognitoResponseQueue;
    @Value("${messaging.channel}")
    String cognitoChannel;
    @Value("${messaging.cacheSize}")
    int cacheSize;

    protected abstract void configureSynchronousRabbitTemplate(RabbitTemplate rabbitTemplate);
    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }
    @Bean
    public RabbitTemplate synchronousRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        //rabbitTemplate.setMessageConverter(jsonMessageConverter());
        configureSynchronousRabbitTemplate(rabbitTemplate);
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
	/*@Bean
    public MessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }*/
}
