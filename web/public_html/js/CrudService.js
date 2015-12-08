angular.module('CrudServiceApp', [])

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
        }).success(function (data, status, headers, config) {
            console.log(data);
            fn(headers.location);
        }).error(function (data, status, headers, config) {
            console.log(data);
            fn(headers.location);
        });        
    }
    
    this.consultar = function(jsonObject) {
        console.log(jsonObject);
    }
    
    this.excluir = function(jsonObject) {
        console.log(jsonObject);
    }
    
    this.alterar = function(jsonObject) {
        console.log(jsonObject);
    }

    this.pesquisar = function(jsonObject, fn) {
        console.log(jsonObject);

        $http.get(this.$serviceURL)
        .success(function (data) {
            console.log(data);
            fn(data);
        });
    }
        
}]);