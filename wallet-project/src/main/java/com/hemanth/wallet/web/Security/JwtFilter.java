package com.hemanth.wallet.web.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemanth.wallet.Exception.WalletException;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    @Autowired
    private JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, WalletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ContentCachingResponseWrapper cachingResponse = new ContentCachingResponseWrapper(httpResponse);

        chain.doFilter(request, cachingResponse);

        byte[] responseData = cachingResponse.getContentAsByteArray();
        String responseBody = new String(responseData, cachingResponse.getCharacterEncoding());

        logger.info("Response Body: " + responseBody);

        String requestURI = ((HttpServletRequest) request).getRequestURI();
        if ("/login/authorise".equals(requestURI)) {

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                WalletUserResponseDto userResponseDto = objectMapper.readValue(responseBody, WalletUserResponseDto.class);

                if (userResponseDto != null && userResponseDto.getId() != null) {
                    logger.info("Extracted User: " + userResponseDto.getName() + ", ID: " + userResponseDto.getId());

                    String token = jwtService.generateToken(userResponseDto.getEmail(), userResponseDto.getName(), userResponseDto.getId() , userResponseDto.getRoles());

                    cachingResponse.setHeader("token", token);
                    logger.info("Token generated and added to response header.");
                } else {
                    logger.warn("User data missing or invalid in response body. Token not generated.");
                }
            } catch (WalletException e) {
                logger.error("Failed to deserialize response body to WalletUserResponseDto", e);
            }
        }

        // Write the modified response back to the client
        cachingResponse.copyBodyToResponse();
    }
}
