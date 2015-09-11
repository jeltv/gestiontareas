<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="panel panel-default">
    <div class="panel-body">
        <div class="row" align="right">
            <!-- Panel de botones - Seleccion de controlador -->
            <div class="container-fluid">
                <app:inputButtonNG value="boton.Aplazar" button_type="default" ng_click="openDelayModal()" fluid_wrapper="true" ng_disabled="(tarea===undefined || tarea===null) ? true : false"/>
                <app:inputButtonNG value="boton.Descartar" button_type="default" ng_click="(fromSearch!='true') ? closeInteraction() : descartar()" fluid_wrapper="true"/>
                <app:inputButtonNG value="boton.Finalizar" type="submit" button_type="primary" ng_click="formVisorTarea.$valid ? finalizar() : null" fluid_wrapper="true" ng_disabled="(tarea===undefined || tarea===null) ? true : false"/>
            </div>
        </div>
    </div>
</div>


<!-- Dialogo Delay Modal -->
<app:delayModalContent/>

<!-- Fin Botones -->
