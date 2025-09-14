package com.springboot.kafka_trainning.execptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidRequestBody extends RuntimeException {
    private final String title;
}
