<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><c:out value="${title}"/></title>
    <%@include file="../include/style.jsp" %>
</head>
<body>
<%@include file="../include/template.jsp" %>
<div class="container">
    <fieldset>
        <legend><c:out value="${title}"/></legend>
        <form method="post" action="controller?command=saveSpecSubject">
            <c:choose>
                <c:when test="${speciality_subject ne null}">
                    <span>Profession</span>
                    <select name="professions">
                        <c:forEach items="${professions}" var="profession">
                            <option value="${profession.getId()}">${profession.getProfessionName()}</option>
                        </c:forEach>
                    </select>
                    <span style= "margin-left: 50px">Subject</span>
                    <select name="subjects">
                        <c:forEach items="${subjects}" var="subject">
                            <option value="${subject.getId()}">${subject.getSubjectName()}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="sp_sb_id" value="${specialitySubject.getId()}"/><br/>
                </c:when>
                <c:otherwise>
                    <span>Profession ID</span>
                    <select name="professions">
                        <c:forEach items="${professions}" var="profession">
                            <option value="${profession.getId()}">${profession.getProfessionName()}</option>
                        </c:forEach>
                    </select>
                    <span style= "margin-left: 50px">Subject ID</span>
                    <select name="subjects">
                        <c:forEach items="${subjects}" var="subject">
                            <option value="${subject.getId()}">${subject.getSubjectName()}</option>
                        </c:forEach>
                    </select>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
</div>
</body>
</html>