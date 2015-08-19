app.controller('marketingsurveytask-ctrl', function ($scope, $http, CommonService, $modal, $log) {
    //Angular Maintenance Survey Controller start
    $scope.getTarea = function () {
        //$log.debug("Loading Marketing Survey Task...")
        //$log.debug("Params: "
        //+ " ccUserId: " + $scope.ccUserId
        //+ " callingList: " + $scope.callingList
        //+ " taskId: " + $scope.tareaId);
        $http({method: 'GET',
            url: '/marketingsurveytask/gettarea',
            params: {ccUserId: $scope.ccUserId, callingList: $scope.callingList, tareaId: $scope.tareaId}
        }).
            success(function (data, status, headers, config) {
                CommonService.processBaseResponse(data, status, headers, config);
                $scope.tarea = data.tarea;
                //$log.debug("Loaded marketing survey task: ", data.tarea)
            }).
            error(function (data, status, headers, config) {
                CommonService.processBaseResponse(data, status, headers, config);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                //$log.debug("Error loading marketing survey task");
            });

        //$log.debug("Loading Excel Task Commons: Closing reason");
        $http({method: 'GET', url: '/exceltaskcommon/getClosingReason'}).
            success(function (data, status, headers, config) {
                CommonService.processBaseResponse(data, status, headers, config);
                $scope.closingReasonList = data.pairList;
                //$log.debug("Loaded Excel Task Commons: Closing reason: ", data.pairList);
            }).
            error(function (data, status, headers, config) {
                CommonService.processBaseResponse(data, status, headers, config);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                //$log.error("Error loading closing reason");
            });
    };


    $scope.getClosingReason = function(){
        //$log.debug("Loading Excel Task Commons: Closing reason");
        $http({method: 'GET', url: '/exceltaskcommon/getClosingReason'}).
            success(function (data, status, headers, config) {
                CommonService.processBaseResponse(data, status, headers, config);
                $scope.closingReasonList = data.pairList;
                //$log.debug("Closing reason list loaded", $scope.closingReasonList)
            }).
            error(function (data, status, headers, config) {
                CommonService.processBaseResponse(data, status, headers, config);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                //$log.error("Error loading closing reason list");
            });
    }

    $scope.getInstallationAndTask = function(){
        $scope.vm.appReady=false;

        //$log.debug("Loading Marketing Survey Task...");
        //$log.debug("Params: "
        //+ " installationId: " + $scope.installationId
        //+ " ccUserId: " + $scope.ccUserId
        //+ " callingList: " + $scope.callingList
        //+ " taskId: " + $scope.tareaId);
        $http({
            method: 'GET',
            url: 'marketingsurveytask/getInstallationAndTask',
            params: {callingList: $scope.callingList, tareaId: $scope.tareaId}
        }).
            success(function (data, status, headers, config) {
                //$log.debug("Loaded list assistant task:" ,data.tarea);
                //$log.debug("Loaded installation data:" ,data.installationData);
                $scope.tarea = data.tarea;
                $scope.installationData = data.installationData;
                CommonService.processBaseResponse(data,status,headers,config);
                $scope.getClosingReason();
                $scope.vm.appReady=true;
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                CommonService.processBaseResponse(data,status,headers,config);
                $scope.vm.appReady=true;
                //$log.error("Error loading list assistant task and/or installation data");
            });
    }
    //Ventana Aplazar - Start
    //Abre la ventana, posibles tamaños '', 'sm', 'lg'
    $scope.openDelayModal = function (size) {
        var modalInstance = $modal.open({
            animation: false, //Indica si animamos el modal
            templateUrl: 'deplayModalContent.html', //HTML del modal
            controller: 'DelayModalInstanceCtrl',  //Referencia al controller especifico para el modal
            size: size,
            resolve: {
                //Creo que esto es para pasar parametros al controller interno
                // items: function () {
                //     return $scope.items;
                // }
            }
        });

        //Funciones para recivir el cierre ok y el cancel
        modalInstance.result.then(function (delayInfo) {
            //Boton Ok del modal
            $scope.aplazar(delayInfo.delayDate, delayInfo.recallType);
        }, function (param) {
            //Boton cancelar del Modal
        });
    };
    $scope.aplazar = function (delayDate, recallType) {
        //$log.info('Delay to ' + delayDate + ' with recallType ' + recallType + ' task ' + JSON.stringify($scope.tarea));
        if ($scope.tarea) {
            var postponeRequest = {
                recallType: recallType,
                delayDate:  delayDate,
                task: $scope.tarea
            };

            //$log.info("Json of Request " + JSON.stringify(postponeRequest));

            $http({
                method: 'PUT',
                url: 'marketingsurveytask/aplazar',
                data: postponeRequest
            })
                .success(function (data, status, headers, config) {
                    CommonService.processBaseResponse(data, status, headers, config);
                })
                .error(function (data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    CommonService.processBaseResponse(data, status, headers, config);
                });
        }
    };
    //Ventana Aplazar - End
    $scope.finalizar = function(){
        //$log.debug("Finalizar Maintenance Survey task, task: ",$scope.tarea);
        var finalizeMaintenanceSurveyTaskRequest = {
            tarea:$scope.tarea
        };
        //$log("Finalizar Maintenance Survey Task, request: ", finalizeMaintenanceSurveyTaskRequest);
        $http({
            method: 'PUT',
            url: 'marketingsurveytask/finalizar',
            data: finalizeMaintenanceSurveyTaskRequest
        })
            .success(function (data, status, headers, config) {
                CommonService.processBaseResponse(data,status,headers,config);
                //$log.debug("Finalized maintenance survey task");
            })
            .error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                CommonService.processBaseResponse(data,status,headers,config);
                //$log.error("Error finalizing maintenance survey task");
            });
    };
});