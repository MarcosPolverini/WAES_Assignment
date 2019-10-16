package com.waes.diff.controller.v1;

import com.waes.diff.controller.v1.model.DiffRequest;
import com.waes.diff.controller.v1.model.Result;
import com.waes.diff.model.DiffResult;
import com.waes.diff.service.DiffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(path = "/v1/diff/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Diff Calculator System")
public final class DiffController {

    @Autowired
    private DiffService service;

    @PostMapping("/left")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Process and save data in left side")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully saved left data"),
            @ApiResponse(code = 400, message = "Error processing data")
    })
    public void sendLeft(@PathVariable("id") Integer id, @RequestBody DiffRequest request) {
        service.saveLeft(id, request.getData());
    }

    @PostMapping("/right")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Process and save data in right side")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully saved right data"),
            @ApiResponse(code = 400, message = "Error processing data")
    })
    public void sendRight(@PathVariable("id") Integer id, @RequestBody DiffRequest request) {
        service.saveRight(id, request.getData());
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Process and return the differences in left and right", response = Result.class, produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully differences from left and right"),
            @ApiResponse(code = 404, message = "Error no such data"),
            @ApiResponse(code = 400, message = "Error processing data")
    })
    public Result getDiff(@PathVariable("id") Integer id) {
        return toResult(service.calculateDiff(id));
    }

    private Result toResult(final DiffResult diffResult) {
        val positions = diffResult.getDifferentPositions().isEmpty() ? null : diffResult.getDifferentPositions();
        return Result.builder()
                .differentPositions(positions)
                .differentSize(diffResult.getDifferentSize())
                .equalData(diffResult.getEqualData())
                .build();
    }
}