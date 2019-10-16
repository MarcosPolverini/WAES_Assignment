package com.waes.diff.service;

import com.waes.diff.infra.BucketNotFoundException;
import com.waes.diff.infra.DiffException;
import com.waes.diff.model.DiffBucket;
import com.waes.diff.model.DiffResult;
import com.waes.diff.repository.DiffRepository;
import com.waes.diff.service.calculator.DiffCalculator;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
class DiffServiceImp implements DiffService {

    @Autowired
    private DiffRepository repository;

    @Autowired
    private DiffCalculator calulator;

    @Override
    public void saveLeft(@NonNull final Integer id, @NonNull final String base64Data) {
        val bucketBuilder = repository.get(id).map(DiffBucket::toBuilder).orElse(DiffBucket.builder().id(id));
        bucketBuilder.left(base64ToString(base64Data));
        repository.insertOrUpdate(bucketBuilder.build());
    }

    @Override
    public void saveRight(@NonNull final Integer id, @NonNull final String base64Data) {
        val bucketBuilder = repository.get(id).map(DiffBucket::toBuilder).orElse(DiffBucket.builder().id(id));
        bucketBuilder.right(base64ToString(base64Data));
        repository.insertOrUpdate(bucketBuilder.build());
    }

    @Override
    public DiffResult calculateDiff(@NonNull final Integer id) {
        return repository.get(id).map(calulator::doCalculate).orElseThrow(BucketNotFoundException::new);
    }

    private String base64ToString(final String base64) {
        try {
            val bytes = Base64.getDecoder().decode(base64);
            return new String(bytes);
        } catch (IllegalArgumentException e) {
            throw new DiffException("Invallid base64 data");
        }
    }

}