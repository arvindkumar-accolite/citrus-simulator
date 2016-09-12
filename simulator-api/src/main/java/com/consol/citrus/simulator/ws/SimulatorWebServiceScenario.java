/*
 * Copyright 2006-2016 the original author or authors.
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

package com.consol.citrus.simulator.ws;

import com.consol.citrus.channel.ChannelSyncEndpoint;
import com.consol.citrus.dsl.builder.*;
import com.consol.citrus.dsl.design.ExecutableTestDesignerComponent;
import com.consol.citrus.simulator.scenario.SimulatorScenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Christoph Deppisch
 */
public class SimulatorWebServiceScenario extends ExecutableTestDesignerComponent implements SimulatorScenario {

    @Autowired
    @Qualifier("simulatorWsInboundEndpoint")
    protected ChannelSyncEndpoint simInbound;

    @Override
    public ReceiveMessageBuilder receiveScenarioRequest() {
        return (ReceiveMessageBuilder)
                receive(simInbound)
                    .description("Received SOAP request");
    }

    @Override
    public SendMessageBuilder sendScenarioResponse() {
        return (SendMessageBuilder)
                send(simInbound)
                    .description("Sending SOAP response");
    }

    /**
     * Sends SOAP fault as scenario response.
     * @return
     */
    public SendSoapFaultBuilder sendScenarioFault() {
        return (SendSoapFaultBuilder)
                sendSoapFault(simInbound)
                    .description("Sending SOAP fault");
    }
}