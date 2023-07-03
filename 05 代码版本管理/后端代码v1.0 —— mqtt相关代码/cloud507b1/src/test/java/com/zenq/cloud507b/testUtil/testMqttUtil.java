package com.zenq.cloud507b.testUtil;

import com.zenq.cloud507b.util.MqttUtil;

import java.util.Arrays;

public class testMqttUtil {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(MqttUtil.hexTobytes("11ff1a")));

    }
}

/*       8      4     2    0
        128    64    32    16    8    4    2    0
原码     1      0     1     0     0    0    0    1
 */