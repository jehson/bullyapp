
<%@ page import="com.bullyapp.Bully" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bully.label', default: 'Bully')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-bully" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-bully" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'bully.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'bully.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="color" title="${message(code: 'bully.color.label', default: 'Color')}" />
					
						<g:sortableColumn property="driversLicenseContentType" title="${message(code: 'bully.driversLicenseContentType.label', default: 'Drivers License Content Type')}" />
					
						<g:sortableColumn property="driversLicenseFile" title="${message(code: 'bully.driversLicenseFile.label', default: 'Drivers License File')}" />
					
						<g:sortableColumn property="driversLicenseFileName" title="${message(code: 'bully.driversLicenseFileName.label', default: 'Drivers License File Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${bullyInstanceList}" status="i" var="bullyInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${bullyInstance.id}">${fieldValue(bean: bullyInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: bullyInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: bullyInstance, field: "color")}</td>
					
						<td>${fieldValue(bean: bullyInstance, field: "driversLicenseContentType")}</td>
						
						<td>${fieldValue(bean: bullyInstance, field: "driversLicenseFile")}</td>
					
						<%--<td><image src="${createLink(controller:'bully', action:'getDriversLicenseImage', id:bullyInstance.ident()) }" width="120 px" /></td>
					
						--%><td>${fieldValue(bean: bullyInstance, field: "driversLicenseFileName")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bullyInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
