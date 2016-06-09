<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <%@include file="../include/style.jsp" %>
</head>
<body>
<%@include file="../include/template.jsp" %>
<div class="container">
    <fieldset>
        <legend>Applicant Result</legend>
        <c:choose>
        <c:when test="${applicantsResult.size() == 0}">
            <p><c:out value="No applicant results yet"></c:out></p>
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <th>ID</th>
                <th>Applicant ID</th>
                <th>Subject Name</th>
                <th>Mark</th>
            </tr>
            <c:forEach items="${applicantsResult}" var="applicantsResult">
                <tr>
                    <td>
                        <c:out value="${applicantsResult.getId()}"/>
                    </td>
                    <td>
                        <c:out value="${applicantsResult.getApplicantId()}"/>
                    </td>
                    <td>
                        <c:out value="${applicantsResult.getSubjectName()}"/>
                    </td>
                    <td>
                        <c:out value="${applicantsResult.getMark()}"/>
                    </td>
                    <td>
                        <a title="Edit ${applicantsResult.getApplicantId()} ${applicantsResult.getSubjectId()} ${applicantsResult.getMark()}"
                           href="controller?command=editApplicantResult&id=${applicantsResult.getId()}">
                            <i class="fa fa-pencil"></i></a>
                        <a title="Delete ${applicantsResult.getApplicantId()} ${applicantsResult.getSubjectId()} ${applicantsResult.getMark()}"
                           href="controller?command=deleteApplicantResult&id=${applicantsResult.getId()}">
                            <i class="fa fa-trash-o"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
    </c:otherwise>
    </c:choose>
    <div class="add_button">
        <i class="fa fa-plus-circle"></i>
        <a href="controller?command=addApplicantResult">Add new Applicant Result</a>
    </div>
</div>
</body>
</html>