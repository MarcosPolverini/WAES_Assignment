package com.waes.diff.model;

import org.springframework.util.StringUtils;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public final class DiffBucket {
    
    private final Integer id;

    private final String right;

    private final String left;

    public boolean isValid(){
        return !StringUtils.isEmpty(right) && !StringUtils.isEmpty(left);
    }

}