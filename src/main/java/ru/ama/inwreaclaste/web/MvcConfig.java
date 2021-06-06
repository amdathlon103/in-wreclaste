package ru.ama.inwreaclaste.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
@Configuration
@EnableWebMvc
@ComponentScan( basePackages = "ru.ama.inwreaclaste")
public class MvcConfig {
}
