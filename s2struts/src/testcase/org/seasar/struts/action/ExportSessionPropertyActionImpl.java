package org.seasar.struts.action;

import org.seasar.struts.Constants;

/**
 * @author Katsuhiko Nagashima
 */
public class ExportSessionPropertyActionImpl implements
        ExportSessionPropertyAction {

    private String bar;

    private String baz;

    private String foo;
    
    private String qux;
    
    public static final String bar_EXPORT = Constants.SESSION;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public static final String baz_EXPORT = Constants.SESSION;
    
    public String getBaz() {
        return baz;
    }

    public void setBaz(String baz) {
        this.baz = baz;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public static final String qux_EXPORT = Constants.SESSION;
    
    public String getQux() {
        return qux;
    }

    public void setQux(String qux) {
        this.qux = qux;
    }

    public String exe() {
        return "success";
    }

}