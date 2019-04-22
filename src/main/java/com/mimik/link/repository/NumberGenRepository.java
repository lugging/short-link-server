package com.mimik.link.repository;

import com.mimik.link.domain.NumberGen;
import com.mimik.link.enums.NumberTypeEnum;
import com.mimik.link.repository.support.SupportRepository;

import java.util.Optional;

/**
 * @ClassName NumberGenRepository
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 14:03
 * @Version
 */
public interface NumberGenRepository extends SupportRepository<NumberGen>{

    Optional<NumberGen> getByType(NumberTypeEnum type);
}
