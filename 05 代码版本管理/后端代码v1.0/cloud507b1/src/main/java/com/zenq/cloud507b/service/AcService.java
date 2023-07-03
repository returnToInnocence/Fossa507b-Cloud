package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.AcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcService {
    @Autowired
    AcMapper acMapper;

    public Integer listAcPopulation(Integer temper){
        return acMapper.selectPopulationList(temper);
    }

    public boolean updateAcPopulation(Integer population, Integer temper) {
        return acMapper.updatePopulationList(population, temper) > 0;
    }

    public boolean addAcPopulation(Integer temper,Integer population) {
        return acMapper.insertAcList(temper,population) > 0;
    }

}
