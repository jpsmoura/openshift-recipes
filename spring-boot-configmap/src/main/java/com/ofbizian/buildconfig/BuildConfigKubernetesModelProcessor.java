package com.ofbizian.buildconfig;

import io.fabric8.openshift.api.model.BuildTriggerPolicy;
import io.fabric8.openshift.api.model.TemplateBuilder;

import java.util.HashMap;
import java.util.Map;

public class BuildConfigKubernetesModelProcessor {

    public void on(TemplateBuilder builder) {
        builder.addNewBuildConfigObject()
                .withNewMetadata()
                    .withName("spring-boot-configmap")
                    .withLabels(getLabels())
                .endMetadata()
                .withNewSpec()
                    .withTriggers(getTriggers())
                    .withNewSource()
                        .withNewGit()
                            .withUri("https://anonymous:anonymous@github.com/bibryam/fis-example.git")
                        .endGit()
                        .withType("Git")
                    .endSource()
                    .withNewStrategy()
                        .withNewSourceStrategy()
                            .withNewFrom()
                                .withKind("ImageStreamTag")
                                .withName("fis-java-openshift:1.0")
                                .withNamespace("openshift")
                            .endFrom()
                        .endSourceStrategy()
                        .withType("Source")
                    .endStrategy()
                    .withNewOutput()
                        .withNewTo()
                            .withKind("ImageStreamTag")
                            .withName("spring-boot-configmap:${IS_TAG}")
                        .endTo()
                    .endOutput()
                .endSpec()
            .endBuildConfigObject()
            .build();
    }

    private BuildTriggerPolicy getTriggers() {
        BuildTriggerPolicy policy = new BuildTriggerPolicy();
        policy.setType("ImageChange");

        return policy;
    }

    private Map<String, String> getLabels() {
        Map<String, String> labels = new HashMap<>();
        labels.put("app", "spring-boot-configmap");
        labels.put("project", "spring-boot-configmap");
        labels.put("version", "1.0.0-SNAPSHOT");
        labels.put("group", "ofbizian");

        return labels;
    }
}
