angular.module('SeriadoModule', ['CrudServiceModule'])

.controller('SeriadoController', ['$scope', 'CrudService', '$routeParams', function($scope, CrudService, $routeParams) {

    // url padrão da chamada REST
    $scope.url = 'http://localhost:8080/seriado-model/rest/seriados';
    
    $scope.objeto = { titulo: '' };
    $scope.resultados;
    $scope.idEdicao = $routeParams.id;

    /*
     * utilizado para armazenar os gêreros possíveis e os selecionados
     * pelo usuário no listbox
     */
    $scope.generosSelecionados = {
        opcoesDisponiveis: null,
        opcoesSelecionadas: null
    };
    
    $scope.salvar = function() {
        $scope.objeto.generos = $scope.generosSelecionados.opcoesSelecionadas;
        CrudService.salvar($scope.url, $scope.objeto, function(location) {
            console.log(location);
            CrudService.geraMensagemDefault();
        });
    }

    $scope.pesquisar = function() {
        CrudService.pesquisar($scope.url, angular.toJson($scope.objeto), function(data) {
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
        $scope.objeto = { titulo:'' };
        $scope.resultados = null;
        $scope.pesquisar();
    }
     
    $scope.consultar = function(id) {
        CrudService.consultar($scope.url, id, function(obj) {
            $scope.objeto = obj;
        });
    }

    $scope.alterar = function() {
        $scope.objeto.generos = $scope.generosSelecionados.opcoesSelecionadas;        
        CrudService.alterar($scope.url, $scope.objeto, function(location) {
            console.log(location);
            CrudService.geraMensagemDefault();
        });
    }

    $scope.listarGeneros = function() {
        CrudService.pesquisar('http://localhost:8080/seriado-model/rest/generos', 
            angular.toJson({genero: ''}), function(data) {
            $scope.generosSelecionados.opcoesDisponiveis = data;
        });
    }

    // se id foi passado como parâmetro para edição
    if(angular.isDefined($scope.idEdicao)) {
        // consulta seus dados e atribui ao objeto
        $scope.consultar($scope.idEdicao);
    }
        
}])

;
