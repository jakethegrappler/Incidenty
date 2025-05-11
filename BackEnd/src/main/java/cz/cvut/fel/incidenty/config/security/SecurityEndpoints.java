package cz.cvut.fel.incidenty.config.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityEndpoints {

    public static final String[] PUBLIC_URLS = {
            "/user/register",
            "/user/login",
            "/incident/create",
            "/incident/all",
            "/incident/stats/**" // ⬅️ přidáno pro veřejný přístup k statistikám
    };


    public static final String[] ADMIN_URLS = {
            "/admin/**",
            "/incident/**"
    };

    public static final String[] EMPLOYEE_URLS = {
            "/employee/**",
            "/incident/**"
    };


//    final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.POST,"/user/register"),
////            antMatcher(HttpMethod.POST,"/art/**"),
//            antMatcher(HttpMethod.POST,"/user/login")
//    );
//
//    final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.GET,"/**")
//    );
//
//    final RequestMatcher EMPLOYEE_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.GET,"/employees/**"),
//            antMatcher(HttpMethod.POST,"/group/create")
//    );
//
//    final RequestMatcher MULTI_ROLE_URLS = new OrRequestMatcher(
//   );
}