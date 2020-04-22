package com.greenboy.bot.telegram.properties;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Builder
@ConfigurationProperties("telegram.bot.command")
@Component
public class CommandProperties {


    private Start start;
    private Help help;

    @Data
    @Builder
    public static class Start {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    @Builder
    public static class Help {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }
}
