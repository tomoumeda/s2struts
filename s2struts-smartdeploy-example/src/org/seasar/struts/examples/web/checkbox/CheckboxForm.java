/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.web.checkbox;

import java.io.Serializable;

import org.seasar.struts.annotation.tiger.StrutsActionForm;

/**
 * @author taedium
 * 
 */
@StrutsActionForm
public class CheckboxForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean arg1;

    private boolean arg2;

    public boolean isArg1() {
        return arg1;
    }

    public void setArg1(boolean arg1) {
        this.arg1 = arg1;
    }

    public boolean isArg2() {
        return arg2;
    }

    public void setArg2(boolean arg2) {
        this.arg2 = arg2;
    }

    public void reset() {
        arg1 = false;
    }
}
