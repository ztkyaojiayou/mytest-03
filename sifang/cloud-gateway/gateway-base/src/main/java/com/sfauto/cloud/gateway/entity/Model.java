package com.sfauto.cloud.gateway.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
public class Model {

    private String datatype;
    private String condition;
    private String result;

    private List<String> datas;

    public String getDatatype() {
        return datatype;
    }

    @Bean
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getCondition() {
        return condition;
    }

    @Bean
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getResult() {
        return result;
    }

    @Bean
    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getDatas() {
        return datas;
    }

    @Bean
    public void setDatas(List<String> datas) {
        this.datas = datas;
    }
}
