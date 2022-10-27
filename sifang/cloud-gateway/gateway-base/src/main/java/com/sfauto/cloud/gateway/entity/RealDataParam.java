package com.sfauto.cloud.gateway.entity;

import lombok.Data;

import java.util.List;

@Data
public class RealDataParam {
    String opType;
    List<String> devList;
    String period;
}
