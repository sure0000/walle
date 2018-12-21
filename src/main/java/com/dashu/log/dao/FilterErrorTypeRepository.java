package com.dashu.log.dao;

import com.dashu.log.entity.FilterErrorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/9/6 上午10:22
 **/
public interface FilterErrorTypeRepository extends JpaRepository<FilterErrorType,Long> {

    @Query(value = "select f.errorTypeId from FilterErrorType f")
    List<Integer> findAllErrorTypeId();
}
