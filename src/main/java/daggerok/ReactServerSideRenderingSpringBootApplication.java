package daggerok;

import daggerok.domain.Post;
import daggerok.domain.PostRestRepository;
import daggerok.domain.RestRepositoryConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

@SpringBootApplication
@Import(RestRepositoryConfig.class)
public class ReactServerSideRenderingSpringBootApplication {

    @Bean
    public CommandLineRunner testData(PostRestRepository posts) {
        return args -> Stream.of("one", "two", "3").map(Post::of).forEach(posts::save);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactServerSideRenderingSpringBootApplication.class, args);
    }
}
