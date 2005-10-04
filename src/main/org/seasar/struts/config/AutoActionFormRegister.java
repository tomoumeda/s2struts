package org.seasar.struts.config;

import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.struts.config.rule.ZeroConfigActionFormRule;
import org.seasar.struts.factory.AnnotationHandler;
import org.seasar.struts.factory.AnnotationHandlerFactory;

/**
 * @author Katsuhiko Nagashima
 */
public class AutoActionFormRegister {

    private static final Logger log = Logger.getLogger(AutoActionFormRegister.class);

    private AutoActionFormRegister() {
    }

    public static void regist(ModuleConfig config, Collection classes) {
        AnnotationHandler annHandler = AnnotationHandlerFactory.getAnnotationHandler();
        classes = ClassComparator.sort(classes);
        
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            StrutsActionFormConfig strutsActionForm = annHandler.createStrutsActionFormConfig(clazz);
            if (strutsActionForm == null && isActionFormClass(clazz)) {
                strutsActionForm = new NullStrutsActionFormConfig();
            }
            if (strutsActionForm != null) {
                registActionForm(strutsActionForm, clazz, config);
            }
        }
    }
    
    private static boolean isActionFormClass(Class clazz) {
        return clazz.getName().matches(configRule().getFormClassPattern());
    }

    private static void registActionForm(StrutsActionFormConfig form, Class formClass, ModuleConfig config) {
        String name = getName(form, formClass, config);
        if (config.findFormBeanConfig(name) == null) {
            regist(config, form, formClass);
        }
    }

    private static void regist(ModuleConfig config, StrutsActionFormConfig form, Class formClass) {
        FormBeanConfig formBeanConfig = new FormBeanConfig();
        formBeanConfig.setName(getName(form, formClass, config));
        formBeanConfig.setType(formClass.getName());
        formBeanConfig.setRestricted(getRestricted(form, formClass, config));
        config.addFormBeanConfig(formBeanConfig);
        
        log.debug("auto regist " + formBeanConfig);
    }

    private static ZeroConfigActionFormRule rule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (ZeroConfigActionFormRule) container.getComponent(ZeroConfigActionFormRule.class);
    }

    private static AutoStrutsConfigRule configRule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (AutoStrutsConfigRule) container.getComponent(AutoStrutsConfigRule.class);
    }

    private static String getName(StrutsActionFormConfig form, Class formClass, ModuleConfig config) {
        return StrutsActionFormConfig.DEFAULT_NAME.equals(form.name()) ? rule().getName(formClass, config) : form.name();
    }

    private static boolean getRestricted(StrutsActionFormConfig form, Class formClass, ModuleConfig config) {
        return form.restricted() == StrutsActionFormConfig.DEFAULT_RESTRICTED ? rule().getRestricted(formClass, config)
                : form.restricted();
    }

}
