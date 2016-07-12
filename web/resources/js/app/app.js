/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 // Define the `BManager` module
var BManagerApp = angular.module('BManager', ['ngRoute', "angularfaces"]);

// configure our routes
BManagerApp.config(function($routeProvider) {
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl : 'views/overview.xhtml',
            controller  : 'mainController'
        })

        // route for the about page
        .when('/project/create', {
            templateUrl : 'views/project/create.xhtml',
            controller  : 'aboutController'
        })

        // route for the about page
        .when('/project/view/:projectid', {
            templateUrl : 'views/project/view.xhtml',
            controller  : 'projectController'
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
        });
});

// create the controller and inject Angular's $scope
BManagerApp.controller('mainController', function($scope) {
    // This initializes the Angular Model with the values of the JSF
    // bean attributes
    initJSFScope($scope);

    // create a message to display in our view
    $scope.message = 'Here you will have your dashboard and overview of all projects!';
});

BManagerApp.controller('aboutController', ['$scope', '$location', function($scope, $location) {
    // This initializes the Angular Model with the values of the JSF
    // bean attributes
    initJSFScope($scope);

    $scope.message = 'Look! I am an about page.';

    $scope.goNext = function (hash) {
        $location.path(hash);
    }

    //<p:commandButton value="Create project" id="nonAjax" styleClass="centered" actionListener="#{projectManagedBean.setTester('LetsGo')}" ajax="true" />
}]);

BManagerApp.controller('contactController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});

BManagerApp.controller('projectController', function($scope, $http, $routeParams) {
    // This initializes the Angular Model with the values of the JSF
    // bean attributes
    initJSFScope($scope);

    $scope.projectId = $routeParams.projectid;
    $scope.message = 'Viewing project number' + $scope.projectId;
    
});
