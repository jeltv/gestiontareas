<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html data-ng-app="myApp">
<head>
    <title><spring:message code="${titulo}"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
</head>
<body>


<body>
<div class="container">

    <!-- Angular JS Scripts -->
    <script src="${pageContext.request.contextPath}/resources/app/gestiontarea-app.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/maincontrollers/taskViewer-ctrl.js"></script>


    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
            <h2><spring:message code="${titulo}"/></h2>
        </div>
    </div>
    <%--<div class="row">--%>
    <%--<jsp:include page="bloques/tabs1.jsp"/>--%>
    <%--</div>--%>

    <div class="spacer_t2"></div>

    <form class="form-horizontal" role="form">

        <!-- Datos de la Instalacion  -  Start -->
        <div class="row">
            <app:inputText id="ninstalacion" label="visortarea.ninstalacion" value="${installationData.numeroInstalacion}" cells="6" cell_label="4" cell_input="8" readonly="true"/>
            <app:inputText id="titular" label="visortarea.titular" value="${installationData.titular}" cells="6" readonly="true"/>
        </div>

        <div class="spacer_t1"></div>
        <div class="row">
            <app:inputText id="personaContacto" label="visortarea.personacontacto" value="${installationData.personaContacto}" cells="6" readonly="true"/>
            <app:inputText id="panel" label="visortarea.panel" value="${installationData.panel}" cells="6" readonly="true"/>
        </div>

        <div class="spacer_t1"></div>

        <div class="row">
            <app:inputText id="telefono" label="visortarea.telefono" value="${installationData.telefono}" cells="6" readonly="true"/>
            <app:inputText id="version" label="visortarea.version" value="${installationData.version}" cells="6" readonly="true"/>
        </div>
        <!-- Datos de la Instalacion  - End -->

        <!-- INCLUDES DE TAREAS, dependiendo del tipo de tarea -->
        <div class="spacer_t3"></div>
        <div class="panel panel-default">
            <div class="panel-body">
                <%--TODO AQUI SE INCLUYE LA SECUNDARIA--%>
                <jsp:include page="${secundaria}"/>
            </div>
        </div>

        <!-- Botones -->
        <div class="spacer_t2"></div>
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row" align="right">
                    <div class="container-fluid">
                        <c:if test="${secundaria eq 'componentes/tarea_avisos.jsp'}">
                            <input type="button" class="btn btn-default[disabled]" value="<spring:message code="boton.Atras" />"/>
                            <input type="button" class="btn btn-default[disabled]" value="<spring:message code="boton.Modificar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.CrearMantenimiento" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Aplazar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Descartar" />"/>
                        </c:if>
                        <!-- Botones -->
                        <c:if test="${secundaria eq 'componentes/tarea_mantenimiento.jsp'}">
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.CrearMantenimiento" />"/>
                        </c:if>
                        <c:if test="${secundaria eq 'componentes/tareaexcel/encuenstasmantenimientos.jsp'}">
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Aplazar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Descartar"/>"/>
                        </c:if>
                        <c:if test="${secundaria eq 'componentes/tareaexcel/encuestasmarketing.jsp'}">
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Aplazar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Descartar"/>"/>
                        </c:if>
                        <c:if test="${secundaria eq 'componentes/tareaexcel/keybox.jsp'}">
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Aplazar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Descartar"/>"/>
                        </c:if>
                        <c:if test="${secundaria eq 'componentes/tareaexcel/limpiezacuota.jsp'}">
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Aplazar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Descartar"/>"/>
                        </c:if>
                        <c:if test="${secundaria eq 'componentes/tareaexcel/listadoassistant.jsp'}">
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Aplazar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Descartar"/>"/>
                        </c:if>
                        <c:if test="${secundaria eq 'componentes/tareaexcel/otrascampanias.jsp'}">
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Aplazar" />"/>
                            <input type="submit" class="btn btn-default" value="<spring:message code="boton.Descartar"/>"/>
                        </c:if>


                        <input type="submit" class="btn btn-primary" value="<spring:message code="boton.Finalizar"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fin Botones -->
        <!-- row -->


    </form>

</div>




</body>
</html>