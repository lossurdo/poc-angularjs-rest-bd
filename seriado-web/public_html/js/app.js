angular.module('app', ['ngRoute'])

.controller('AppController', ['$scope', function($scope) {

}])

.config(['$routeProvider', function ($routeProvider) {        
    $routeProvider.
        when('/', {
            templateUrl: 'principal.html'
        }).
        when('/pesquisar-seriado', {
            templateUrl: 'pesquisar-seriado.html'
            //,
            //controller: 'PesquisarSeriadoController'
        }).
        /*when('/users', {
            templateUrl: 'users.html',
            controller: 'UserController'
        }).*/
        otherwise({
            redirectTo: '/'
        });
}]);
        