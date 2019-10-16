package com.waes.diff.controller.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private final Boolean equal;

    private final Boolean differentSize;

    private final Set<Integer> differentPositions;
}
