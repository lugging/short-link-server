package com.mimik.link.domain;

import com.mimik.link.enums.NumberTypeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sl_number_gen")
public class NumberGen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 生成器类型
     */
    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.PRIVATE)
    private NumberTypeEnum type;

    /**
     * version 字段，用于乐观锁控制
     */
    @Version
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private long version;

    /**
     * 当前 number
     */
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "current_number")
    private Long currentNumber;


    public NumberGen(){

    }

    public NumberGen(NumberTypeEnum type){
        this.setType(type);
        setCurrentNumber(0L);
    }

    public Long nextNumber(){
        return ++currentNumber;
    }

    public List<Long> nextNumber(int size){
        List<Long> numbers = new ArrayList<>(size);
        for (int i=0;i<size;i++){
            numbers.add(nextNumber());
        }
        return numbers;
    }

}