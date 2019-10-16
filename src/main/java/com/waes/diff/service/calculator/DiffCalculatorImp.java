package com.waes.diff.service.calculator;

import com.waes.diff.infra.DiffException;
import com.waes.diff.model.DiffBucket;
import com.waes.diff.model.DiffResult;
import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
class DiffCalculatorImp implements DiffCalculator {

    @Override
    public DiffResult doCalculate(@NonNull final DiffBucket bucket) {
        if (!bucket.isValid()) {
            throw new DiffException(String.format("Right and Left need to be filled for ID %d", bucket.getId()));
        }
        if (!isEqualSize(bucket)) {
            return DiffResult.builder().differentSize(true).build();
        }
        if (isEqual(bucket)) {
            return DiffResult.builder().equal(true).build();
        }
        return getDiffs(bucket);
    }

    private DiffResult getDiffs(final DiffBucket bucket) {
        val builder = DiffResult.builder();
        val left = bucket.getLeft().toCharArray();
        val right = bucket.getRight().toCharArray();
        for (int i = 0; i < left.length; i++) {
            if (left[i] != right[i]) {
                builder.addPosition(i);
            }
        }
        return builder.build();
    }

    private boolean isEqual(final DiffBucket bucket) {
        return bucket.getRight().equals(bucket.getLeft());
    }

    private boolean isEqualSize(final DiffBucket bucket) {
        return bucket.getRight().length() == bucket.getLeft().length();
    }
}