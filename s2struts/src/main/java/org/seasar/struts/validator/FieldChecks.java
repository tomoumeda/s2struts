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
package org.seasar.struts.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.validator.exception.ValidatorException;

/**
 * 標準検証ルールで使用される{@link FieldChecks}をS2Struts用に拡張したクラスです。
 * 
 * @author Satoshi Kimura
 */
public class FieldChecks extends org.apache.struts.validator.FieldChecks {
    private static final long serialVersionUID = -8929484039023790851L;

    /**
     * 独自に作成したバリデータを実行します。
     * <p>
     * このメソッドを呼び出すにはあらかじめ次の設定が必要です。
     * <ul>
     * <li>実際の検証処理は{@link org.seasar.struts.validator.Validator}の実装クラスに記述する</li>
     * <li>作成した<code>Validator</code>の実装クラスはコンポーネントとしてS2コンテナに登録する</li>
     * <li>バリデータの設定で{@link Field}に<code>componentKey</code>という文字列をキー、<code>Validator</code>コンポーネントの名前もしくはクラス名を値として登録する</li>
     * </ul>
     * </p>
     * 
     * @param bean
     * @param validatorAction
     * @param field
     * @param errors
     * @param validator
     * @param request
     * @return
     */
    public static boolean validateCustom(Object bean, ValidatorAction validatorAction, Field field,
            ActionMessages errors, Validator validator, HttpServletRequest request) {
        if (bean == null) {
            return true;
        }
        try {
            validate(bean, field);
        } catch (ValidatorException e) {
            errors.add(field.getKey(), new ActionMessage(e.getResourceKey(), e.getMessageArgs()));
        } catch (Exception e) {
            addError(errors, field, validator, validatorAction, request);
            return false;
        }

        return true;
    }

    private static void validate(Object bean, Field field) throws ValidatorException {
        org.seasar.struts.validator.Validator validator = getValidator(bean, field);
        validator.validate(bean);
    }

    private static org.seasar.struts.validator.Validator getValidator(Object bean, Field field) {
        String componentKey = field.getVarValue("componentKey");
        S2Container container = SingletonS2ContainerFactory.getContainer();
        try {
            return (org.seasar.struts.validator.Validator) container.getComponent(componentKey);
        } catch (ComponentNotFoundRuntimeException e) {
            return (org.seasar.struts.validator.Validator) container.getComponent(ClassUtil.forName(componentKey));
        }
    }

    /**
     * Checks if the field's length of byte is less than or equal to the maximum
     * value. A <code>Null</code> will be considered an error.
     * 
     * @param bean
     *            The bean validation is being performed on.
     * @param validatorAction
     *            The <code>ValidatorAction</code> that is currently being
     *            performed.
     * @param field
     *            The <code>Field</code> object associated with the current
     *            field being validated.
     * @param errors
     *            The <code>ActionMessages</code> object to add errors to if
     *            any validation errors occur.
     * @param validator
     *            The <code>Validator</code> instance, used to access other
     *            field values.
     * @param request
     *            Current request object.
     * @return True if stated conditions met.
     */
    public static boolean validateMaxByteLength(Object bean, ValidatorAction validatorAction, Field field,
            ActionMessages errors, Validator validator, HttpServletRequest request) {

        String value = toString(bean, field);

        if (!GenericValidator.isBlankOrNull(value)) {
            try {
                int max = parseInt(field.getVarValue("maxbytelength"));
                String charset = field.getVarValue("charset");

                if (!GenericValidator.maxByteLength(value, max, charset)) {
                    addError(errors, field, validator, validatorAction, request);
                    return false;
                }
            } catch (Exception e) {
                addError(errors, field, validator, validatorAction, request);
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the field's length of byte is greater than or equal to the
     * minimum value. A <code>Null</code> will be considered an error.
     * 
     * @param bean
     *            The bean validation is being performed on.
     * @param validatorAction
     *            The <code>ValidatorAction</code> that is currently being
     *            performed.
     * @param field
     *            The <code>Field</code> object associated with the current
     *            field being validated.
     * @param errors
     *            The <code>ActionMessages</code> object to add errors to if
     *            any validation errors occur.
     * @param validator
     *            The <code>Validator</code> instance, used to access other
     *            field values.
     * @param request
     *            Current request object.
     * @return True if stated conditions met.
     */
    public static boolean validateMinByteLength(Object bean, ValidatorAction validatorAction, Field field,
            ActionMessages errors, Validator validator, HttpServletRequest request) {

        String value = toString(bean, field);

        if (!GenericValidator.isBlankOrNull(value)) {
            try {
                int min = parseInt(field.getVarValue("minbytelength"));
                String charset = field.getVarValue("charset");

                if (!GenericValidator.minByteLength(value, min, charset)) {
                    addError(errors, field, validator, validatorAction, request);
                    return false;
                }
            } catch (Exception e) {
                addError(errors, field, validator, validatorAction, request);
                return false;
            }
        }

        return true;
    }

    private static String toString(Object bean, Field field) {
        String value = null;
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }
        return value;
    }

    private static int parseInt(String value) {
        return java.lang.Integer.parseInt(value);
    }

    private static void addError(ActionMessages errors, Field field, Validator validator,
            ValidatorAction validatorAction, HttpServletRequest request) {
        errors.add(field.getKey(), Resources.getActionMessage(validator, request, validatorAction, field));
    }
}
