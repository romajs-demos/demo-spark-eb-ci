package com.romajs.spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class Application {

	static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		Application application = new Application();
		application.configureRoutes();
		application.start();
	}

	public void start() {
		SparkUtils.createServerWithRequestLog(logger);
	}

	public void configureRoutes() {
		Spark.get("/hello", (req, res) -> "Hello World");
	}
}
