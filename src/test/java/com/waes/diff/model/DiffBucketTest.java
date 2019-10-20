package com.waes.diff.model;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DiffBucketTest {

    @Test
    public void it_should_return_valid() {
        val test = new DiffBucket();
        test.setLeft("sample");
        test.setRight("xxxx");
        assertTrue(test.isValid());
    }

    @Test
    public void it_should_return_invalid_all_blank() {
        assertFalse(new DiffBucket().isValid());
    }

    @Test
    public void it_should_return_invalid_right_blank() {
        val test = new DiffBucket();
        test.setLeft("sample");
        assertFalse(test.isValid());
    }

    @Test
    public void it_should_return_invalid_left_blank() {
        val test = new DiffBucket();
        test.setRight("xxxx");
        assertFalse(test.isValid());
    }

}