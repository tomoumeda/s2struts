<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html:html>
<head>
<title><bean:message key="system.error"/></title>
</head>
<body>
<html:errors/>
<br>
<html:link forward="welcome"><bean:message key="system.back"/></html:link><br>
<br>
</body>
</html:html>