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

        <form method="post" action="controller?command=saveApplicantResult">
            <c:choose>
                <c:when test="${applicantResult ne null}">
                    <span>Applicant ID</span>
                    <select name="applicants">
                        <c:forEach items="${applicants}" var="applicant">
                            <option value="${applicant.getId()}">${applicant.getId()}</option>
                        </c:forEach>
                    </select>
                    <span style= "margin-left: 50px" >Subject ID</span>
                    <select name="subjects">
                        <c:forEach items="${subjects}" var="subject">
                            <option value="${subject.getId()}">${subject.getSubjectName()}</option>
                        </c:forEach>
                    </select>
                    <span style= "margin-left: 50px">Mark</span>
                    <input type="text" name="mark"
                           value="${applicantResult.getMark()}"/><br/>
                    <input type="hidden" name="applicant_result_id" value="${applicantResult.getId()}"/><br/>
                </c:when>
                <c:otherwise>
                    <span>Applicant ID</span>
                    <select name="applicants">
                        <c:forEach items="${applicants}" var="applicant">
                            <option value="${applicant.getId()}">${applicant.getId()}</option>
                        </c:forEach>
                    </select>
                    <span style= "margin-left: 50px">Subject Name </span>
                    <select name="subjects">
                        <c:forEach items="${subjects}" var="subject">
                            <option value="${subject.getId()}">${subject.getSubjectName()}</option>
                        </c:forEach>
                    </select>
                    <span style= "margin-left: 50px">Mark</span>
                    <input type="text" name="mark"/>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
</div>
</body>
</html>