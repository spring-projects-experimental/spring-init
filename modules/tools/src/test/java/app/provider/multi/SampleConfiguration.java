package app.provider.multi;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfiguration {

	@Value("${app.value}")
	private String message;

	@Bean
	public Foo foo() {
		return new Foo();
	}

	@Bean
	public Bar<Foo, Foo> bar(Map<String, Foo> foo) {
		return new Bar<>(new Foo("pre"), foo.values().iterator().next());
	}

	@Bean
	// This one generates an initializer that won't compile on command line (Eclipse is
	// fine)
	public CommandLineRunner runner(ObjectProvider<Bar<Foo, Foo>> bar) {
		return args -> {
			System.out.println("Message: " + message);
			System.out.println("Bar: " + bar.stream().map(v -> v.toString())
					.collect(Collectors.joining(",")));
			System.out.println("Foo: " + bar.stream().map(v -> v.getFoo().toString())
					.collect(Collectors.joining(",")));
		};
	}

}
