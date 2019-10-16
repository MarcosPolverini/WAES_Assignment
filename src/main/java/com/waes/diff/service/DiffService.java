package com.waes.diff.service;

import java.io.Serializable;

import com.waes.diff.model.DiffResult;

import lombok.NonNull;

public interface DiffService extends Serializable {

    void saveLeft(@NonNull final Integer id, @NonNull final String base64Data);

    void saveRight(@NonNull final Integer id, @NonNull final String base64Data);

    DiffResult calculateDiff(@NonNull final Integer id);

}