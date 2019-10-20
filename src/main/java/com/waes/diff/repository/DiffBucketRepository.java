package com.waes.diff.repository;

import com.waes.diff.model.DiffBucket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiffBucketRepository extends CrudRepository<DiffBucket, Integer> {
}