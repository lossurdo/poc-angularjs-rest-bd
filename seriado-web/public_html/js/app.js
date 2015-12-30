angular.module('AppModule', ['ngRoute', 
    'SeriadoModule', 
    'GeneroModule'
])

.controller('AppController', ['$scope', function($scope) {

}])

/* **************************************************
 * Configuração das rotas
 */
.config(['$routeProvider', function ($routeProvider) {        
    $routeProvider.
        when('/', {
            templateUrl: 'partials/principal.html',
            controller: 'AppController'
        }).
        when('/seriado-pesquisar', {
            templateUrl: 'partials/seriado-pesquisar.html',
            controller: 'SeriadoController'
        }).
        when('/genero-pesquisar', {
            templateUrl: 'partials/genero-pesquisar.html',
            controller: 'GeneroController'
        }).
        otherwise({
            redirectTo: '/'
        });
}])

/* **************************************************
 * Definição dos templates de página
 */
.directive('conteudo-interno', function () {
    return {
        restrict: 'E',
        transclude: true,
        template: '<div class="row"><div class="col-md-3"></div><div class="col-md-6" ng-transclude></div><div class="col-md-3"></div></div>'
    };
})

;       