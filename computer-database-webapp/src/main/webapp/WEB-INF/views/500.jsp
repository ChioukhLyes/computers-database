<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="common/header.jsp"></jsp:include>

<body>
	

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<spring:message code="error500"/> <br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>