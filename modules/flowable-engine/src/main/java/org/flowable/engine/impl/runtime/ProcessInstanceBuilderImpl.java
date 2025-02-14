/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.flowable.engine.impl.runtime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.engine.impl.RuntimeServiceImpl;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceBuilder;
import org.flowable.form.api.FormInfo;

/**
 * @author Bassam Al-Sarori
 * @author Joram Barrez
 */
public class ProcessInstanceBuilderImpl implements ProcessInstanceBuilder {

    protected RuntimeServiceImpl runtimeService;

    protected String processDefinitionId;
    protected String processDefinitionKey;
    protected String processDefinitionParentDeploymentId;
    protected String messageName;
    protected String processInstanceName;
    protected String businessKey;
    protected String businessStatus;
    protected String callbackId;
    protected String callbackType;
    protected String referenceId;
    protected String referenceType;
    protected String stageInstanceId;
    protected String tenantId;
    protected String overrideDefinitionTenantId;
    protected String predefinedProcessInstanceId;
    protected Map<String, Object> variables;
    protected Map<String, Object> transientVariables;
    protected Map<String, Object> startFormVariables;
    protected String outcome;
    protected Map<String, Object> extraFormVariables;
    protected FormInfo extraFormInfo;
    protected String extraFormOutcome;
    protected boolean fallbackToDefaultTenant;
    protected Map<String, Set<String>> userIdentityLinks;
    protected Map<String, Set<String>> groupIdentityLinks;

    public ProcessInstanceBuilderImpl(RuntimeServiceImpl runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public ProcessInstanceBuilder processDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder processDefinitionParentDeploymentId(String parentDeploymentId) {
        this.processDefinitionParentDeploymentId = parentDeploymentId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder processDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
        return this;
    }

    @Override
    public ProcessInstanceBuilder messageName(String messageName) {
        this.messageName = messageName;
        return this;
    }

    @Override
    public ProcessInstanceBuilder name(String processInstanceName) {
        this.processInstanceName = processInstanceName;
        return this;
    }

    @Override
    public ProcessInstanceBuilder businessKey(String businessKey) {
        this.businessKey = businessKey;
        return this;
    }

    @Override
    public ProcessInstanceBuilder businessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
        return this;
    }

    @Override
    public ProcessInstanceBuilder callbackId(String callbackId) {
        this.callbackId = callbackId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder callbackType(String callbackType) {
        this.callbackType = callbackType;
        return this;
    }

    @Override
    public ProcessInstanceBuilder referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder referenceType(String referenceType) {
        this.referenceType = referenceType;
        return this;
    }

    @Override
    public ProcessInstanceBuilder stageInstanceId(String stageInstanceId) {
        this.stageInstanceId = stageInstanceId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder overrideProcessDefinitionTenantId(String tenantId) {
        this.overrideDefinitionTenantId = tenantId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder predefineProcessInstanceId(String processInstanceId) {
        this.predefinedProcessInstanceId = processInstanceId;
        return this;
    }

    @Override
    public ProcessInstanceBuilder variables(Map<String, Object> variables) {
        if (this.variables == null) {
            this.variables = new HashMap<>();
        }
        if (variables != null) {
            this.variables.putAll(variables);
        }
        return this;
    }

    @Override
    public ProcessInstanceBuilder variable(String variableName, Object value) {
        if (this.variables == null) {
            this.variables = new HashMap<>();
        }
        this.variables.put(variableName, value);
        return this;
    }

    @Override
    public ProcessInstanceBuilder transientVariables(Map<String, Object> transientVariables) {
        if (this.transientVariables == null) {
            this.transientVariables = new HashMap<>();
        }
        if (transientVariables != null) {
            this.transientVariables.putAll(transientVariables);
        }
        return this;
    }

    @Override
    public ProcessInstanceBuilder transientVariable(String variableName, Object value) {
        if (this.transientVariables == null) {
            this.transientVariables = new HashMap<>();
        }
        this.transientVariables.put(variableName, value);
        return this;
    }

    @Override
    public ProcessInstanceBuilder startFormVariables(Map<String, Object> startFormVariables) {
        if (this.startFormVariables == null) {
            this.startFormVariables = new HashMap<>();
        }
        if (startFormVariables != null) {
            this.startFormVariables.putAll(startFormVariables);
        }
        return this;
    }

    @Override
    public ProcessInstanceBuilder startFormVariable(String variableName, Object value) {
        if (this.startFormVariables == null) {
            this.startFormVariables = new HashMap<>();
        }
        this.startFormVariables.put(variableName, value);
        return this;
    }

    @Override
    public ProcessInstanceBuilder outcome(String outcome) {
        this.outcome = outcome;
        return this;
    }

    @Override
    public ProcessInstanceBuilder formVariables(Map<String, Object> formVariables, FormInfo formInfo, String formOutcome) {
        if (formInfo == null) {
            throw new FlowableIllegalArgumentException("formInfo is null");
        }

        if (this.extraFormVariables == null) {
            this.extraFormVariables = new HashMap<>();
        }

        if (formVariables != null) {
            this.extraFormVariables.putAll(formVariables);
        }

        this.extraFormInfo = formInfo;
        this.extraFormOutcome = formOutcome;
        return this;
    }

    @Override
    public ProcessInstanceBuilder fallbackToDefaultTenant() {
        this.fallbackToDefaultTenant = true;
        return this;
    }

    @Override
    public ProcessInstanceBuilder userIdentityLinks(Map<String, Set<String>> userIdentityLinks) {
        if (userIdentityLinks != null) {
            if (this.userIdentityLinks == null) {
                this.userIdentityLinks = new HashMap<>();
            }
            mergeIdentityLinks(this.userIdentityLinks, userIdentityLinks);
        }
        return this;
    }

    @Override
    public ProcessInstanceBuilder userIdentityLink(String identityLinkType, String user) {
        if (identityLinkType != null && user != null) {
            Set<String> users = new HashSet<>();
            users.add(user);
            Map<String, Set<String>> identityLinkMap = new HashMap<>();
            identityLinkMap.put(identityLinkType, users);
            userIdentityLinks(identityLinkMap);
        }
        return this;
    }

    @Override
    public ProcessInstanceBuilder groupIdentityLinks(Map<String, Set<String>> groupIdentityLinks) {
        if (groupIdentityLinks != null) {
            if (this.groupIdentityLinks == null) {
                this.groupIdentityLinks = new HashMap<>();
            }
            mergeIdentityLinks(this.groupIdentityLinks, groupIdentityLinks);
        }
        return this;
    }

    @Override
    public ProcessInstanceBuilder groupIdentityLink(String identityLinkType, String group) {
        if (identityLinkType != null && group != null) {
            Set<String> groups = new HashSet<>();
            groups.add(group);
            Map<String, Set<String>> identityLinkMap = new HashMap<>();
            identityLinkMap.put(identityLinkType, groups);
            groupIdentityLinks(identityLinkMap);
        }
        return this;
    }

    private void mergeIdentityLinks(Map<String, Set<String>> target, Map<String, Set<String>> additionalLinks) {
        for (Map.Entry<String, Set<String>> additionalLink : additionalLinks.entrySet()) {
            String linkType = additionalLink.getKey();
            Set<String> parties = additionalLink.getValue();
            if (linkType != null && parties != null) {
                target.merge(linkType, parties, (party1, party2) -> {
                    Set<String> combinedParties = new HashSet<String>(party1);
                    combinedParties.addAll(party2);
                    return combinedParties;
                });
            }
        }
    }

    @Override
    public ProcessInstance start() {
        return runtimeService.startProcessInstance(this);
    }

    @Override
    public ProcessInstance startAsync() {
        return runtimeService.startProcessInstanceAsync(this);
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public String getProcessDefinitionParentDeploymentId() {
        return processDefinitionParentDeploymentId;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getProcessInstanceName() {
        return processInstanceName;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public String getStageInstanceId() {
        return stageInstanceId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getOverrideDefinitionTenantId() {
        return overrideDefinitionTenantId;
    }

    public String getPredefinedProcessInstanceId() {
        return predefinedProcessInstanceId;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public Map<String, Object> getTransientVariables() {
        return transientVariables;
    }

    public Map<String, Object> getStartFormVariables() {
        return startFormVariables;
    }
    public String getOutcome() {
        return outcome;
    }

    public Map<String, Object> getExtraFormVariables() {
        return extraFormVariables;
    }

    public FormInfo getExtraFormInfo() {
        return extraFormInfo;
    }

    public String getExtraFormOutcome() {
        return extraFormOutcome;
    }

    public boolean isFallbackToDefaultTenant() {
        return fallbackToDefaultTenant;
    }

    public Map<String, Set<String>> getUserIdentityLinks() {
        return userIdentityLinks;
    }

    public Map<String, Set<String>> getGroupIdentityLinks() {
        return groupIdentityLinks;
    }

}
