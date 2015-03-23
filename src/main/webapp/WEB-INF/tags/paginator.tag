<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"
	description="CurrentPage"%>
<%@ attribute name="size" required="true" type="java.lang.String"
	description="itemPerPage"%>

<div class="container text-center">
	<ul class="pagination">

		<c:if test="${page != 1}">
			<li><a
				href="
			<c:url value="dashboard">
			<c:param name="page" value="${page-1}" />
			<c:param name="size" value="${size}" />
		</c:url>"
				aria-label="Previous"> <span aria-hidden=true>&laquo;</span>
			</a></li>
		</c:if>

		<c:if test="${page-2 > 0}">
			<li><a	href="<c:url value="dashboard">
			<c:param name="page" value="${page-2}" />
			<c:param name="size" value="${size}" /></c:url>
				">${page-2}</a></li>
		</c:if>

		<c:if test="${page-1 > 0}">
			<li><a 
				href="<c:url value="dashboard">
			<c:param name="page" value="${page-1}" />
			<c:param name="size" value="${size}" /></c:url>">${page-1}</a></li>
		</c:if>

		<li class="active"><a href="#">${page}</a></li>
		
		<c:if test="${page+1 <= pageMax}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${page+1}" />
			<c:param name="size" value="${size}" /></c:url>">${page+1}</a></li>
		</c:if>

		<c:if test="${page+2 <= pageMax}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${page+2}" />
			<c:param name="size" value="${size}" /></c:url>">${page+2}</a></li>
		</c:if>

		<c:if test="${page != pageMax}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${page+1}" />
			<c:param name="size" value="${size}" /></c:url>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>

	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?size=10'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?size=50'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?size=100'">100</button>
	</div>
</div>