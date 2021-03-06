<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<jsp:include page="common/header.jsp"></jsp:include>


<div class="container" onload='document.loginForm.username.focus();'>
	<div class="row">
		<form role="form" class="form-horizontal pull-left" name='loginForm'
			action="<c:url value='j_spring_security_check' />" method='POST'>

			<div class="col-lg-10 col-lg-offset-4">

				<div class="form-group">
					<h2>
						<spring:message code="form.login.title" />
					</h2>
					<c:if test="${not empty errorr}">
						<div class="errorr">${errorr}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>
				</div>

				<!-- Text input-->
				<div class="form-group input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span> <input class="form-control"
						id="textinput" name='username'
						placeholder="<spring:message code="username"/>" type="text">
				</div>

				<!-- Password input-->
				<div class="form-group input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-lock"></i></span> <input id="passwordinput"
						name='password' placeholder="******" class="form-control"
						type="password">
				</div>

				<!-- Button (Double) -->
				<div class="form-group">
					<div class="controls">
						<button type="submit" id="button1id" name="button1id"
							class="btn  btn-def btn-block btn-success">
							<spring:message code="submit.form" />
						</button>
						<button type="reset" id="button2id" name="button2id"
							class="btn  btn-def btn-block">
							<spring:message code="cancel.form" />
						</button>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</div>
		</form>
	</div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
</html>


