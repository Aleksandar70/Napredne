<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <div class="center alert alert-success">
            ${message}
        </div>
    </c:when>
    <c:when test="${not empty error}">
        <div class="center alert alert-danger">
            ${error}
        </div>
    </c:when>
    <c:otherwise>
        <div class="center">
            <h1 class="custom-header"><spring:message code="reset.password"/></h1>
            <form:form method="POST" action="/reset-password" modelAttribute="resetPasswordForm">
                <div class="form-group">
                    <label for="newPassword"><spring:message code="new.password"/></label>
                    <form:input path="newPassword" id="newPassword" type="password" cssClass="form-control custom-input"/>
                    <div id="newPasswordError" class="alert alert-danger custom-input" role="alert" hidden>
                        <spring:message code="new.password.error"/>
                    </div>
                    <label for="confirmPassword"><spring:message code="confirm.password"/></label>
                    <form:input path="confirmPassword" id="confirmPassword" type="password" cssClass="form-control custom-input"/>
                    <div id="confirmPasswordError" class="alert alert-danger custom-input" role="alert" hidden>
                        <spring:message code="confirm.password.error"/>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="button"><spring:message code="reset.password"/></button>
                </div>
            </form:form>
        </div>
    </div>
    </c:otherwise>
</c:choose>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/main.js"/>" rel="stylesheet"></script>
</body>
</html>
