/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-08-11 17:42
 * @Version: 1.0.0
 */

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    /**
     * Cookie 辅助类
     */
    public class CookieUtils {


        /**
         * 根据cookie名称获得cookie
         * @param request
         * @param name  cookie的名称
         * @return
         */
        public static Cookie getCookie(HttpServletRequest request, String name) {
            Assert.notNull(request);
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie c : cookies) {
                    if (c.getName().equals(name)) {
                        return c;
                    }
                }
            }
            return null;
        }


        /**
         * 保存cookie 保存在根目录下
         * @param request
         * @param response
         * @param name cookie名称
         * @param value  cookie的值
         * @param expiry  过期时间（可以为空,单位：秒）
         * @param domain  域名（可以为空）
         * @return
         */
        public static Cookie addCookie(HttpServletRequest request,
                                       HttpServletResponse response, String name, String value,
                                       Integer expiry, String domain) {
            Cookie cookie = new Cookie(name, value);
            if (expiry != null) {
                cookie.setMaxAge(expiry);
            }
            if (StringUtils.isNotBlank(domain)) {
                cookie.setDomain(domain);
            }
            String ctx = request.getContextPath();
            cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
            response.addCookie(cookie);
            return cookie;
        }


        /**
         * 清除cookie
         * @param request
         * @param response
         * @param name cookie名称
         * @param domain
         */
        public static void cancleCookie(HttpServletRequest request,
                                        HttpServletResponse response, String name, String domain) {
            Cookie cookie = new Cookie(name, "");
            cookie.setMaxAge(0);
            String ctx = request.getContextPath();
            cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
            if (StringUtils.isNotBlank(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        }

        /**
         *  向浏览器中添加cookie
         */
        public void doGet(HttpServletRequest request,HttpServletResponse response){
            Cookie mycookie = new Cookie("tkzou", "ctrip");

        }

    }
