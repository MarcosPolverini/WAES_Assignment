package com.waes.diff.service.calculator;

import java.io.Serializable;

import com.waes.diff.model.DiffBucket;
import com.waes.diff.model.DiffResult;

import lombok.NonNull;

public interface DiffCalculator extends Serializable{

    DiffResult doCalculate(@NonNull final DiffBucket bucket);

}