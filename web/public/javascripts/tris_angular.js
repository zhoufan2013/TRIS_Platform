/**
 * Created by zhoufan on 15/6/2.
 *
 * Based on Angular JS
 */

var tris = angular.module('tris', ['ngRoute', 'LocalStorageModule']);


//TODO 用 directives 重写 菜单控制

tris.controller('loginController', ['$scope', '$location', 'localStorageService','Login', '$rootScope', function(scope, location, storageService, login, rootScope){

    scope.user = {};

    // 如果用户已经登录了$rootScope.user.token，则立即跳转到一个默认主页/upc_home上去，无需再登录
    /*if($rootScope.user.token){
        $location.path('/upc_home');
        $location.$replace();
        return;
    }*/


    //登录校验监听事件
    scope.signin = function() {


        var promise = login.sign(scope.user);
        promise.then(function(data){
            var category = data;


            //var account = scope.user.account;
            //var password = scope.user.password;

            var remenber = scope.user.remenber;

            if(storageService.isSupported) {
                if(remenber == 'true') {
                    storageService.set('zhoufan', 'yes');
                }
            }

            location.path('/upc_home');
            location.replace();

            //Mock verify user info
            if (password == '123') {

                debugger;
                rootScope.login_result = false;

                /*
                 $http.get('/images/owl-login-arm.png').then(function(response) {
                 var time = response.config.responseTimestamp - response.config.requestTimestamp;
                 console.log('The request took ' + (time / 1000) + ' seconds.');
                 });*/

                location.path('/upc_home');
                location.replace();

            } else {
                scope.errorName = '';
            }


        }, function(data){
            debugger;
        });

    }

}]);

tris.controller('upcDetailController', function($scope){

    $scope.executions = [
        {
            "case_version": "0.8",
            "case_unit": "low",
            "executor": "caojian",
            "start_time": "22:42 31/05/2015",
            "pass_rate": "65%"
        },
        {
            "case_version": "0.8",
            "case_unit": "low",
            "executor": "tianhj",
            "start_time": "22:42 19/09/2015",
            "pass_rate": "70%"
        },
        {
            "case_version": "0.8",
            "case_unit": "low",
            "executor": "zhaopy",
            "start_time": "01:42 29/08/2015",
            "pass_rate": "86%"
        },
        {
            "case_version": "1.1",
            "case_unit": "low",
            "executor": "zhoufan",
            "start_time": "22:42 30/01/2015",
            "pass_rate": "76%"
        },
        {
            "case_version": "0.8",
            "case_unit": "low",
            "executor": "caiwm",
            "start_time": "22:42 11/04/2015",
            "pass_rate": "96%"
        }
    ];

});

tris.controller('homeController', function($scope, $http, $rootScope){

    $rootScope.login_result = true;

    //TODO call restful API
    $scope.schedules =
    [
        {
            "case_version": "0.8",
            "execute_time": "06:45"
        },
        {
            "case_version": "0.32",
            "execute_time": "23:05"
        }
    ];


    $http.get('/api/category'
        ).success(function(data, status, headers, config) {
            //加载成功之后做一些事
            $scope.caseGroups = data;
        }).error(function(data, status, headers, config) {
            //处理错误
        });

    //TODO call restful API
    /*
    $scope.caseGroups =
    [
        {
            "category": "All",
            "groups": []
        },
        {
            "category": "Functional",
            "groups": [
                {
                    "group": "BVTs"
                },
                {
                    "group": "Core Tests"
                },
                {
                    "group": "Func Tests"
                }
            ]
        },
        {
            "category": "Priority",
            "groups": [
                {
                    "group": "High"
                },
                {
                    "group": "Medium"
                },
                {
                    "group": "Low"
                }
            ]
        }
    ];*/



});

tris.factory('reqRespTimeMarker', [function(){

    var timestampMarker = {
        request: function(config){
            config.requestTimestamp = new Date().getTime();
            return config;
        },
        response: function(response) {
            response.config.responseTimestamp = new Date().getTime();
            return response;
        }
    };

    return timestampMarker;

}]);


/**
 * Define Login Service
 * #auth
 * #signin
 *
 */
tris.service('Login', ['$http', '$q', function(http, promise) {

    var login = {

        sign: function(user) {
            //申明异步等待
            var deferred = promise.defer();
            http.get('/api/category')
                .success(function(data, status, headers, config){
                    deferred.resolve(data);
                }).error(function(data, status, headers, config){
                    deferred.reject(data);
                });
            return deferred.promise;
        }

    };
    return login;

}]);

tris.directive('loginButton', [function(){

    return {
        restrict: "A",
        link: function(scope, element, attrs){
            element.bind("click", function(){
                debugger;
                alert("233")

            });
        }
    }
}]);


tris.directive('hello', function() {
    return {
        restrict: 'E',
        template: '<div>Hi there</div>',
        replace: true
    };
});



//拦截所有Restful API 请求，header加上token
/*tris.factory('UserInterceptor', ["$q", "$rootScope", function($q, $rootScope) {
    return {
        request: function(config){
            config.headers["TOKEN"]  = $rootScope.user.token;
            return config;
        },
        responseError: function(rejection) {
            if(rejection.status === 401) {
                //用户鉴权失败 TODO

            }
            return $q.reject(rejection);
        }
    };
}]);*/

//Single Page 路由配置
tris.config(function($routeProvider, localStorageServiceProvider, $httpProvider){

    //$httpProvider.interceptors.push('UserInterceptor');
    //$httpProvider.interceptors.push('Login');

    $routeProvider
        .when('/asd', {

        })
        .when('/', {
            templateUrl: '/partials/home.html'
        })
        .when('/upc_execute', {
            templateUrl: '/partials/upc/execute.html'
        })
        .when('/upc_analysis', {
            templateUrl: '/partials/upc/analysis.html'
        })
        .when('/upc_detail', {
            templateUrl: '/partials/upc/detail.html'
        })
        .when('/upc', {
            templateUrl: '/partials/upc/login.html',
            controller: 'loginController'
        })
        .when('/upc_home', {
            templateUrl: '/partials/upc/upc_home.html'
        });

    localStorageServiceProvider
        .setPrefix('tris')
        .setStorageType('localStorage')
        .setStorageCookie(40, '/');

    // use the HTML5 History API
    //$locationProvider.html5Mode(true);
});





