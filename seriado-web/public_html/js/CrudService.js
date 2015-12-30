angular.module('CrudServiceModule', [])

.service('CrudService', ['$http', function($http) {

    var $serviceURL;

    this.init = function(url) {
        this.$serviceURL = url;
    }
            
    this.salvar = function(jsonObject, fn) {
        console.log(jsonObject);
        
        $http({
            method: 'POST',
            data: jsonObject,
            url: this.$serviceURL,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status, headers, config, statusText) {            
            fn(headers('Location'));
        }).error(function (data, status, header, config, statusText) {
            console.log('Erro: ' + statusText);
        });        
    }
    
    this.consultar = function(jsonObject) {
        console.log(jsonObject);
    }
    
    this.excluir = function(id, fn) {
        console.log(id);
        $http.delete(this.$serviceURL + "/" + id)
            .success(function (data) {
                console.log(data);
                fn();
            });
    }
    
    this.alterar = function(jsonObject) {
        console.log(jsonObject);
    }

    this.pesquisar = function(jsonObject, fn) {
        console.log(jsonObject);

        $http.get(this.$serviceURL + "/search/" + jsonObject)
            .success(function (data) {
                console.log(data);
                fn(data);
            });
    }
        
}]);