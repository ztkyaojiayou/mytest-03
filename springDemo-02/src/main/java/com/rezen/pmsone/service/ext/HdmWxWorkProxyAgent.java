package com.rezen.pmsone.service.ext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rezen.framework.web.core.httpclient.adapter.HttpClientFactory;
import com.rezen.framework.web.core.soa.SoaNode;
import com.rezen.framework.web.core.utils.RezenJsonUtil;
import com.rezen.mob.mhk.response.BaseResponseDTO;
import com.rezen.wxwork.entity.WxWorkUserInfo;
import com.rezen.wxwork.request.DepartmentListByUserIdRequest;
import com.rezen.wxwork.response.DepartmentListByUserIdResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-09-09 15:10
 * @Version: 1.0.0
 */

@Component
public class HdmWxWorkProxyAgent {

    @SoaNode(ProductName = "hdm", ServiceName = "hdm-wxwork-proxy", MethodName = "getUserInfoByWxWorkUserId")
    public BaseResponseDTO<WxWorkUserInfo> getUserInfoByWxWorkUserId(String wxWorkUserId) {
        Map<String, String> param = new HashMap<>(1);
        param.put("wxWorkUserid", wxWorkUserId);
        String response = HttpClientFactory.getSoaAdapter().doPostJson(RezenJsonUtil.toJSONString(param));
        return RezenJsonUtil.parseObject(response, new TypeReference<BaseResponseDTO<WxWorkUserInfo>>() {
        });
    }

    @SoaNode(ProductName = "hdm", ServiceName = "hdm-wxwork-proxy", MethodName = "getDepartmentListByWxWorkUserId")
    public BaseResponseDTO<DepartmentListByUserIdResponse> getDepartmentListByWxWorkUserId(DepartmentListByUserIdRequest request) {
        String response = HttpClientFactory.getSoaAdapter().doPostJson(RezenJsonUtil.toJSONString(request));
        return RezenJsonUtil.parseObject(response, new TypeReference<BaseResponseDTO<DepartmentListByUserIdResponse>>() {
        });
    }
}
