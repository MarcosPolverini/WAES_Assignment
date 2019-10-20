package com.waes.diff;

import com.waes.diff.model.DiffBucket;
import com.waes.diff.repository.DiffBucketRepository;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public abstract class MockedTests {

    protected final DiffBucketRepository mockedRepository = Mockito.mock(DiffBucketRepository.class);

    private static final Map<Integer, DiffBucket> DIFF_BUCKET_MAP = new HashMap<>();

    @Before
    public void setUp() {
        when(mockedRepository.findById(anyInt())).thenAnswer(inv -> Optional.ofNullable(DIFF_BUCKET_MAP.get(inv.getArguments()[0])));
        when(mockedRepository.save(any(DiffBucket.class))).then(inv -> {
            DiffBucket bucket = inv.getArgument(0);
            DIFF_BUCKET_MAP.put(bucket.getId(), bucket);
            return null;
        });
    }
}
