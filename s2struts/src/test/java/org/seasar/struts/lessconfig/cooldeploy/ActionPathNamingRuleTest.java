package org.seasar.struts.lessconfig.cooldeploy;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.lessconfig.cooldeploy.NamingRule;
import org.seasar.struts.lessconfig.cooldeploy.web.add.AddAction;
import org.seasar.struts.lessconfig.cooldeploy.web.add.AddForm;

public class ActionPathNamingRuleTest extends S2TestCase {
    
    private NamingRule namingRule;

    public void setUp() {
        include("ActionPathNamingRuleTest.dicon");
    }
    
    public void testIsTarget() {
        assertTrue(namingRule.isTargetClass(AddAction.class));
    }
    
    public void testIsNotTarget() {
        assertFalse(namingRule.isTargetClass(AddForm.class));
    }
    
    public void testDefineName() {
        String name = namingRule.defineName(AddAction.class);
        assertEquals("/add_add", name);
    }
    
    public void testDefineClass() {
        Class clazz = namingRule.defineClass("/add_add");
        assertEquals(AddAction.class, clazz);
    }
    
    public void testNotFoundDefineClass() {
        Class clazz = namingRule.defineClass("/not_found");
        assertNull(clazz);
    }
    
}