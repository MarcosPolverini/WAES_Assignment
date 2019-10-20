package com.waes.diff.controller.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private final Boolean equalData;

    private final Boolean differentSize;

    private final Set<Integer> differentPositions;
}
