<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<jsp:include page="common/header.jsp"></jsp:include>

<body>
	
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div id="idComputer" class="label label-default pull-right">id :
						${Computer.id}</div>
					<h1><spring:message code="home.editcomputer"/></h1>

					<form:form id="formAddEditComputer" action="editComputer" method="POST">
						<input type="hidden" name="id" value="${Computer.id}" />
						<fieldset>
							<div class="form-group">
								<label  for="computerName"><spring:message code="home.table.computername"/> </label> <input
									type="text" name="computerName" class="form-control"
									id="computerName" placeholder="<spring:message code="home.table.computername"/>"
									value="<c:out value='${Computer.name}'></c:out>">
									<form:errors path="computerName" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label  for="introduced"><spring:message code="home.table.introduceddate"/></label> <input
									type="date" name="introduced" class="form-control"
									id="introduced" placeholder="<spring:message code="home.table.introduceddate"/>"
									value="<c:out value='${Computer.introduced}'></c:out>">
									<form:errors path="introduced" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="home.table.discontinueddate"/></label> <input
									type="date" name="discontinued" class="form-control"
									id="discontinued" placeholder="<spring:message code="home.table.discontinueddate"/>"
									value="<c:out value='${Computer.discontinued}'></c:out>">
									<form:errors path="discontinued" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="home.table.company"/></label> <select
									class="form-control" name="companyId" id="companyId">
									<option value="${Company.id}"><c:out value='${Company.name}'></c:out></option>
									<c:forEach var="company" items="${Companies}">
										<option value="${company.id}"><c:out value='${company.name}'></c:out></option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="home.edit"/>" class="btn btn-primary">
							<spring:message code="or"/> <a href="dashboard" class="btn btn-default"><spring:message code="cancel"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>