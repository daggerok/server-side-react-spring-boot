package daggerok.react;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngineManager;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by mak on 9/7/16.
 */
@Component
public class React {

    private ThreadLocal<NashornScriptEngine> engineHolder;

    @PostConstruct
    public void init() {

        val nashornScriptEngine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");

        engineHolder = new ThreadLocal<NashornScriptEngine>() {
            @Override @SneakyThrows
            protected NashornScriptEngine initialValue() {
                nashornScriptEngine.eval(read("nashorn/polyfill.js"));
                nashornScriptEngine.eval(read("static/app.js"));
                return nashornScriptEngine;
            }
        };
    }

    @SneakyThrows
    public String renderApp() {

        val html = engineHolder.get().invokeFunction("renderServer");

        return String.valueOf(html);
    }

    @SneakyThrows
    private Reader read(String path) {

        val resource = getClass().getClassLoader().getResourceAsStream(path);

        return new InputStreamReader(resource);
    }
}
