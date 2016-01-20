angular.module('GeneroModule', ['CrudServiceModule'])

.controller('GeneroController', ['$scope', 'CrudService', '$routeParams', function($scope, CrudService, $routeParams) {

    $scope.objeto = { genero: '' };
    $scope.resultados;
    $scope.idEdicao = $routeParams.id;

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
    CrudService.init('http://localhost:8080/seriado-model/rest/generos');

    /*
     * Método utilizado para ajudar a marcar os
     * checkboxes de gêneros no seriado.
     */
    $scope.buscaGenero = function (id, obj) {
        for(var i=0; i<obj.length; i++) {
            if(obj[i].id === id) {
                return true;
            }
        }
        return false;
    }
        
    $scope.limpar();
}])

;
