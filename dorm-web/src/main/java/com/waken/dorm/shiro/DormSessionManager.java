package com.waken.dorm.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
/**
 * @ClassName SessionManager
 * @Description DormSessionManager
 * @Author zhaoRong
 * @Date 2019/4/8 17:25
 **/
public class DormSessionManager extends DefaultWebSessionManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String AUTHORIZATION = "userToken";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public DormSessionManager() {
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头，或者请求参数中的Token
        logger.info("后台用户userToken为："+WebUtils.toHttp(request).getHeader(AUTHORIZATION));
        String id = StringUtils.isEmpty(WebUtils.toHttp(request).getHeader(AUTHORIZATION))
                ? request.getParameter(AUTHORIZATION) : WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        // 如果请求头中有 userToken 则其值为sessionId
        if (StringUtils.isNotEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return id;
        } else {
            // 否则按默认规则从cookie取sessionId
            Serializable sessionId = super.getSessionId(request, response);
            logger.info("按默认规则从cookie取sessionId为："+sessionId);
            return sessionId;
        }
    }

    /**
     * 获取session 优化单次请求需要多次访问redis的问题
     *
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (request != null && null != sessionId) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                return (Session) sessionObj;
            }
        }

        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
