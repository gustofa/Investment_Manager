package groupone.java.views;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
	
	 public static void main( String[] args ) {

	        get("/hello", (req, res) -> "Hello World");


	        get("/velocity", (request, response) -> {
	            Map<String, Object> model = new HashMap<String, Object>();
	            model.put("hello", "Welcome");
	            model.put("person", new Person("Foobar"));

	            // The wm files are located under the resources directory
	            return new ModelAndView(model, "hello.vm");
	        }, new VelocityTemplateEngine());

	        get("/json", (request, response) -> {
	            return new Person("Pepe");
	        }, new JSONTransformer());
	    }

	    public static class Person {
	        private String name;

	        public Person(String name) {
	            this.name = name;
	        }

	        public String getName() {
	            return name;
	        }
	        public void setName(String name) {
	            this.name = name;
	        }
	    }

}
