package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.AcMapper;
import com.zenq.cloud507b.po.Ac;
import com.zenq.cloud507b.po.AcTemExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcService {
    @Autowired
    AcMapper acMapper;

    public boolean updateAcExpTem(int intvalue, String date){
        return acMapper.updateAcExpTem(intvalue,date);
    }

    public List<Integer> listAcTemper(){
        return acMapper.selectListAcTemper();
    }

    public List<Integer> listPopulation(){
        return acMapper.selectListPopulation();
    }
    public Integer listAcPopulation(Integer temper){
        return acMapper.selectPopulationList(temper);
    }

    public boolean updateAcPopulation(Integer population, Integer temper) {
        return acMapper.updatePopulationList(population, temper) > 0;
    }

    public boolean addAcPopulation(Integer temper,Integer population) {
        return acMapper.insertAcList(temper,population) > 0;
    }

    public String getDate(String date) {
        return acMapper.selectDatebyAcTemExp(date);
    }

    public boolean setTodayAcTem(String today,int temper){
        return acMapper.setTodayAcTem(today,temper);
    }
    public Boolean addTodayAcTem(String today, Integer temper) {
        return acMapper.addTodayAcTem(today, temper);
    }

    public List<AcTemExp> selectAcExpTemper() {
        return acMapper.selectAcTemExp();
    }
}
