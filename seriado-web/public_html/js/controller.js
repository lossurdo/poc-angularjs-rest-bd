var app = angular.module('app', ['CrudServiceApp'])

.controller('MeuController', ['$scope', 'CrudService', function($scope, CrudService) {

    $scope.fields;
    $scope.debug;

    $scope.salvar = function() {
        CrudService.salvar($scope.fields, function(location) {
            console.log(location);
        });
    }
    
    $scope.pesquisar = function() {
        CrudService.pesquisar($scope.fields, function(data) {
            $scope.debug = angular.toJson(data, true);
        });
    }

    // definindo padrão de chamada
    CrudService.init('http://localhost:9000/rest/seriados.json.js');
}])

;
