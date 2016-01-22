/* *************************************************************
 * Service genérico para permitir chamadas CRUD padrão
 * RESTful. Utilizado para chamar a camada MODEL do Java.
 * 
 * Por: Rafael Lossurdo
 ************************************************************ */
angular.module('CrudServiceModule', [])

.service('CrudService', ['$http', function($http) {
            
    this.salvar = function(serviceURL, jsonObject, fn) {
        console.log(jsonObject);
        
        $http({
            method: 'POST',
            data: jsonObject,
            url: serviceURL,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status, headers, config, statusText) {            
            fn(headers('Location'));
        }).error(function (data, status, header, config, statusText) {
            console.log('Erro: ' + statusText);
        });        
    }
    
    this.consultar = function(serviceURL, id, fn) {
        console.log(id);
        $http.get(serviceURL + "/" + id)
            .success(function (data) {
                console.log(data);
                fn(data);
            })
            .error(function (data, status, header, config, statusText) {
                console.log('Erro: ' + statusText);
            });
    }
    
    this.excluir = function(serviceURL, id, fn) {
        console.log(id);
        $http.delete(serviceURL + "/" + id)
            .success(function (data) {
                console.log(data);
                fn();
            })
            .error(function (data, status, header, config, statusText) {
                console.log('Erro: ' + statusText);
            });
    }
    
    this.alterar = function(serviceURL, jsonObject, fn) {
        console.log(jsonObject);
        
        $http({
            method: 'PUT',
            data: jsonObject,
            url: serviceURL,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status, headers, config, statusText) {            
            fn(headers('Location'));
        }).error(function (data, status, header, config, statusText) {
            console.log('Erro: ' + statusText);
        });        
    }

    this.pesquisar = function(serviceURL, jsonObject, fn) {
        console.log(jsonObject);

        $http.get(serviceURL + "/search/" + jsonObject)
            .success(function (data) {
                console.log(data);
                fn(data);
            });
    }
        
    // definição de mensagens padrões para ações de CRUD - abertura do modal
    this.geraMensagemDefault = function() {
        $('#myModal').modal();        
    };    
    
}]);