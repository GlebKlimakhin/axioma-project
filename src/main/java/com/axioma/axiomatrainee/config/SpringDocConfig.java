package com.axioma.axiomatrainee.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi usersOpenApi() {
        String[] paths = {"/users/**"};

        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi groupsOpenApi() {
        String[] paths = {"/groups/**"};

        return GroupedOpenApi.builder()
                .group("groups")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi textWithQuestionsOpenApi() {
        String[] paths = {"/texts/**"};

        return GroupedOpenApi.builder()
                .group("texts-questions")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi readingSpeedOpenApi() {
        String[] paths = {"/reading/**"};

        return GroupedOpenApi.builder()
                .group("reading-speed")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi homeworkOpenApi() {
        String[] paths = {"/homeworks/**"};

        return GroupedOpenApi.builder()
                .group("homeworks")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi doneExerciseOpenApi() {
        String[] paths = {"/done/**"};

        return GroupedOpenApi.builder()
                .group("done-exercises")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi authOpenApi() {
        String[] paths = {"/auth/**"};

        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch(paths)
                .build();
    }

    public GroupedOpenApi afterburnerOpenApi() {
        String[] paths = {"/afterburner/**"};

        return GroupedOpenApi.builder()
                .group("afterburner")
                .pathsToMatch(paths)
                .build();
    }

}
