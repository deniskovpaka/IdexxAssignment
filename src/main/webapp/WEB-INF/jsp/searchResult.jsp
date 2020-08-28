<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Search result</title>
    <link href="${pageContext.request.contextPath}/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap.min.js"></script>
</head>
<body>
<div>
    <div class="container" style="margin:50px">
        <div class="row text-center"><strong>Search details</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-4 text-center"><strong>Title</strong></div>
            <div class="col-md-4 text-center"><strong>Authors</strong></div>
            <div class="col-md-4 text-center"><strong>Book/Album</strong></div>
        </div>
        <c:forEach var="result" items="${searchResult}">
            <div class="row" style="border:1px solid green;padding:10px">
                <div class="col-md-4 text-center">${result.title}</div>
                <div class="col-md-4 text-center" >${result.creator}</div>
                <div class="col-md-4 text-center">${result.product}</div>
            </div>
        </c:forEach>
        <a href="/">Back</a>
    </div>
</div>
</body>
</html>
