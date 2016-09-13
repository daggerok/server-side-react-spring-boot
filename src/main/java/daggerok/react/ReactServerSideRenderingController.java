package daggerok.react;

import com.fasterxml.jackson.databind.ObjectMapper;
import daggerok.domain.PostRestRepository;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by mak on 9/7/16.
 */
@Controller
public class ReactServerSideRenderingController {

    final React react;
    final PostRestRepository postRestRepository;
    final ObjectMapper objectMapper;

    public ReactServerSideRenderingController(React react,
                                              PostRestRepository postRestRepository,
                                              ObjectMapper objectMapper) {
        this.react = react;
        this.postRestRepository = postRestRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    @SneakyThrows
    public String index(Model model) {
        val posts = postRestRepository.findAll();
        model.addAttribute("data", react.renderApp(posts));
        model.addAttribute("posts", objectMapper.writeValueAsString(posts));
        return "index";
    }
}
