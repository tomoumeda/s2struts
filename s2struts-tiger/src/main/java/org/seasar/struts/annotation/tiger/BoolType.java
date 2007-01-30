/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.struts.annotation.tiger;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public enum BoolType {

    TRUE, FALSE, UNDEFINED;

    public Boolean getBoolean() {
        if (this == UNDEFINED) {
            return null;
        }
        return (this == TRUE);
    }

    public String toString() {
        if (this == UNDEFINED) {
            return null;
        }
        return super.toString();
    }

}
