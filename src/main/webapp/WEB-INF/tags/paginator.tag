<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="page" required="true" type="com.excilys.model.Page"
	description="CurrentPage"%>

<div class="container text-center">
	<ul class="pagination">

		<c:if test="${currentPage.pageNumber != 1}">
			<li><a
				href="
			<c:url value="dashboard">
			<c:param name="page" value="1" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
		</c:url>"
				aria-label="First"> <span class="glyphicon glyphicon-step-backward" aria-hidden=true></span>
			</a></li>
			
			<li><a
				href="
			<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber-1}" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
		</c:url>"
				aria-label="Previous"> <span class="" aria-hidden=true>Previous</span>
			</a></li>
			
		</c:if>

		<c:if test="${currentPage.pageNumber-2 > 0}">
			<li><a	href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber-2}" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
			</c:url>">${currentPage.pageNumber-2}</a></li>
		</c:if>

		<c:if test="${currentPage.pageNumber-1 > 0}">
			<li><a 
				href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber-1}" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
			</c:url>">${currentPage.pageNumber-1}</a></li>
		</c:if>

		<li class="active"><a href="#">${currentPage.pageNumber}</a></li>
		
		<c:if test="${currentPage.pageNumber+1 <= currentPage.maxPage}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber+1}" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
			</c:url>">${currentPage.pageNumber+1}</a></li>
		</c:if>

		<c:if test="${currentPage.pageNumber+2 <= currentPage.maxPage}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber+2}" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
			</c:url>">${currentPage.pageNumber+2}</a></li>
		</c:if>

		<c:if test="${currentPage.pageNumber != currentPage.maxPage}">
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.pageNumber+1}" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
			</c:url>"
			
			aria-label="Next"> <span class="" aria-hidden="true">Next</span>
<!-- 				aria-label="Next"> <span aria-hidden="true">&raquo;</span> -->
			</a></li>
			
			<li><a href="<c:url value="dashboard">
			<c:param name="page" value="${currentPage.maxPage}" />
			<c:param name="size" value="${currentPage.pageSize}" />
			<c:param name="search" value="${currentPage.searchString}" />
			<c:param name="orderby" value="${currentPage.orderEntitiesBy}" />
			<c:param name="optionorder" value="${currentPage.optionOrder}" />
			</c:url>"
				aria-label="Last"> <span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span>
			</a></li>
			
		</c:if>

	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?page=1&size=10&search=${currentPage.searchString}&orderby=${currentPage.orderEntitiesBy}&optionorder=${currentPage.optionOrder}'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?page=1&size=50&search=${currentPage.searchString}&orderby=${currentPage.orderEntitiesBy}&optionorder=${currentPage.optionOrder}'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?page=1&size=100&search=${currentPage.searchString}&orderby=${currentPage.orderEntitiesBy}&optionorder=${currentPage.optionOrder}'">100</button>
	</div>
</div>