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
package org.seasar.struts.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.processor.MethodBinding;

/**
 * @author Satoshi Kimura
 */
public class InitializeTag extends BaseTag {
    private String action;

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public int doStartTag() throws JspException {
        // In case of Tomcat4.1, HttpServletRequest is set again,
        // because of the different HttpServletRequest for JSP and Servlet.
        SingletonS2ContainerFactory.getContainer().setRequest(
                (HttpServletRequest) this.pageContext.getRequest());
        
        MethodBinding methodBinding = new MethodBinding(this.action);
        methodBinding.invoke();
        return SKIP_BODY;
    }

}