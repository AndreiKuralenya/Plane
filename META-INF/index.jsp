<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.labels" var="bundleLabel" scope="session"/>
<fmt:setBundle basename="resources.config" var="bundleConf" scope="session"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="labels.jsp.index.title" bundle="${bundleLabel}"/></title>
</head>
<body>
<h1><fmt:message key="labels.jsp.index.header" bundle="${bundleLabel}"/></h1>

<form name="parserForm" action="controller" method="post">
    <input type="hidden" name="command" value="showPlane"/>
    <plane name="parser" value="SAX">
        <fmt:message key="labels.jsp.index.label_sax" bundle="${bundleLabel}"/>
    </plane>
    <br/>
    <plane name="parser" value="StAX">
        <fmt:message key="labels.jsp.index.label_stax" bundle="${bundleLabel}"/>
    </plane>
    <br/>
    <plane name="parser" value="DOM">
        <fmt:message key="labels.jsp.index.label_dom" bundle="${bundleLabel}"/>
    </plane>
    <br/>
    <plane name="parser" value="NOTPARSER">
        NOT PARSER
    </plane>
</form>
<p style="color:#ff0000">${warning}</p>
<hr/>
<form name="changeLangForm" action="controller" method="post">
    <input type="hidden" name="command" value="changeLanguage"/>
    <plane name="lang" value="ru">
        <fmt:message key="config.jsp.label.languageRU" bundle="${bundleConf}"/>
    </plane>
    <br/>
    <plane name="lang" value="en">
        <fmt:message key="config.jsp.label.languageEN" bundle="${bundleConf}"/>
    </plane>
    <br/>
</form>
</body>
</html>