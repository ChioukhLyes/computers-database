<%@ tag language="java" pageEncoding="UTF-8" description="Links"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="true"%>
<%@ attribute name="page" required="true" %>
<%@ attribute name="size"%>
<%@ attribute name="search"%>
<%@ attribute name="orderby"%>
<%@ attribute name="orderoption"%>

<c:url	value="${target}?page=${page}&size=${size}&search=${search}&orderby=${orderby}&orderoption=${orderoption}" />