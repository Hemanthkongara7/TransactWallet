package com.hemanth.wallet.web.Security;

import com.hemanth.wallet.Exception.WalletException;
import com.hemanth.wallet.Exception.WalletExceptionMessage;
import com.hemanth.wallet.service.dto.WalletUserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws WalletException {
        logger.info("prehandle");
        if (!(handler instanceof HandlerMethod method)) {
            return true;
        }
        logger.info("before");
        if (method.hasMethodAnnotation(Authorized.class)) {
            logger.info("Above the first request");
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.info("not authorized");
                throw new WalletException(WalletExceptionMessage.UN_AUTHORIZED);
            }

            String token = authHeader.substring(7);
            logger.info("token "+token);
            AuthenticationClaims claims = jwtService.validateToken(token);

            if (claims == null || claims.getName() == null) {
                throw new WalletException(WalletExceptionMessage.UN_AUTHORIZED);
            }

            request.setAttribute("name", claims.getName());
            request.setAttribute("id", claims.getId());
            request.setAttribute("email", claims.getEmail());
            request.setAttribute("roles",claims.getRoles());
        }
        return true;

    }
}

