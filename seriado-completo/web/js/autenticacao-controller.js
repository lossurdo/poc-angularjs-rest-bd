angular.module('AutenticacaoModule', ['CrudServiceModule'])

.controller('AutenticacaoController', ['$scope', 'CrudService', '$http', function($scope, CrudService, $http) {

    $scope.username = 'admin';
    $scope.password = 'admin';    
    $scope.token = '';
    $scope.consulta;
            
    $scope.autenticar = function() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/seriado-completo/rest/authdummy',
            headers: {
                'usuario': $scope.username,
                'senha': $scope.password
            }
        })
        .then(function(response) {          
            console.log(response);
            $scope.token = response.headers('token');
        });
    };
                        
    $scope.enviandoToken = function() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/seriado-completo/rest/generos',
            headers: {
                'token': $scope.token
            }
        })
        .then(function(response) {          
            console.log(response);
            $scope.consulta = JSON.stringify(response.data, null, " ");
        });
    };
            
}])

;
