package com.sfauto.base.global;

import com.google.gson.Gson;

import java.io.Serializable;

public class BsError implements Serializable {

    private int	code;

    private String	msg;

    private String data;

    private String	json;

    public static BsError fromJson(String json)
    {
        return new Gson().fromJson(json, BsError.class);
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }


    public String getJson()
    {
        return this.json;
    }

    public void setJson(String json)
    {
        this.json = json;
    }

    @Override
    public String toString()
    {
        if (this.json != null)
        {
            return this.json;
        }
        return "Error: Code=" + this.code + ", Msg=" + this.msg;
    }

}
