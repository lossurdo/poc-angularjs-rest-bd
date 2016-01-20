angular.module('SeriadoModule', ['CrudServiceModule'])

.controller('SeriadoController', ['$scope', 'CrudService', '$routeParams', function($scope, CrudService, $routeParams) {

    $scope.objeto = { titulo: '' };
    $scope.resultados;
    $scope.idEdicao = $routeParams.id;

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
        $scope.objeto = { titulo:'' };
        $scope.resultados = null;
        $scope.pesquisar();
    }
     
    $scope.consultar = function(id) {
        CrudService.consultar(id, function(obj) {
            $scope.objeto = obj;
        });
    }

    $scope.alterar = function() {
        CrudService.alterar($scope.objeto, function(location) {
            console.log(location);
        });
    }

    // se id foi passado como parâmetro para edição
    if(angular.isDefined($scope.idEdicao)) {
        // consulta seus dados e atribui ao objeto
        $scope.consultar($scope.idEdicao);
    }
        
    // definindo padrão de chamada
    CrudService.init('http://localhost:8080/seriado-model/rest/seriados');
}])

;
