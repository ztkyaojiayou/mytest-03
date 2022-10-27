/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.sfauto.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备服务
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelService implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
    @TableId(value = "uuid", type = IdType.INPUT)
    private String uuid;

    /**
     * 设备ID
     */
    private String tid;

    /**
     * 服务名称
     */
    private String sname;

    /**
     * 服务说明
     */
    private String sdes;

    /**
     * 计算类别;实时、统计
     */
    private Integer calclass;

    /**
     * 计算类型;内置、脚本、插件
     */
    private Integer caltype;

    /**
     * 计算公式
     */
    private String expression;

    /**
     * 脚本类型;JS、Python
     */
    private Integer scripttype;

    /**
     * 输入参数
     */
    private String input;

    /**
     * 输出参数
     */
    private String output;


}
