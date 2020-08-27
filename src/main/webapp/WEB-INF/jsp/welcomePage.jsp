<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <body>
        <div>
            <div>
                <h1>Welcome to the Search page</h1>
                <h2>Please, enter your search criteria</h2>
                <form action="${pageContext.request.contextPath}/search">
                    <input type="text" class="form-control" id="term" name="term" required>
                    <button type="submit" class="btn btn-primary" >Search criteria</button>
                </form>
            </div>
        </div>
    </body>
</html>