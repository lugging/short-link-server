package com.mimik.link.service.impl;

import com.google.common.collect.Lists;
import com.mimik.link.domain.NumberGen;
import com.mimik.link.enums.NumberTypeEnum;
import com.mimik.link.repository.NumberGenRepository;
import com.mimik.link.service.INumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DBBasedBatchNumberGenerator implements INumberGenerator {

    private static final int BATCH_SIZE = 500;

    @Autowired
    private NumberGenRepository numberGenRepository;

    private List<Long> tmp = Lists.newArrayList();

    @Override
    public Long nextNumber(NumberTypeEnum type) {
        synchronized (tmp){
            if (CollectionUtils.isEmpty(tmp)){
                do {
                    try {
                        List<Long> numbers = nextNumber(type, BATCH_SIZE);
                        tmp.addAll(numbers);
                        break;
                    }catch (ObjectOptimisticLockingFailureException e){
                    }
                }while (true);
            }
            return tmp.remove(0);
        }
    }

    private List<Long> nextNumber(NumberTypeEnum type, int size){
        Optional<NumberGen> numberGen = this.numberGenRepository.getByType(type);
        numberGen = numberGen.ofNullable(new NumberGen(type));
        List<Long> ids = numberGen.get().nextNumber(size);
        this.numberGenRepository.save(numberGen.get());
        return ids;
    }
}