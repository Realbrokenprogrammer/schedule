angular.module('BManager.controllers.Project', [])

.controller('ProjectController', function($scope, $http, $routeParams) {
	// This initializes the Angular Model with the values of the JSF
    // bean attributes
    initJSFScope($scope);

    $scope.projectId = $routeParams.projectid;
    $scope.message = 'Viewing project number' + $scope.projectId;

    document.getElementById("formId:x").value = $scope.projectId;
    $myBtn = document.getElementById("formId:ajax");
    $myBtn.click(); //Click the form button
    $myBtn = null;

    //Remove autosubmitted form.
    $('#formId').remove();
});