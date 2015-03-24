<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="page" required="true" type="model.Page"
	description="CurrentPage"%>

<div class="container text-center">
	<ul class="pagination">

		<c:if test="${currentPage.pageNumber != 1}">
			<li><a
				href="
			<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber-1}" />
			<c:param name="size" value="${currentPage.pageSize}" />
		</c:url>"
				aria-label="Previous"> <span aria-hidden=true>&laquo;</span>
			</a></li>
		</c:if>

		<c:if test="${currentPage.pageNumber-2 > 0}">
			<li><a	href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber-2}" />
			<c:param name="size" value="${currentPage.pageSize}" /></c:url>
				">${currentPage.pageNumber-2}</a></li>
		</c:if>

		<c:if test="${currentPage.pageNumber-1 > 0}">
			<li><a 
				href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber-1}" />
			<c:param name="size" value="${currentPage.pageSize}" /></c:url>">${currentPage.pageNumber-1}</a></li>
		</c:if>

		<li class="active"><a href="#">${currentPage.pageNumber}</a></li>
		
		<c:if test="${currentPage.pageNumber+1 <= currentPage.maxPage}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber+1}" />
			<c:param name="size" value="${currentPage.pageSize}" /></c:url>">${currentPage.pageNumber+1}</a></li>
		</c:if>

		<c:if test="${currentPage.pageNumber+2 <= currentPage.maxPage}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber+2}" />
			<c:param name="size" value="${currentPage.pageSize}" /></c:url>">${currentPage.pageNumber+2}</a></li>
		</c:if>

		<c:if test="${currentPage.pageNumber != currentPage.maxPage}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber+1}" />
			<c:param name="size" value="${currentPage.pageSize}" /></c:url>"
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