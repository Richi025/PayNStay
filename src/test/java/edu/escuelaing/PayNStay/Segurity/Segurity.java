package edu.escuelaing.PayNStay.Segurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.escuelaing.PayNStay.Security.JwtRequestFilter;
import edu.escuelaing.PayNStay.Security.JwtUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class JwtRequestFilterTest {

    private static final String CLAIMS_ROLES_KEY = null;

    private static final BooleanSupplier TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE = null;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private FilterChain filterChain;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldAuthenticateWithValidToken() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        String token = "Bearer validToken";
        request.addHeader("Authorization", token);

        Claims claims = mock(Claims.class);
        when(claims.getSubject()).thenReturn("user");
        when(claims.get(CLAIMS_ROLES_KEY, List.class)).thenReturn(new ArrayList<>());
        when(jwtUtil.extractAndVerifyClaims("validToken")).thenReturn(claims);
        Method doFilterInternal = JwtRequestFilter.class.getDeclaredMethod("doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        doFilterInternal.setAccessible(true);
        doFilterInternal.invoke(jwtRequestFilter, request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(securityContext).setAuthentication(any());
    }

    @Test
    void shouldContinueChainWithNoAuthorizationHeader() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        Method doFilterInternal = JwtRequestFilter.class.getDeclaredMethod("doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        doFilterInternal.setAccessible(true);
        doFilterInternal.invoke(jwtRequestFilter, request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(securityContext, never()).setAuthentication(any());
    }
}