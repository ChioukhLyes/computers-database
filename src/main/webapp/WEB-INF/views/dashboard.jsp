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
			<h1 id="homeTitle">
				<c:out value="${numberComputers}"></c:out>
				Computers found
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							href="<c:url value="dashboard">
								<c:param name="page" value="${currentPage.pageNumber}" />
								<c:param name="size" value="${currentPage.pageSize}" />
								<c:param name="search" value="${currentPage.searchString}" />
								<c:param name="orderby" value="name" />
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<c:param name="orderoption" value="ASC" />
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<c:param name="orderoption" value="DESC" />
									</c:when>
									<c:otherwise>
										<c:param name="orderoption" value="ASC" />
									</c:otherwise>
								</c:choose>
								</c:url>"
								aria-label="Last"> 
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<span class="glyphicon glyphicon-sort-by-alphabet-alt"
											aria-hidden="true"></span>
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<span class="glyphicon glyphicon-sort-by-alphabet"
											aria-hidden="true"></span>
									</c:when>
									<c:otherwise>
										<span
											class="glyphicon glyphicon glyphicon-sort-by-alphabet-alt"
											aria-hidden="true"></span>
									</c:otherwise>
								</c:choose>

						</a> Computer name</th>
						<th><a
							href="<c:url value="dashboard">
								<c:param name="page" value="${currentPage.pageNumber}" />
								<c:param name="size" value="${currentPage.pageSize}" />
								<c:param name="search" value="${currentPage.searchString}" />
								<c:param name="orderby" value="introduced" />
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<c:param name="orderoption" value="ASC" />
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<c:param name="orderoption" value="DESC" />
									</c:when>
									<c:otherwise>
										<c:param name="orderoption" value="ASC" />
									</c:otherwise>
								</c:choose>
								</c:url>"
								aria-label="Last"> 
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<span class="glyphicon glyphicon-sort-by-order-alt"
											aria-hidden="true"></span>
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<span class="glyphicon glyphicon-sort-by-order"
											aria-hidden="true"></span>
									</c:when>
									<c:otherwise>
										<span
											class="glyphicon glyphicon glyphicon-sort-by-order-alt"
											aria-hidden="true"></span>
									</c:otherwise>
								</c:choose>
						</a> Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th><a
							href="<c:url value="dashboard">
								<c:param name="page" value="${currentPage.pageNumber}" />
								<c:param name="size" value="${currentPage.pageSize}" />
								<c:param name="search" value="${currentPage.searchString}" />
								<c:param name="orderby" value="discontinued" />
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<c:param name="orderoption" value="ASC" />
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<c:param name="orderoption" value="DESC" />
									</c:when>
									<c:otherwise>
										<c:param name="orderoption" value="ASC" />
									</c:otherwise>
								</c:choose>
								</c:url>"
								aria-label="Last"> 
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<span class="glyphicon glyphicon-sort-by-order-alt"
											aria-hidden="true"></span>
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<span class="glyphicon glyphicon-sort-by-order"
											aria-hidden="true"></span>
									</c:when>
									<c:otherwise>
										<span
											class="glyphicon glyphicon glyphicon-sort-by-order-alt"
											aria-hidden="true"></span>
									</c:otherwise>
								</c:choose>
						</a> Discontinued date</th>
						<!-- Table header for Company -->
						<th><a
							href="<c:url value="dashboard">
								<c:param name="page" value="${currentPage.pageNumber}" />
								<c:param name="size" value="${currentPage.pageSize}" />
								<c:param name="search" value="${currentPage.searchString}" />
								<c:param name="orderby" value="companyname" />
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<c:param name="orderoption" value="ASC" />
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<c:param name="orderoption" value="DESC" />
									</c:when>
									<c:otherwise>
										<c:param name="orderoption" value="ASC" />
									</c:otherwise>
								</c:choose>
								</c:url>"
								aria-label="Last"> 
								<c:choose>
									<c:when test="${currentPage.optionOrder == 'DESC' }">
										<span class="glyphicon glyphicon-sort-by-alphabet-alt"
											aria-hidden="true"></span>
									</c:when>
									<c:when test="${currentPage.optionOrder == 'ASC' }">
										<span class="glyphicon glyphicon-sort-by-alphabet"
											aria-hidden="true"></span>
									</c:when>
									<c:otherwise>
										<span
											class="glyphicon glyphicon glyphicon-sort-by-alphabet-alt"
											aria-hidden="true"></span>
									</c:otherwise>
								</c:choose>
						</a> Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="item" items="${currentPage.entities}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${item.id}"></td>
							<td><a
								href="<c:url value="editComputer">                            
							       				<c:param name="id" value="${item.id}"/>                            
							       				</c:url>"
								onclick=""> ${item.name}</a></td>
							<td>${item.introduced}</td>
							<td>${item.discontinued}</td>
							<td>${item.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<mylib:paginator page="${currentPage}" />
	</footer>

	<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>