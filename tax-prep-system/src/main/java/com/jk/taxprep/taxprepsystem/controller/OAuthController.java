package com.jk.taxprep.taxprepsystem.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.jk.taxprep.taxprepsystem.config.JwtTokenProvider;
import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;


@Controller
public class OAuthController {
    private final OAuth2AuthorizedClientService clientService;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public OAuthController(OAuth2AuthorizedClientService clientService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.clientService = clientService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("/userinfo")
    @ResponseBody
    public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User user) {
        return user.getAttributes();
    }

    @GetMapping("/accessToken")
    @ResponseBody
    public String accessToken(Authentication auth, HttpServletResponse response) {

        //checking to see if the auth object that we pulled from security context is a OAuth2AuthenticationToken
        if(auth instanceof OAuth2AuthenticationToken) {

            //casting the Authentication object to be a OAuth2AuthenticationToken object
            OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken)auth;

            // retrieving the authorized client with *this specific* Authenticatation Principal (each user is unique)
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());

            Cookie cookie = new Cookie("Access Token", client.getAccessToken().getTokenValue());
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            //returning the value of the token
            return client.getAccessToken().getTokenValue();
        }
        return "";
    }

    @GetMapping("/oauth2Signin")
    public RedirectView handleGoogleOAuth2Redirect(Authentication authentication, HttpServletResponse response) throws UnsupportedEncodingException {
        try {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
            );

            String email = oauthToken.getPrincipal().getAttribute("email");
            // This will create a user if it doesn't exist, and return the user if it does
            User user = userService.findOrCreateUser(email);

            String jwt = jwtTokenProvider.generateToken(authentication);

            Cookie cookie = new Cookie("token", jwt);
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            response.addCookie(cookie);

            return new RedirectView("http://localhost:5173/main");
        } catch (Exception e) {
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            return new RedirectView("http://localhost:5173/login?error=" + errorMessage);
        }
        //     return new RedirectView("http://ericschang.net:5173/main");
        // } catch (Exception e) {
        //     String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
        //     return new RedirectView("http://ericschang.net:5173/login?error=" + errorMessage);
        // }
    }

}