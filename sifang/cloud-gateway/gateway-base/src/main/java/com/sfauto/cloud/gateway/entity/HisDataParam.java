package com.sfauto.cloud.gateway.entity;

import lombok.Data;

import java.util.List;

@Data
public class HisDataParam {
    List<String> devList;
    long startTime;
    long endTime;
}
