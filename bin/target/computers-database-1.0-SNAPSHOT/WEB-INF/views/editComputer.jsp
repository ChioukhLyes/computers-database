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
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div id="idComputer" class="label label-default pull-right">id :
						${Computer.id}</div>
					<h1>Edit Computer</h1>

					<form id="formAddEditComputer" action="editComputer" method="POST">
						<input type="hidden" name="id" value="${Computer.id}" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name </label> <input
									type="text" name="computerName" class="form-control"
									id="computerName" placeholder="Computer name"
									value="<c:out value='${Computer.name}'></c:out>">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" name="introduced" class="form-control"
									id="introduced" placeholder="Introduced date"
									value="<c:out value='${Computer.introduced}'></c:out>">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" name="discontinued" class="form-control"
									id="discontinued" placeholder="Discontinued date"
									value="<c:out value='${Computer.discontinued}'></c:out>">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" name="companyId" id="companyId">
									<option value="${Computer.companyId}"><c:out value='${Computer.companyName}'></c:out></option>
									<c:forEach var="company" items="${Companies}">
										<option value="${company.id}"><c:out value='${company.name}'></c:out></option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>