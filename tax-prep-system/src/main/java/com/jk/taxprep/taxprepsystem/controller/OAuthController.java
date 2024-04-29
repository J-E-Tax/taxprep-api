// package com.jk.taxprep.taxprepsystem.controller;

// import java.util.Map;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
// import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

// @Controller
// public class OAuthController {
//     private final OAuth2AuthorizedClientService clientService;

//     public OAuthController(OAuth2AuthorizedClientService clientService) {
//         this.clientService = clientService;
//     }

//     @GetMapping("/userinfo")
//     @ResponseBody
//     public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User user) {
//         return user.getAttributes();
//     }

//     @GetMapping("/accessToken")
//     @ResponseBody
//     public String accessToken(Authentication auth) {

//         //checking to see if the auth object that we pulled from security context is a OAuth2AuthenticationToken
//         if(auth instanceof OAuth2AuthenticationToken) {

//             //casting the Authentication object to be a OAuth2AuthenticationToken object
//             OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken)auth;

//             // retrieving the authorized client with *this specific* Authenticatation Principal (each user is unique)
//             OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());

//             /* Something like this for adding access token to http only cookie - make sure you take in HttpServletResponse as parameter
//             Cookie cookie = new Cookie("Access Token", client.getAccessToken().getTokenValue());
//             cookie.isHttpOnly();
//             response.addCookie(cookie);
//             */

//             //returning the value of the token
//             return client.getAccessToken().getTokenValue();
//         }
//         return "";
//     }


// }
