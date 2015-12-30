angular.module('GeneroModule', ['CrudServiceModule'])

.controller('GeneroController', ['$scope', 'CrudService', function($scope, CrudService) {

    $scope.objeto = { genero: '' };
    $scope.resultados;

    $scope.salvar = function() {
        CrudService.salvar($scope.objeto, function(location) {
            console.log(location);
        });
    }
    
    $scope.pesquisar = function() {
        CrudService.pesquisar(angular.toJson($scope.objeto), function(data) {
            $scope.resultados = data;
        });
    }

    $scope.excluir = function(obj) {
        CrudService.excluir(obj.id, function() {
            $scope.limpar();
        });
    }

    $scope.limpar = function() {
        $scope.objeto = { genero:'' };
        $scope.resultados = null;
        $scope.pesquisar();
    }

    // definindo padrão de chamada
    CrudService.init('http://localhost:8080/seriado-model/rest/generos');
}])

;
