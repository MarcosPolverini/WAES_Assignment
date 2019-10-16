package com.waes.diff.model;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiffBucketTest {

    @Test
    public void it_should_return_valid() {
        val test = DiffBucket.builder().left("sample").right("xxxx").build();
        assertTrue(test.isValid());
    }

    @Test
    public void it_should_return_invalid_all_blank() {
        assertFalse(DiffBucket.builder().build().isValid());
    }

    @Test
    public void it_should_return_invalid_right_blank() {
        assertFalse(DiffBucket.builder().left("sample").build().isValid());
    }

    @Test
    public void it_should_return_invalid_left_blank() {
        assertFalse(DiffBucket.builder().right("xxxx").build().isValid());
    }

}