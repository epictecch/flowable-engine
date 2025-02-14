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

package org.flowable.engine.impl.bpmn.listener;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.common.engine.impl.scripting.AbstractScriptEvaluator;
import org.flowable.common.engine.impl.scripting.ScriptingEngines;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.impl.util.CommandContextUtil;

public class ScriptTypeExecutionListener extends AbstractScriptEvaluator implements ExecutionListener {

    private static final long serialVersionUID = 1L;

    public ScriptTypeExecutionListener() {
    }

    public ScriptTypeExecutionListener(Expression language, Expression script) {
        super(language, script);
    }

    @Override
    protected ScriptingEngines getScriptingEngines() {
        return CommandContextUtil.getProcessEngineConfiguration().getScriptingEngines();
    }

    @Override
    public void notify(DelegateExecution execution) {
        validateParametersAndEvaluteScript(execution);
    }

}
