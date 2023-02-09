package me.kreal.attendance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

//The jwt filter that we want to add to the chain of filters of Spring Security
@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<AuthUserDetails> authUserDetailOptional = jwtProvider.resolveToken(request); // extract jwt from request, generate a userdetails object

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Vary", "Origin");

        // Access-Control-Max-Age
        response.setHeader("Access-Control-Max-Age", "3600");

        // Access-Control-Allow-Credentials
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Access-Control-Allow-Methods
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");

        // Access-Control-Allow-Headers
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");

        if (authUserDetailOptional.isPresent()){
            AuthUserDetails authUserDetails = authUserDetailOptional.get();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    authUserDetails,
                    null,
                    authUserDetails.getAuthorities()
            ); // generate authentication object

//            authentication.setDetails(authUserDetails);

            SecurityContextHolder.getContext().setAuthentication(authentication); // put authentication object in the secruitycontext
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");

        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        System.out.println(path);
        return "/user".equals(path) || path.startsWith("/zoom") || path.startsWith("/error") || path.endsWith(".png") || path.endsWith(".jsp") ||path.endsWith(".css") || path.endsWith(".js");
    }


}
