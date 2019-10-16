package com.waes.diff.service;

import com.waes.diff.BeanTest;
import com.waes.diff.infra.BucketNotFoundException;
import com.waes.diff.infra.DiffException;
import com.waes.diff.model.DiffResult;
import com.waes.diff.repository.DiffRepository;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiffServiceTest extends BeanTest {

    @Autowired
    private DiffService service;

    @Autowired
    private DiffRepository repository;

    @Test
    public void it_should_save_right_data() {
        val msg = "right value";
        service.saveRight(1, toBase64(msg));
        val res = repository.get(1);
        assertTrue(res.isPresent());
        assertEquals(msg, res.get().getRight());
    }

    @Test
    public void it_should_save_left_data() {
        val msg = "left value";
        service.saveLeft(1, toBase64(msg));
        val res = repository.get(1);
        assertTrue(res.isPresent());
        assertEquals(msg, res.get().getLeft());
    }

    @Test
    public void it_should_calculate_diff() {
        val id = 1;
        val expected = DiffResult.builder().addPosition(0).addPosition(5).addPosition(9).build();
        service.saveLeft(id, toBase64("Sample te t"));
        service.saveRight(id, toBase64("samplE text"));
        assertEquals(expected, service.calculateDiff(1));
    }

    @Test(expected = BucketNotFoundException.class)
    public void it_should_throws_exception_bucket_not_found() {
        service.calculateDiff(1);
    }

    @Test(expected = DiffException.class)
    public void it_should_throws_exception_not_all_filled() {
        service.saveLeft(1, toBase64("Sample te t"));
        service.calculateDiff(1);
    }

    private String toBase64(final String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

}