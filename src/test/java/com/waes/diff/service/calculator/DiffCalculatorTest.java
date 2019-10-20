package com.waes.diff.service.calculator;

import com.waes.diff.MockedTests;
import com.waes.diff.infra.DiffException;
import com.waes.diff.model.DiffBucket;
import com.waes.diff.model.DiffResult;
import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiffCalculatorTest extends MockedTests {

    private final DiffCalculator calculator = new DiffCalculatorImp();

    @Test(expected = NullPointerException.class)
    public void it_should_throws_exception_null_bucket() {
        calculator.doCalculate(null);
    }

    @Test(expected = DiffException.class)
    public void it_should_thorws_exception_invalid_bucket() {
        calculator.doCalculate(new DiffBucket());
    }

    @Test
    public void it_should_return_equals() {
        execute("sample text", "sample text", DiffResult.builder().equalData(true).build());
    }

    @Test
    public void it_should_return_not_same_size() {
        execute("sample text ", "sample text", DiffResult.builder().differentSize(true).build());
    }

    @Test
    public void it_should_return_diff_positions() {
        execute("Sample te t", "samplE text", DiffResult.builder().addPosition(0).addPosition(5).addPosition(9).build());
    }

    private void execute(final String left, final String right, final DiffResult expected) {
        val bucket = new DiffBucket();
        bucket.setRight(right);
        bucket.setLeft(left);
        assertEquals(expected, calculator.doCalculate(bucket));
    }

}