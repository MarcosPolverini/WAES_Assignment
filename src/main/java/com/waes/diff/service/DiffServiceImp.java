package com.waes.diff.service;

import com.waes.diff.infra.BucketNotFoundException;
import com.waes.diff.infra.DiffException;
import com.waes.diff.model.DiffBucket;
import com.waes.diff.model.DiffResult;
import com.waes.diff.repository.DiffBucketRepository;
import com.waes.diff.service.calculator.DiffCalculator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
class DiffServiceImp implements DiffService {

    private final DiffBucketRepository repository;

    private final DiffCalculator calculator;

    @Override
    public void saveLeft(@NonNull final Integer id, @NonNull final String base64Data) {
        val bucket = getOrCreate(id);
        bucket.setLeft(base64ToString(base64Data));
        repository.save(bucket);
    }

    @Override
    public void saveRight(@NonNull final Integer id, @NonNull final String base64Data) {
        val bucket = getOrCreate(id);
        bucket.setRight(base64ToString(base64Data));
        repository.save(bucket);
    }

    @Override
    public DiffResult calculateDiff(@NonNull final Integer id) {
        return repository.findById(id).map(calculator::doCalculate).orElseThrow(BucketNotFoundException::new);
    }

    private DiffBucket getOrCreate(final Integer id) {
        val bucket = repository.findById(id);
        if (bucket.isEmpty()) {
            val newBucket = new DiffBucket();
            newBucket.setId(id);
            return newBucket;
        }
        return bucket.get();
    }

    private String base64ToString(final String base64) {
        try {
            val bytes = Base64.getDecoder().decode(base64);
            return new String(bytes);
        } catch (IllegalArgumentException e) {
            throw new DiffException("Invalid base64 data");
        }
    }

}