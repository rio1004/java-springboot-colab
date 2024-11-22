package com.example.demo.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.beans.factory.annotation.Qualifier;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class StartupLogger implements ApplicationListener<ApplicationStartedEvent> {

    private final Environment environment;
    private final RequestMappingHandlerMapping handlerMapping;

    public StartupLogger(
            Environment environment,
            @Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping handlerMapping) {
        this.environment = environment;
        this.handlerMapping = handlerMapping;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            String port = environment.getProperty("server.port", "8080");
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String localUrl = String.format("http://localhost:%s", port);
            String networkUrl = String.format("http://%s:%s", hostAddress, port);

            String banner = """
                    
                    \u001B[32m--------------------------------------------------------
                    🚀 Application is running! Access URLs:
                    
                    🏠 Local:    \u001B[36m%s\u001B[0m
                    🌍 Network:  \u001B[36m%s\u001B[0m
                    
                    📚 API Documentation:
                    ⚡️ Swagger UI:  \u001B[36m%s/swagger-ui.html\u001B[0m
                    📘 API Docs:    \u001B[36m%s/v3/api-docs\u001B[0m
                    📗 YAML Docs:   \u001B[36m%s/v3/api-docs.yaml\u001B[0m
                    \u001B[32m--------------------------------------------------------\u001B[0m
                    """;

            System.out.println(String.format(banner,
                    localUrl,
                    networkUrl,
                    localUrl,
                    localUrl,
                    localUrl));

            // Optionally print endpoints
            // System.out.println("\n📍 Available endpoints:");
            // handlerMapping.getHandlerMethods().forEach((key, value) -> {
            //     System.out.println(String.format("\t%s %s",
            //             key.getMethodsCondition().toString(),
            //             key.getPatternValues().iterator().next()));
            // });

        } catch (UnknownHostException e) {
            // Handle exception
        }
    }
} 