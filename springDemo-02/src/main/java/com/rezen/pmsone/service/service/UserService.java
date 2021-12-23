package com.rezen.pmsone.service.service;

import com.rezen.mob.common.entity.WxWorkDepartmentInfo;
import com.rezen.mob.common.request.WxWorkUserInfoByPmsStaffCodeRequest;
import com.rezen.mob.common.response.WxWorkUserInfoByPmsStaffCodeResponse;
import com.rezen.mob.mhk.response.BaseResponseDTO;
import com.rezen.one.auth.middle.dto.base.PmsUserDTO;
import com.rezen.one.auth.middle.dto.user.GetPmsUserBaseInfoRequestDTO;
import com.rezen.pmsone.service.ext.AuthMiddleAgent;
import com.rezen.pmsone.service.ext.HdmWxWorkProxyAgent;
import com.rezen.wxwork.entity.DepartmentInfo;
import com.rezen.wxwork.entity.WxWorkUserInfo;
import com.rezen.wxwork.request.DepartmentListByUserIdRequest;
import com.rezen.wxwork.response.DepartmentListByUserIdResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-09-09 14:59
 * @Version: 1.0.0
 */

@Service
public class UserService {

    @Autowired
    private AuthMiddleAgent authMiddleAgent;
    @Autowired
    private HdmWxWorkProxyAgent hdmWxWorkProxyAgent;

    public BaseResponseDTO<WxWorkUserInfoByPmsStaffCodeResponse> getWxWorkUserInfoByPmsStaffCode(WxWorkUserInfoByPmsStaffCodeRequest request) {
        final String staffCode = request.getStaffCode();
        // 1. 根据PMS操作员代码获取其企业微信ID
        GetPmsUserBaseInfoRequestDTO pmsUserInfoRq = new GetPmsUserBaseInfoRequestDTO();
        pmsUserInfoRq.setUserName(staffCode);
        BaseResponseDTO<PmsUserDTO> pmsUserInfoRs = authMiddleAgent.getPmsUserBaseInfo(pmsUserInfoRq);
        if (Objects.isNull(pmsUserInfoRs) || Objects.isNull(pmsUserInfoRs.getBody())) {
            return BaseResponseDTO.failed("当前用户不是PMS用户");
        }
        String wxWorkUserId = pmsUserInfoRs.getBody().getWechartWorkId();
        if (StringUtils.isEmpty(wxWorkUserId)) {
            return BaseResponseDTO.failed("当前用户尚未绑定企业微信");
        }
        // 2. 根据企业微信ID获取企业微信用户信息
        BaseResponseDTO<WxWorkUserInfo> wxWorkUserInfoRs = hdmWxWorkProxyAgent.getUserInfoByWxWorkUserId(wxWorkUserId);
        if (Objects.isNull(wxWorkUserInfoRs) || Objects.isNull(wxWorkUserInfoRs.getBody())) {
            return BaseResponseDTO.failed("当前用户的企业微信信息不完整");
        }
        // 3. 根据企业微信id获取企业微信组织架构
        DepartmentListByUserIdRequest wxWorkDepartmentListRq = new DepartmentListByUserIdRequest();
        wxWorkDepartmentListRq.setWxWorkUserId(wxWorkUserId);
        BaseResponseDTO<DepartmentListByUserIdResponse> wxWorkDepartmentListRs = hdmWxWorkProxyAgent.getDepartmentListByWxWorkUserId(wxWorkDepartmentListRq);
        // 4. 组装结果集
        WxWorkUserInfo wxWorkUserInfo = wxWorkUserInfoRs.getBody();
        WxWorkUserInfoByPmsStaffCodeResponse response = new WxWorkUserInfoByPmsStaffCodeResponse();
        response.setWxWorkUserId(wxWorkUserInfo.getUserId());
        response.setWxWorkUserName(wxWorkUserInfo.getName());
        response.setAvatarUrl(wxWorkUserInfo.getAvatar());
        response.setThumbAvatarUrl(wxWorkUserInfo.getThumbAvatar());
        if (Objects.isNull(wxWorkDepartmentListRs) || Objects.isNull(wxWorkDepartmentListRs.getBody())) {
            response.setDepartmentInfos(Collections.emptyList());
        } else {
            List<DepartmentInfo> wxDepartmentInfos = wxWorkDepartmentListRs.getBody().getDepartmentInfos();
            List<WxWorkDepartmentInfo> departmentInfos = new ArrayList<>(wxDepartmentInfos.size());
            for (DepartmentInfo wxDepartmentInfo : wxDepartmentInfos) {
                WxWorkDepartmentInfo wxWorkDepartmentInfo = new WxWorkDepartmentInfo();
                BeanUtils.copyProperties(wxDepartmentInfo, wxWorkDepartmentInfo);
                departmentInfos.add(wxWorkDepartmentInfo);
            }
            response.setDepartmentInfos(departmentInfos);
        }
        return BaseResponseDTO.ok(response);
    }
}
