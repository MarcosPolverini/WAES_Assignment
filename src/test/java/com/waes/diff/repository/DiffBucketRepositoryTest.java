package com.waes.diff.repository;

import com.waes.diff.model.DiffBucket;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DiffBucketRepositoryTest {

    @Autowired
    private DiffBucketRepository repository;

    @Test
    public void it_should_insert_and_return_data() {
        val diff = new DiffBucket();
        diff.setId(1);
        diff.setLeft("left");
        diff.setRight("right");
        repository.save(diff);
        Assert.assertEquals(diff, repository.findById(1).orElse(null));
    }

    @Test
    public void it_should_insert_and_update_data() {
        val id = 2;
        val diff = new DiffBucket();
        diff.setId(id);
        diff.setLeft("left");
        repository.save(diff);
        Assert.assertEquals(diff, repository.findById(id).orElse(null));
        diff.setRight("right");
        repository.save(diff);
        Assert.assertEquals(diff, repository.findById(id).orElse(null));
    }

    @Test(expected = DataAccessException.class)
    public void it_should_validate_null_param_insert() {
        repository.save(null);
    }

    @Test(expected = DataAccessException.class)
    public void it_should_throw_exception_insert_with_invalid_id() {
        repository.save(new DiffBucket());
    }

    @Test(expected = DataAccessException.class)
    public void it_should_validate_null_param_get() {
        repository.findById(null);
    }

    @Test
    public void it_should_return_empty() {
        Assert.assertFalse(repository.findById(9999).isPresent());
    }
}