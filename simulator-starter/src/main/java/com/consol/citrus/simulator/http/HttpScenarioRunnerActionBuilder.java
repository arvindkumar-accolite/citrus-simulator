/*
 * Copyright 2006-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.consol.citrus.simulator.http;

import com.consol.citrus.TestAction;
import com.consol.citrus.dsl.builder.HttpActionBuilder;
import com.consol.citrus.dsl.builder.HttpServerActionBuilder;
import com.consol.citrus.endpoint.Endpoint;
import com.consol.citrus.simulator.scenario.ScenarioRunner;
import org.springframework.context.ApplicationContext;

/**
 * @author Christoph Deppisch
 */
public class HttpScenarioRunnerActionBuilder extends HttpActionBuilder {

    /**
     * Scenario endpoint
     */
    private final Endpoint endpoint;

    /**
     * Scenario runner
     */
    private final ScenarioRunner runner;

    /**
     * Spring application context
     */
    private ApplicationContext applicationContext;

    public HttpScenarioRunnerActionBuilder(ScenarioRunner runner, Endpoint endpoint) {
        this.runner = runner;
        this.endpoint = endpoint;
    }

    /**
     * Default scenario server receive operation.
     *
     * @return
     * @deprecated use {@link #server()}.receive() instead
     */
    @Deprecated
    public TestAction receive(HttpBuilderSupport<HttpServerActionBuilder.HttpServerReceiveActionBuilder> configurer) {
        return server().receive(configurer);
    }

    /**
     * Default scenario server send response operation.
     *
     * @return
     * @deprecated use {@link #server()}.send() instead
     */
    @Deprecated
    public TestAction send(HttpBuilderSupport<HttpServerActionBuilder.HttpServerSendActionBuilder> configurer) {
        return server().send(configurer);
    }

    /**
     * http server builder for receiving http requests from http clients
     *
     * @return the HTTP Server action builder
     */
    public HttpServerScenarioRunnerActionBuilder server() {
        return new HttpServerScenarioRunnerActionBuilder(runner, endpoint)
                .withApplicationContext(applicationContext);
    }

    /**
     * http client builder for sending request to http server
     *
     * @return the HTTP Client action builder
     */
    public HttpClientScenarioRunnerActionBuilder client() {
        return new HttpClientScenarioRunnerActionBuilder(runner, endpoint)
                .withApplicationContext(applicationContext);
    }

    @Override
    public HttpScenarioRunnerActionBuilder withApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        return (HttpScenarioRunnerActionBuilder) super.withApplicationContext(applicationContext);
    }
}