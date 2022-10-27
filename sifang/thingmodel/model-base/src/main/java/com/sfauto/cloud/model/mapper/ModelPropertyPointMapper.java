/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.sfauto.cloud.model.mapper;

import com.sfauto.cloud.model.dto.ModelPointDto;
import com.sfauto.cloud.model.entity.ModelPropertyPoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 属性点表对应 Mapper 接口
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
public interface ModelPropertyPointMapper extends BaseMapper<ModelPropertyPoint> {
    List<ModelPointDto> getAllPropertyPoint();
}
