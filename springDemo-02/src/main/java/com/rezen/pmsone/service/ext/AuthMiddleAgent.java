package com.rezen.pmsone.service.ext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rezen.framework.web.core.httpclient.adapter.HttpClientFactory;
import com.rezen.framework.web.core.soa.SoaNode;
import com.rezen.framework.web.core.utils.RezenJsonUtil;
import com.rezen.mob.mhk.response.BaseResponseDTO;
import com.rezen.one.auth.middle.dto.base.PmsUserDTO;
import com.rezen.one.auth.middle.dto.user.GetPmsUserBaseInfoRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-09-09 15:09
 * @Version: 1.0.0
 */

@Component
public class AuthMiddleAgent {
    @SoaNode(ProductName = "rezen-pms", ServiceName = "rezen-one-auth-middle-service", MethodName = "getPmsUserBaseInfo")
    public BaseResponseDTO<PmsUserDTO> getPmsUserBaseInfo(GetPmsUserBaseInfoRequestDTO request) {
        String response = HttpClientFactory.getSoaAdapter().doPostJson(RezenJsonUtil.toJSONString(request));
        return RezenJsonUtil.parseObject(response, new TypeReference<BaseResponseDTO<PmsUserDTO>>() {
        });
    }
}
