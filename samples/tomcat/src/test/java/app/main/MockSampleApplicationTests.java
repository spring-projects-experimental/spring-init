/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.DispatcherServlet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Dave Syer
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SampleController.class, properties = "spring.functional.enabled=false")
public class MockSampleApplicationTests {

	@MockBean
	private Foo foo;

	@Autowired
	private MockMvc mockMvc;

	@Autowired(required = false)
	private CommandLineRunner runner;

	@Autowired(required = false)
	private DispatcherServlet servlet;

	@Test
	public void servlet() {
		assertThat(servlet.getClass().getName())
				.isEqualTo("org.springframework.test.web.servlet.TestDispatcherServlet");
	}

	@Test
	public void runner() {
		assertThat(runner).isNull();
	}

	@Test
	public void test() throws Exception {
		Mockito.when(foo.getValue()).thenReturn("Test");
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().string("Test"));
	}

}