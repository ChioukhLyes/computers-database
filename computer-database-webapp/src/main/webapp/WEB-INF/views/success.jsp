<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="common/header.jsp"></jsp:include>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard">  <spring:message code="title"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-success">
				<h1>
					<strong> <spring:message code="title"/></strong>
				</h1>
				<br />
				<!-- stacktrace -->
			</div>
			
			<div class="alert alert-success">
				<h2>
					 <spring:message code="success"/>
				</h2>
				<br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>