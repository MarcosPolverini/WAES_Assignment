package com.waes.diff.infra;

import com.waes.diff.repository.DiffRepository;
import lombok.extern.java.Log;
import lombok.val;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Log
public class ReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        val repository = event.getApplicationContext().getBean(DiffRepository.class);
        repository.createTable();
        log.info("diff_bucket table created");
    }
}
