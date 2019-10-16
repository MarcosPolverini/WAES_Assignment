package com.waes.diff.model;

import java.util.Set;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public final class DiffResult {

    private final Boolean equalData;

    private final Boolean differentSize;

    @Singular("addPosition")
    private final Set<Integer> differentPositions;

}