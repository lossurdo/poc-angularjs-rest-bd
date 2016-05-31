angular.module('AutenticacaoModule', ['CrudServiceModule'])

.controller('AutenticacaoController', ['$scope', 'CrudService', function($scope, CrudService) {

    // url padrão da chamada REST
    $scope.url = 'http://localhost:8080/seriado-completo/rest/xxx';
            
}])

;
