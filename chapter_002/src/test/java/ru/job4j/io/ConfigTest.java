package ru.job4j.io;

import org.junit.Test;
import ru.jo4j.io.Config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
        assertThat(
                config.value("hibernate.connection.password"),
                is("password")
        );
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
        config.value("## PostgreSQL");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPairWithEmptyLine() {
        String path = "./data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
        assertThat(
                config.value("hibernate.connection.password"),
                is("password")
        );
        config.value("\r\n");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPairWithWrongLine() {
        String path = "./data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
        assertThat(
                config.value("hibernate.connection.password"),
                is("password")
        );
        config.value("----");
    }

}
