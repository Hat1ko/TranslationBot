package com.greenboy.bot.telegram.properties;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ConfigurationProperties("telegram.bot.command")
@Component
public class CommandProperties {

    private Start start;
    private Help help;

    private TranslateText translateText;
    private TranslateWords translateWords;
    private SynthesizeText synthesizeText;
    private SynthesizeWords synthesizeWords;
    private TranslateAndSynthesizeText translateAndSynthesizeText;

    private List<String> requestList;

    @PostConstruct
    private void prepareRequestList() {

        requestList = new ArrayList<>();
        requestList.add(start.getRequest());
        requestList.add(help.getRequest());
    }

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

    @Data
    @Builder
    public static class TranslateText {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    @Builder
    public static class TranslateWords {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    @Builder
    public static class SynthesizeText {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    @Builder
    public static class SynthesizeWords {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    @Builder
    public static class RecognizeLanguage {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    @Builder
    public static class TranslateAndSynthesizeText {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }
}
