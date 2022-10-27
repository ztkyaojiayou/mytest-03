package com.sfauto.base.global;

public enum YesNoEnum {
    Y("Y", "Yes"), N("N", "No");

    private final String key;
    private final String value;

    private YesNoEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public static YesNoEnum getEnumByKey(String key){

        for(YesNoEnum yn : YesNoEnum.values()){
            if(yn.key.equalsIgnoreCase(key)){
                return yn;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
