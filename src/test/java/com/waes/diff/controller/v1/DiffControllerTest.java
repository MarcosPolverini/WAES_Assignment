package com.waes.diff.controller.v1;

import com.waes.diff.WebTest;
import com.waes.diff.controller.v1.model.DiffRequest;
import com.waes.diff.controller.v1.model.Result;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiffControllerTest extends WebTest {

    @Test
    public void it_should_add_left() {
        sendAndAssertPost("left", "LEFT_VALUE");
    }

    @Test
    public void it_should_add_right() {
        sendAndAssertPost("right", "RIGHT_VALUE");
    }

    @Test
    @SneakyThrows
    public void it_should_get_differences() {
        sendAndAssertPost("left", "SOME TEXT ADD");
        sendAndAssertPost("right", "TEXT ADD SOME");
        getMvc().perform(get("/v1/diff/1").contentType(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(createCompareJson()));
    }

    @Test
    public void it_should_return_exception_invalid_base64() {
        sendAndValidateNonBase64("left");
        sendAndValidateNonBase64("right");
    }

    @Test
    @SneakyThrows
    public void it_should_fail_invalid_bucket() {
        getMvc().perform(get("/v1/diff/25225")
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andExpect(status().isNotFound());
    }

    @SneakyThrows
    private void sendAndValidateNonBase64(final String path) {
        val req = new DiffRequest();
        req.setData("NON BASE64");
        val rs = getMvc().perform(post(getUrl(path))
                .content(toJson(req))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(400));
    }

    private String createJson(final String value) {
        val req = new DiffRequest();
        req.setData(Base64.getEncoder().encodeToString(value.getBytes()));
        return toJson(req);
    }

    @SneakyThrows
    private void sendAndAssertPost(final String path, final String value) {
        val content = createJson(value);
        getMvc().perform(post(getUrl(path))
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isCreated());
    }

    private String createCompareJson() {
        val positions = IntStream.range(0, 13).filter(i -> i != 4).boxed().collect(Collectors.toSet());
        val res = Result.builder().differentPositions(positions).build();
        return toJson(res);
    }

    private String getUrl(final String path) {
        return String.format("/v1/diff/1/%s", path);
    }
}