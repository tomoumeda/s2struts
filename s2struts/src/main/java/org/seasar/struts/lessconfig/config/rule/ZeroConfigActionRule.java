/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.lessconfig.config.rule;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.struts.lessconfig.config.StrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public interface ZeroConfigActionRule {
    
    ActionConfig createActionConfig(ModuleConfig config, Class actionClass, String path, StrutsActionConfig strutsAction);

    ForwardConfig createActionForwardConfig(ModuleConfig config, Class actionClass, String name,
            StrutsActionForwardConfig actionForward);

    void addForwardConfig(ModuleConfig config, Class actionClass, ActionConfig actionConfig);

}
