package com.mimik.link.service.impl;

import com.mimik.link.domain.NumberGen;
import com.mimik.link.enums.NumberTypeEnum;
import com.mimik.link.repository.NumberGenRepository;
import com.mimik.link.service.INumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBBasedSingleIdNumberGenerator implements INumberGenerator {

    @Autowired
    private NumberGenRepository numberGenRepository;

    @Override
    public Long nextNumber(NumberTypeEnum type) {
        do {
            try {
                // 尝试获取nextNumber
                Long number = doNextNumber(type);
                // 保存成功，说明未发生冲突
                if (number != null){
                    return number;
                }
            }catch (ObjectOptimisticLockingFailureException e){
                // 更新失败，进行重试
//                LOGGER.error("opt lock failure to generate number, retry ...");
            }
        }while (true);
    }

    private Long doNextNumber(NumberTypeEnum type){
        Optional<NumberGen> numberGen = this.numberGenRepository.getByType(type);
        numberGen = numberGen.ofNullable(new NumberGen(type));
        Long id = numberGen.get().nextNumber();
        this.numberGenRepository.save(numberGen.get());
        return id;
    }
}