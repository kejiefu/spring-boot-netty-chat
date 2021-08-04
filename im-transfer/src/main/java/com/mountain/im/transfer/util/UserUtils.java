package com.mountain.im.transfer.util;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import com.mountain.common.domain.BusinessException;
import com.mountain.common.domain.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/5/11 17:08
 * @Created by kejiefu
 */
@Component
@Slf4j
public class UserUtils {

    public Long getUserId(String token) {
        if (!StringUtils.isEmpty(token)) {
            JWTPayload jwtPayload = JWT.of(token.substring(7)).getPayload();
            JSONObject jsonObject = jwtPayload.getClaimsJson();
            return jsonObject.getLong("userId");
        }
        log.error("===== token解析异常 =====");
        throw new BusinessException(ReturnCode.TOKEN_IS_NULL);
    }

    public String getUsername(String token) {
        if (!StringUtils.isEmpty(token)) {
            JWTPayload jwtPayload = JWT.of(token.substring(7)).getPayload();
            JSONObject jsonObject = jwtPayload.getClaimsJson();
            return jsonObject.getStr("userName");
        }
        log.error("===== token解析异常 =====");
        throw new BusinessException(ReturnCode.TOKEN_IS_NULL);
    }


}
