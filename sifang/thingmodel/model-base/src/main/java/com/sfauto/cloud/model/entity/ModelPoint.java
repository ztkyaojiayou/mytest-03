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
 * 点表
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点ID
     */
    @TableId(value = "pid", type = IdType.INPUT)
    private String pid;

    /**
     * 系统编号
     */
    private Integer sid;

    /**
     * 名称
     */
    private String name;

    /**
     * 别名
     */
    private String aliasname;

    /**
     * 点类别;遥测、遥信、遥脉
     */
    private Integer ptype;

    /**
     * RTU编号
     */
    private String rtuid;

    /**
     * 设备路径
     */
    private String pathname;


}
