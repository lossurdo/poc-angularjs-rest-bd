angular.module('GeneroModule', ['CrudServiceModule'])

.controller('GeneroController', ['$scope', 'CrudService', '$routeParams', function($scope, CrudService, $routeParams) {

    $scope.url = 'http://localhost:8080/seriado-model/rest/generos';

    $scope.objeto = { genero: '' };
    $scope.resultados;
    $scope.idEdicao = $routeParams.id;

    $scope.salvar = function() {
        CrudService.salvar($scope.url, $scope.objeto, function(location) {
            console.log(location);
            CrudService.geraMensagemDefault();
        });
    }
    
    $scope.pesquisar = function() {
        CrudService.pesquisar($scope.url, $scope.objeto.genero, function(data) {
            $scope.resultados = data;
        });
    }

    $scope.excluir = function(obj) {
        CrudService.excluir($scope.url, obj.id, function() {
            $scope.limpar();
            CrudService.geraMensagemDefault();
        });
    }

    $scope.limpar = function() {
        $scope.objeto = { genero:'' };
        $scope.resultados = null;
        $scope.pesquisar();        
    }

    $scope.consultar = function(id) {
        CrudService.consultar($scope.url, id, function(obj) {
            $scope.objeto = obj;
        });
    }

    $scope.alterar = function() {
        CrudService.alterar($scope.url, $scope.objeto, function(location) {
            console.log(location);
            CrudService.geraMensagemDefault();
        });
    }

    // se id foi passado como parâmetro para edição
    if(angular.isDefined($scope.idEdicao)) {
        // consulta seus dados e atribui ao objeto
        $scope.consultar($scope.idEdicao);
    }

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
}])

;
