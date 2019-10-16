package com.waes.diff.repository;

import java.io.Serializable;
import java.util.Optional;

import com.waes.diff.model.DiffBucket;

import lombok.NonNull;

public interface DiffRepository extends Serializable{

    void insertOrUpdate(@NonNull final DiffBucket bucket);

    Optional<DiffBucket> get(@NonNull final Integer id);

    void createTable();

}