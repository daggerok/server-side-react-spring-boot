package daggerok.react;

import daggerok.domain.Post;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by mak on 9/7/16.
 */
@Component
public class React {

    private Invocable nashorn;

    @SneakyThrows
    @PostConstruct
    private void init() {
        // val engine = new ScriptEngineManager().getEngineByExtension("js");
        val engine = new ScriptEngineManager().getEngineByName("nashorn");

        engine.eval(read("nashorn/polyfill.js"));
        engine.eval(read("static/app.js"));

        this.nashorn = (Invocable) engine;
    }

    @SneakyThrows
    public String renderApp(List<Post> posts) {

        val html = call("renderServer", posts);

        return String.valueOf(html);
    }

    @SneakyThrows
    public Object call(String function, Object... arguments) {
        return nashorn.invokeFunction(function, arguments);
    }

    @SneakyThrows
    private Reader read(String path) {

        val resource = getClass().getClassLoader().getResourceAsStream(path);

        return new InputStreamReader(resource);
    }
}
