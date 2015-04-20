<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
						id="textinput" name='username' placeholder="placeholder"
						type="text">
				</div>

				<!-- Password input-->
				<div class="form-group input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-lock"></i></span> <input id="passwordinput"
						name='password' placeholder="placeholder" class="form-control"
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


<!-- <div class="container">
    <div class="row">
        <form role="form">
            <div class="col-lg-6">
                <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                <div class="form-group">
                    <label for="InputName">Enter Name</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="InputName" id="InputName" placeholder="Enter Name" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Enter Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="InputEmailFirst" name="InputEmail" placeholder="Enter Email" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Confirm Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="InputEmailSecond" name="InputEmail" placeholder="Confirm Email" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputMessage">Enter Message</label>
                    <div class="input-group">
                        <textarea name="InputMessage" id="InputMessage" class="form-control" rows="5" required></textarea>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
            </div>
        </form>
        
    </div>
</div> -->

<jsp:include page="common/footer.jsp"></jsp:include>

</html>


