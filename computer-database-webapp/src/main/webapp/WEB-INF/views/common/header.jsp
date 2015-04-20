<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">

			<!-- If user is anonymous -->
			<security:authorize access="!isAuthenticated()">
				<a id="logout-button" href="<c:url value="/login" />"
					class="btn btn-success pull-right"> <span
					class="glyphicon glyphicon-log-in"></span> <spring:message
						code="loginicon" />
				</a>
			</security:authorize>
			<!-- If user is authenticated -->
			<security:authorize access="isAuthenticated()">
				<button class="btn btn-danger pull-right" id="logout-button"
					data-toggle="modal" data-target=".bs-example-modal-sm">
					<span class="glyphicon glyphicon-log-out"></span>
					<spring:message code="logouticon" />
				</button>
			</security:authorize>
			<div class="modal bs-example-modal-sm" tabindex="-1" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h4>
								<spring:message code="logout" />
								<i class="fa fa-unlock-alt"></i>
							</h4>
						</div>
						<div class="modal-body">
							<i class="fa fa-question-circle"></i>
							<spring:message code="confirmation" />
						</div>
						<div class="modal-footer">
							<a href="<c:url value="j_spring_security_logout" />"
								class="btn btn-danger btn-block"><spring:message
									code="logout" /></a>
						</div>
					</div>
				</div>
			</div>

			<!-- Switch language button  -->
			<img class="notificationicon on"
				title="<spring:message	code="changeLanguage" />" alt=""
				src="<spring:message	code="image.src" />" id="languagesChange" />

			<ul class="notifications" id="languagesMenu">
				<li class="titlebar"><span class="title"><spring:message
							code="changeLanguage" /></span></li>

				<li class="notifbox">
					<ul class="notif">
						<li><a onclick="updateQueryStringParameter('language','fr')">
								<ul class="imageblock">
									<li><img src="images/France.png" class="notifimage" /></li>
								</ul>
								<ul class="messageblock">
									<li>
										<div class="message">
											<strong>Français</strong>
										</div>
									</li>
								</ul>
						</a></li>
					</ul>
					<ul class="notif">
						<li><a onclick="updateQueryStringParameter('language','en')">
								<ul class="imageblock">
									<li><img src="images/English.png" class="notifimage" /></li>
								</ul>
								<ul class="messageblock">
									<li>
										<div class="message">
											<strong>English</strong>
										</div>
									</li>
								</ul>
						</a></li>
					</ul>
				</li>
			</ul>

			<!-- End Switch language operation  -->
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="title" /></a>
		</div>
	</header>
</body>