package com.waes.diff.repository;

import com.waes.diff.BeanTest;
import com.waes.diff.infra.DiffException;
import com.waes.diff.model.DiffBucket;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DiffRepositoryTest extends BeanTest {

    @Autowired
    private DiffRepository repository;

    @Test
    public void it_should_insert_and_return_data() {
        val diff = DiffBucket.builder().id(1).left("left").right("right").build();
        repository.insertOrUpdate(diff);
        Assert.assertEquals(diff, repository.get(1).orElse(null));
    }

    @Test
    public void it_should_insert_and_update_data() {
        val id = 2;
        val diff = DiffBucket.builder().id(id).left("left").build();
        repository.insertOrUpdate(diff);
        Assert.assertEquals(diff, repository.get(id).orElse(null));
        val updatedDiff = diff.toBuilder().right("right").build();
        repository.insertOrUpdate(updatedDiff);
        Assert.assertEquals(updatedDiff, repository.get(id).orElse(null));
    }

    @Test(expected = NullPointerException.class)
    public void it_should_validate_null_param_insert() {
        repository.insertOrUpdate(null);
    }

    @Test(expected = DiffException.class)
    public void it_should_throw_exception_insert_with_invalid_id() {
        repository.insertOrUpdate(DiffBucket.builder().build());
    }

    @Test(expected = NullPointerException.class)
    public void it_should_validate_null_param_get() {
        repository.get(null);
    }

    @Test
    public void it_should_return_empty() {
        Assert.assertFalse(repository.get(9999).isPresent());
    }
}