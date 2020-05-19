package com.hatiko.bot.telegram.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties("telegram.bot.command")
@Component
public class CommandProperties {

    private AutoReply autoReply;

    private Start start;
    private Help help;

    private TranslateText translateText;
    private TranslateWords translateWords;
    private SynthesizeText synthesizeText;
    private SynthesizeWords synthesizeWords;
    private RecognizeLanguage recognizeLanguage;
    private TranslateAndSynthesizeText translateAndSynthesizeText;

    private List<String> requestList;

    @PostConstruct
    private void prepareRequestList() {

        requestList = new ArrayList<>();
        requestList.add(start.getRequest());
        requestList.add(help.getRequest());
        requestList.add(translateText.getRequest());
        requestList.add(translateWords.getRequest());
        requestList.add(synthesizeText.getRequest());
        requestList.add(synthesizeWords.getRequest());
        requestList.add(recognizeLanguage.getRequest());
        requestList.add(translateAndSynthesizeText.getRequest());
    }

    @Data
    public static class AutoReply {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class Start {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class Help {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class TranslateText {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class TranslateWords {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class SynthesizeText {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class SynthesizeWords {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class RecognizeLanguage {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }

    @Data
    public static class TranslateAndSynthesizeText {
        private String request;
        private String response;
        private String method;
        private Integer argc;
    }
}
