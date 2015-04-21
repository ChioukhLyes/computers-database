<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="WEB-INF/views/common/header.jsp"></jsp:include>

<body>
   
    <section id="main">
        <div class="container">
            <div class="alert alert-success">
                <h1><spring:message code="hellomessage"/> <strong><spring:message code="computer.database"/></strong></h1>
                <br/>
            </div>
        </div>
    </section>

    <jsp:include page="WEB-INF/views/common/footer.jsp"></jsp:include>

</body>
</html>