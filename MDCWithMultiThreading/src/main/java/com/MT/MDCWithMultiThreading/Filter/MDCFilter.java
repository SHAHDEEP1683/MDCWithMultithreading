    package com.MT.MDCWithMultiThreading.Filter;

    import jakarta.servlet.*;
    import org.slf4j.MDC;
    import org.springframework.stereotype.Component;

    import java.io.IOException;
    import java.util.UUID;

    @Component
    public class MDCFilter implements Filter {

        private static final String REQUEST_ID = "requestId";

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            try {
                String requestId = UUID.randomUUID().toString();
                MDC.put(REQUEST_ID, requestId);
                filterChain.doFilter(servletRequest, servletResponse);
            } finally {
                MDC.clear();
            }
        }
    }
