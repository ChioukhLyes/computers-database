<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<jsp:include page="common/header.jsp"></jsp:include>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 500: An error has occured! WTF! <br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>