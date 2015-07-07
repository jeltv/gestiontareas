//Another Campaigns ANGULARJS script START
app.controller('anotherCampaigns', function ($scope, $http) {

    $scope.getTarea = function () {
        console.log("Loading Another Campaigns Task...")
        $http({method: 'GET', url: 'visortarea/anothercampaignstask/getanothercampaginstask'}).
            success(function (data, status, headers, config) {
                $scope.tarea = data;
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        console.log("Loaded Another Campaigns Task")

        console.log("Loading Excel Task Commons: Closing reason");
        $http({method: 'GET', url: 'visortarea/exceltaskcommon/getClosingReason'}).
            success(function (data, status, headers, config) {
                $scope.closingReasonList = data;
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        console.log("Loaded Excel Task Commons: Closing reason");
    };
});
//Another Campaigns ANGULARJS script END