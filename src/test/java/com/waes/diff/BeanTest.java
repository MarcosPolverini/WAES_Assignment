package com.waes.diff;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BeanTest {

    @Autowired
    private JdbcTemplate template;

    @After
    public void clearDataBase() {
        template.execute("DELETE FROM diff_bucket");
    }
}
