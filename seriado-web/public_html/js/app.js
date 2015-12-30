angular.module('app', ['ngRoute'])

.controller('AppController', ['$scope', function($scope) {

}])

.config(['$routeProvider', function ($routeProvider) {        
    $routeProvider.
        when('/', {
            templateUrl: 'partials/principal.html'
        }).
        when('/pesquisar-seriado', {
            templateUrl: 'partials/pesquisar-seriado.html'
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
        