//package com.axioma.axiomatrainee.security;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SimpleCorsFilter implements Filter {
//
//    public SimpleCorsFilter() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        System.out.println("CORSFilter HTTP Request: " + request.getMethod());
//
//        // Authorize (allow) all domains to consume the content
//        ((HttpServletResponse) res).addHeader("Access-Control-Allow-Origin", "*");
//        ((HttpServletResponse) res).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
//
//        HttpServletResponse resp = (HttpServletResponse) res;
//
//        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
//        if (request.getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//            return;
//        }
//
//        // pass the request along the filter chain
//        chain.doFilter(request, res);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void destroy() {
//    }
//}