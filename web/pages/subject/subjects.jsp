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
        <legend>Subjects</legend>
        <c:choose>
        <c:when test="${subjects.size() == 0}">
            <p><c:out value="No subjects yet"></c:out></p>
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <th>ID</th>
                <th>Subject Name</th>
            </tr>
            <c:forEach items="${subjects}" var="subject">
                <tr>
                    <td>
                        <c:out value="${subject.getId()}"/>
                    </td>
                    <td>
                        <c:out value="${subject.getSubjectName()}"/>
                    </td>
                    <td>
                        <a title="Edit ${subject.getSubjectName()} ${subject.getSubjectName()}"
                           href="controller?command=editSubject&id=${subject.getId()}">
                            <i class="fa fa-pencil"></i></a>
                        <a title="Delete ${subject.getSubjectName()} ${subject.getSubjectName()}"
                           href="controller?command=deleteSubject&id=${subject.getId()}">
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
        <a href="controller?command=addSubject">Add new subject</a>
    </div>
</div>
</body>
</html>
