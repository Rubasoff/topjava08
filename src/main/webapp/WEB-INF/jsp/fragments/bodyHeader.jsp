<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<header><a href=""><fmt:message key="app.home"/></a>&nbsp;&nbsp;<a href="meals"><fmt:message key="app.title"/></a></header>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="meals" class="navbar-brand"><fmt:message key="app.title"/></a>

        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <a class="btn btn-info" role="button" href="users"><fmt:message key="users.title"/></a>
                <a class="btn btn-primary" role="button" href="logout"><fmt:message key="app.logout"/></a>
            </form>
        </div>
    </div>
</div>