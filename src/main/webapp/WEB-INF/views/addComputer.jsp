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
					<h1>
						<spring:message code="home.addcomputer" />
					</h1>
					<form:form id="formAddEditComputer" action="addComputer"
						method="POST" novalidate="novalidate">
						<fieldset>
							<div class="form-group">
								<label for="computerName"> <spring:message
										code="home.table.computername" /></label> <input type="text"
									name="computerName" class="form-control" id="computerName"
									placeholder="<spring:message code="home.table.computername"/>">
								<form:errors path="computerName" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label for="introduced"> <spring:message
										code="home.table.introduceddate" /></label> <input type="date"
									name="introduced" class="form-control" id="introduced"
									placeholder="<spring:message code="home.table.introduceddate"/> : yyyy-MM-dd">
								<form:errors path="introduced" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label for="discontinued"> <spring:message
										code="home.table.discontinueddate" /></label> <input type="date"
									name="discontinued" class="form-control" id="discontinued"
									placeholder="<spring:message code="home.table.discontinueddate"/> : yyyy-MM-dd">
								<form:errors path="discontinued" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label for="companyId"> <spring:message
										code="home.table.company" /></label> <select class="form-control"
									name="companyId" id="companyId">
									<option value="0">
										<spring:message code="selectcompany" /> --
									</option>
									<c:forEach var="company" items="${Companies}">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">

							<input type="submit" value=" <spring:message code="add"/>"
								class="btn btn-primary">
							<spring:message code="or" />
							<a href="dashboard" class="btn btn-default"> <spring:message
									code="cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="common/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		var strings = new Array();
		strings['language'] = "<spring:message code='language' javaScriptEscape='true' />";
		strings['required'] = "<spring:message code='validator.required' javaScriptEscape='true' />"
		strings['date'] = "<spring:message code='validator.date' javaScriptEscape='true' />"
	</script>


</body>
</html>