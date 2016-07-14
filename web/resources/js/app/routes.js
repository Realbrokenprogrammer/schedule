/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 angular.module('BManager.routes', ['ngRoute',])

 .config(function($routeProvider) {
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
            controller  : 'ProjectController'
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
        });
});