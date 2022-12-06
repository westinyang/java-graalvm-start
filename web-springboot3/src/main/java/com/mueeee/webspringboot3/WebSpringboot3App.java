package com.mueeee.webspringboot3;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;

@SpringBootApplication
public class WebSpringboot3App {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(WebSpringboot3App.class);
		springApplication.setBannerMode(Banner.Mode.CONSOLE);   // Banner.Mode.OFF : Close Banner
		springApplication.run(args);
	}

	/*static {
		// Disable e.printStackTrace(); System.err.println();
		// Disable SpringBoot3 + POI + Log4j GraalVM Native Image Runtime Error:
		//     - ERROR StatusLogger Unable to load services for service class org.apache.logging.log4j.spi.Provider
		//     - Caused by: com.oracle.svm.core.jdk.UnsupportedFeatureError: Defining hidden classes at runtime is not supported.
		//     - ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
		PrintStream err = new PrintStream(System.err) {
			@Override
			public void print(char c) {}

			@Override
			public void print(String x) {}

			@Override
			public void print(Object x) {}

			@Override
			public void println(char c) {}

			@Override
			public void println(String x) {}

			@Override
			public void println(Object x) {}
		};
		System.setErr(err);
	}*/

}
