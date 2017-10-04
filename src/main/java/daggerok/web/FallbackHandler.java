package daggerok.web;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by mak on 9/7/16.
 */
@Slf4j
//@Controller
@RequiredArgsConstructor
public class FallbackHandler implements ErrorController {

    static final String ENDPOINT = "/error";

    // @org.springframework.beans.factory.annotation.Autowired
    final ServletContext servletContext;

    @SneakyThrows
    @GetMapping(ENDPOINT)
    void handle404(final HttpServletRequest request, final HttpServletResponse response, final Exception e) {

        if (NOT_FOUND.value() != response.getStatus()) {
            return;
        }

        Optional.ofNullable(e).ifPresent(ex -> {

            if (Optional.ofNullable(e.getMessage()).isPresent()) {

                log.error("{} fallback: {} {}", e.getClass().getSimpleName(), e.getMessage());

            } else {

                log.error("{} fallback", e.getClass().getSimpleName());
            }
        });

        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }

        response.sendRedirect(servletContext.getContextPath().concat("/"));
    }

    @Override
    public String getErrorPath() {
        return ENDPOINT;
    }
}
