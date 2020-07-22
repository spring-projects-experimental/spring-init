/*
 * Copyright 2020-2020 the original author or authors.
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
package org.springframework.init.maven;

import java.io.File;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

@Mojo(name = "testGenerate", defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES, requiresProject = true, threadSafe = true, requiresDependencyResolution = ResolutionScope.TEST, requiresDependencyCollection = ResolutionScope.TEST)
public class GenerateTestsMojo extends AbstractInitMojo {

	/**
	 * Directory containing the classes and resource files that should be scanned.
	 * 
	 * @since 1.0.0
	 */
	@Parameter(defaultValue = "${project.build.testOutputDirectory}", required = true)
	private File classesDirectory;
	/**
	 * Directory containing the generated sources.
	 * 
	 * @since 1.0.0
	 */
	@Parameter(defaultValue = "${project.build.directory}/generated-test-sources/init", required = true)
	private File outputDirectory;

	@Override
	protected void postProcess(MavenProject project) {
		project.addTestCompileSourceRoot(outputDirectory.getAbsolutePath());
	}

	@Override
	protected File getOutputDirectory() {
		return outputDirectory;
	}

	@Override
	protected File getClassesDirectory() {
		return classesDirectory;
	}
}