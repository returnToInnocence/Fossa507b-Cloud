package com.zenq.cloud507b.service;

import com.zenq.cloud507b.mapper.SeatMapper;
import com.zenq.cloud507b.po.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatMapper seatMapper;

    public List<Seat> seatList(){
        return seatMapper.selectListSeat();
    }

    public int updateSeat(String seatstatus, String seatuserguest, String seatid){
        return seatMapper.updateSeat(seatstatus, seatuserguest, seatid);
    }

    public String selectStatusbyid(String seatid){
        return seatMapper.selectStatusbyID(seatid);
    }

    public String selectguest(String seatid) {
        return seatMapper.selectGuestbyID(seatid);
    }

    public List<Integer> selectStatus() {
        return seatMapper.selectListStatus();
    }


    public Object setSeatUsedValue(int sum, String date) {
        return seatMapper.setSeatUsedValue(sum, date);
    }

    public static <T> List<List<T>> convertToRowsColumns(List<T> originalList, int rows, int columns) {
        if (originalList.size() != rows * columns) {
            throw new IllegalArgumentException("原始List的大小必须等于指定的行数乘以列数");
        }

        List<List<T>> rowColumnList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<T> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                int index = i * columns + j;
                row.add(originalList.get(index));
            }
            rowColumnList.add(row);
        }

        return rowColumnList;
    }
}
