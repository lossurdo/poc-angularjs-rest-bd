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
        /* ********** SERIADO ****************** */
        when('/seriado-pesquisar', {
            templateUrl: 'partials/seriado-pesquisar.html',
            controller: 'SeriadoController'
        }).
        when('/seriado-novo', {
            templateUrl: 'partials/seriado-novo.html',
            controller: 'SeriadoController'
        }).
        when('/seriado-editar/:id', {
            templateUrl: 'partials/seriado-editar.html',
            controller: 'SeriadoController'
        }).
        /* ********** GÊNERO ****************** */
        when('/genero-pesquisar', {
            templateUrl: 'partials/genero-pesquisar.html',
            controller: 'GeneroController'
        }).
        when('/genero-novo', {
            templateUrl: 'partials/genero-novo.html',
            controller: 'GeneroController'
        }).
        when('/genero-editar/:id', {
            templateUrl: 'partials/genero-editar.html',
            controller: 'GeneroController'
        }).
        /* ********** PADRÃO ****************** */
        when('/', {
            templateUrl: 'partials/principal.html',
            controller: 'AppController'
        }).
        otherwise({
            redirectTo: '/'
        });
}])

/* **************************************************
 * Definição dos templates de página
 */
.directive('conteudoInterno', function () {
    return {
        restrict: 'E',
        transclude: true,
        template: '<div class="row" ng-if="mensagem"><div class="col-md-12"><span class="bg-info mensagem">{{mensagem}}</span></div></div><div class="row"> <div class="col-md-3"></div><div class="col-md-6" ng-transclude></div><div class="col-md-3"></div></div>'
    };
})

.directive('avaliacaoSeriado', function() {
    return {
        restrict: 'E',
        scope: {
            valorNota: '@nota'
        },
        templateUrl: '../templates/avaliacao-seriado.html'
    }
})

;       