package org.seasar.struts.lessconfig.factory;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TigerValidatorAnnotationHandlerTest extends S2TestCase {

    private ValidatorAnnotationHandler annHandler;

    private Form form;

    public void setUp() {
        include("s2struts.dicon");

        annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
    }

    public void setUpAfterContainerInit() {
        form = annHandler.createForm("testForm", TestValidatorAnnotationForm.class);
    }

    public void testArg() {
        Field field = form.getField("arg");
        assertNotNull(field);
        assertEquals("Arg", field.getArg(0).getKey());
        assertNull(field.getArg(0).getBundle());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testArg2() {
        Field field = form.getField("arg2");
        assertNotNull(field);
        assertEquals("Arg2", field.getArg(0).getKey());
        assertEquals("myapp", field.getArg(0).getBundle());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testArg3() {
        Field field = form.getField("arg3");
        assertNotNull(field);
        assertEquals("Arg3.1", field.getArg(0).getKey());
        assertNull(field.getArg(0).getBundle());
        assertEquals(true, field.getArg(0).isResource());

        assertEquals("Arg3.2", field.getArg(1).getKey());
        assertEquals("myapp", field.getArg(1).getBundle());
        assertEquals(false, field.getArg(1).isResource());

        assertEquals("Arg3.1-other", field.getArg("other", 0).getKey());
        assertEquals("other", field.getArg("other", 0).getName());
        assertEquals("myapp", field.getArg("other", 0).getBundle());
        assertEquals(false, field.getArg("other", 0).isResource());
    }

    public void testArgs() {
        Field field = form.getField("args");
        assertNotNull(field);
        assertEquals("Arg0", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
        assertEquals("Arg1", field.getArg(1).getKey());
        assertEquals(false, field.getArg(1).isResource());
        assertEquals("Arg2", field.getArg(2).getKey());
        assertEquals(false, field.getArg(2).isResource());
    }

    public void testArgDefaultResource() {
        Field field = form.getField("argDefaultResource");
        assertNotNull(field);
        assertEquals("Arg", field.getArg(0).getKey());
        assertEquals(true, field.getArg(0).isResource());
    }

    public void testMessage() {
        Field field = form.getField("message");
        assertNotNull(field);
        assertNotNull(field.getMessage("required"));
        assertEquals("myrequired", field.getMsg("required"));
        assertEquals("myrequired", field.getMessage("required").getKey());
        assertNull(field.getMessage("required").getBundle());
        assertTrue(field.getMessage("required").isResource());
    }

    public void testMessage2() {
        Field field = form.getField("message2");
        assertNotNull(field);
        assertNotNull(field.getMessage("required"));
        assertEquals("my2required", field.getMsg("required"));
        assertEquals("my2required", field.getMessage("required").getKey());
        assertEquals("myapp", field.getMessage("required").getBundle());
        assertFalse(field.getMessage("required").isResource());
    }

    public void testMessages() {
        Field field = form.getField("messages");
        assertNotNull(field);
        assertNotNull(field.getMessage("required"));
        assertEquals("myrequired", field.getMsg("required"));
        assertEquals("myrequired", field.getMessage("required").getKey());
        assertEquals("myinteger", field.getMsg("integer"));
        assertEquals("myinteger", field.getMessage("integer").getKey());
    }

    public void testRequired() {
        Field field = form.getField("required");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
    }

    public void testInteger() {
        Field field = form.getField("integer");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
    }

    public void testDate() {
        Field field = form.getField("date");
        assertNotNull(field);
        assertEquals("date", field.getDepends());
        assertEquals("yyyyMMdd", field.getVarValue("datePattern"));
        assertNull(field.getVarValue("datePatternStrict"));
    }

    public void testStrictDate() {
        Field field = form.getField("strictDate");
        assertNotNull(field);
        assertEquals("date", field.getDepends());
        assertNull(field.getVarValue("datePattern"));
        assertEquals("yyyyMMdd", field.getVarValue("datePatternStrict"));
    }

    public void testAutoInteger() {
        Field field = form.getField("autoInteger");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
    }

    public void testAutoDate() {
        Field field = form.getField("autoDate");
        assertNotNull(field);
        assertEquals("date", field.getDepends());
        assertEquals("yyyy/MM/dd", field.getVarValue("datePattern"));
    }

    public void testNoValidate() {
        Field field = form.getField("noValidate");
        assertNull(field);
    }

    public void testNoValidateDate() {
        Field field = form.getField("noValidateDate");
        assertNull(field);
    }

    public void testCreditCard() {
        Field field = form.getField("creditCard");
        assertNotNull(field);
        assertEquals("creditCard", field.getDepends());
    }

    public void testLength() {
        Field field = form.getField("length");
        assertNotNull(field);
        assertEquals("minlength,maxlength = " + field.getDepends(), "minlength,maxlength".length(), field.getDepends()
                .length());
        assertEquals("3", field.getVarValue("minlength"));
        assertEquals("5", field.getVarValue("maxlength"));
    }

    public void testByteLength() {
        Field field = form.getField("byteLength");
        assertNotNull(field);
        assertEquals("minbytelength,maxbytelength = " + field.getDepends(), "minbytelength,maxbytelength".length(),
                field.getDepends().length());
        assertEquals("3", field.getVarValue("minbytelength"));
        assertEquals("5", field.getVarValue("maxbytelength"));
        assertEquals("ISO8859_1", field.getVarValue("charset"));
    }

    public void testDefaultByteLength() {
        Field field = form.getField("defaultByteLength");
        assertNotNull(field);
        assertEquals("minbytelength,maxbytelength = " + field.getDepends(), "minbytelength,maxbytelength".length(),
                field.getDepends().length());
        assertEquals("3", field.getVarValue("minbytelength"));
        assertEquals("5", field.getVarValue("maxbytelength"));
        assertNull(field.getVarValue("charset"));
    }

    public void testRange() {
        Field field = form.getField("range");
        assertNotNull(field);
        assertEquals("floatRange", field.getDepends());
        assertEquals("5.0", field.getVarValue("min"));
        assertEquals("10.1", field.getVarValue("max"));
    }

    public void testLongRange() {
        Field field = form.getField("longRange");
        assertNotNull(field);
        assertEquals("longRange", field.getDepends());
        assertEquals("5", field.getVarValue("min"));
        assertEquals("10", field.getVarValue("max"));
    }

    public void testMask() {
        Field field = form.getField("mask");
        assertNotNull(field);
        assertEquals("mask", field.getDepends());
        assertEquals("(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", field.getVarValue("mask"));
        assertEquals("comma", field.getMsg("mask"));
        assertTrue(field.getMessage("mask").isResource());
    }

    public void testMask2() {
        Field field = form.getField("mask2");
        assertNotNull(field);
        assertEquals("mask", field.getDepends());
        assertEquals("(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", field.getVarValue("mask"));
        assertNull(field.getMessage("mask"));
    }

    public void testMask3() {
        Field field = form.getField("mask3");
        assertNotNull(field);
        assertEquals("mask", field.getDepends());
        assertEquals("(^[0-9]{1,3}\\.{1}[0-9]{1,2}$)", field.getVarValue("mask"));
        assertEquals("comma", field.getMsg("mask"));
        assertFalse(field.getMessage("mask").isResource());
    }

    public void testMix() {
        Field field = form.getField("mix");
        assertNotNull(field);
        assertEquals("required,minlength,maxlength,mask,email = " + field.getDepends(),
                "required,minlength,maxlength,mask,email".length(), field.getDepends().length());
        assertEquals("10", field.getVarValue("minlength"));
        assertEquals("15", field.getVarValue("maxlength"));
        assertEquals("com$", field.getVarValue("mask"));

        assertEquals("mustendcom", field.getMsg("mask"));
    }

    public void testFullValidatorField() {
        Field field = form.getField("fullValidatorField");
        assertNotNull(field);
        assertEquals("intRange,integer,maxlength,required".length(), field.getDepends().length());

        assertEquals("3", field.getVarValue("maxlength"));
        assertEquals("10", field.getVarValue("min"));
        assertEquals("100", field.getVarValue("max"));

        assertEquals("form.message1", field.getArg(0).getKey());
        assertEquals(true, field.getArg(0).isResource());
        assertEquals("${var:maxlength}", field.getArg("maxlength", 1).getKey());
        assertEquals("${var:min}", field.getArg("intRange", 1).getKey());
        assertEquals("${var:max}", field.getArg("intRange", 2).getKey());
    }

    public void testSimpleValidatorField() {
        Field field = form.getField("simpleValidatorField");
        assertNotNull(field);
        assertEquals("intRange,integer,maxlength,required".length(), field.getDepends().length());

        assertEquals("3", field.getVarValue("maxlength"));
        assertEquals("10", field.getVarValue("min"));
        assertEquals("100", field.getVarValue("max"));

        assertEquals("form.message2", field.getArg(0).getKey());
        assertEquals(true, field.getArg(0).isResource());
        assertEquals("${var:maxlength}", field.getArg("maxlength", 1).getKey());
        assertEquals("${var:min}", field.getArg("intRange", 1).getKey());
        assertEquals("${var:max}", field.getArg("intRange", 2).getKey());
    }

    public void testArray() {
        Field field = form.getField("array[].");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("Array", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testAutoArray() {
        Field field = form.getField("autoArray[].");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
        assertEquals("AutoArray", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildRequired() {
        Field field = form.getField("child.required");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("ChildRequired", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildInteger() {
        Field field = form.getField("child.integer");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
        assertEquals("ChildInteger", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildNoValidate() {
        Field field = form.getField("child.noValidate");
        assertNull(field);
    }

    public void testChildrenRequired() {
        Field field = form.getField("children[].required");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("ChildRequired", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildrenInteger() {
        Field field = form.getField("children[].integer");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
        assertEquals("ChildInteger", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildrenNoValidate() {
        Field field = form.getField("children[].noValidate");
        assertNull(field);
    }

    public void testChildGrandchildRequired() {
        Field field = form.getField("child.grandchild.required");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("GrandchildRequired", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildGrandchildInteger() {
        Field field = form.getField("child.grandchild.integer");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
        assertEquals("GrandchildInteger", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildGrandchildNoValidate() {
        Field field = form.getField("child.grandchild.noValidate");
        assertNull(field);
    }

    public void testChildGrandchildrenRequired() {
        Field field = form.getField("child.grandchildren[].required");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("GrandchildRequired", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildGrandchildrenInteger() {
        Field field = form.getField("child.grandchildren[].integer");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
        assertEquals("GrandchildInteger", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildGrandchildrenNoValidate() {
        Field field = form.getField("child.grandchildren[].noValidate");
        assertNull(field);
    }

    public void testChildrenGrandchildRequired() {
        Field field = form.getField("children[].grandchild.required");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("GrandchildRequired", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildrenGrandchildInteger() {
        Field field = form.getField("children[].grandchild.integer");
        assertNotNull(field);
        assertEquals("integer", field.getDepends());
        assertEquals("GrandchildInteger", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

    public void testChildrenGrandchildNoValidate() {
        Field field = form.getField("children[].grandchild.noValidate");
        assertNull(field);
    }

    public void testRequiredFile() {
        Field field = form.getField("file");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("File", field.getArg(0).getKey());
    }

    public void testConstant() {
        Field field = form.getField("constant");
        assertNotNull(field);
        assertEquals("required", field.getDepends());
        assertEquals("Constant", field.getArg(0).getKey());
        assertEquals(false, field.getArg(0).isResource());
    }

}
