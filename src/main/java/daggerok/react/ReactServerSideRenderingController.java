package daggerok.react;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by mak on 9/7/16.
 */
@Controller
public class ReactServerSideRenderingController {

    @Autowired React react;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("data", react.renderApp());
        return "index";
    }
}
