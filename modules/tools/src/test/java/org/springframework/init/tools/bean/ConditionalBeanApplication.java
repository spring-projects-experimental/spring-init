/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.init.tools.bean;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class ConditionalBeanApplication {

}

@Configuration
class SampleConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "sample", value = "enabled", havingValue = "true", matchIfMissing = false)
	public Foo foo() {
		return new Foo("Hello");
	}

}

class Foo {

	private String value;

	public Foo() {
	}

	public Foo(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Foo [value=" + this.value + "]";
	}

}