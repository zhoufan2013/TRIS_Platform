/**
 * Created by zhoufan on 15/6/2.
 *
 * Based on Angular JS
 */

var tris = angular.module('tris', ['ngRoute', 'LocalStorageModule']);


tris.controller('dashboardController', ['$scope', function(scope){

    debugger;
    scope.user = {};

}]);


//TODO 用 directives 重写 菜单控制

tris.controller('loginController', ['$scope', '$location', 'localStorageService','Login', '$rootScope', '$http', function(scope, location, storageService, login, rootScope, http){


    debugger;
    scope.user = {};

    // 如果用户已经登录了$rootScope.user.token，则立即跳转到一个默认主页/upc_home上去，无需再登录
    //if(storageService.get('token')){
      //  location.path('/dashboard');
        //location.replace();
        //return;
    //}

    //登录校验监听事件
    scope.signin = function() {
        debugger;
        location.path('/dashboard');
        location.replace();

        rootScope.login_result = false;

        $('#sidebar').css('display', '');
        $('#person').css('display', '');
        $('#wrapper').toggleClass('sidebar-hide');


        /*
        var promise = login.sign(login.userInfo(scope.user));
        promise.then(function(data) {
            var rspCode = data.rspCode;
            var token = data.rspInfo.token;

            debugger;
            rootScope.login_result = false;

            location.path('/upc_home');
            location.replace();

            if(login.isRemember(scope.user)) {
                debugger;
                if(storageService.isSupported) {
                    storageService.set('token', token);
                } else {

                }

            }

        }, function(data){
            debugger;
        });*/


    }



}]);

function check() {
    return true;
}


/*
 $http.get('/images/owl-login-arm.png').then(function(response) {
 var time = response.config.responseTimestamp - response.config.requestTimestamp;
 console.log('The request took ' + (time / 1000) + ' seconds.');
 });*/

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

tris.controller('upcExecuteController', ['$http', function(http) {

    http.get('/api/category'
        ).success(function(data, status, headers, config) {
            debugger;
            //加载成功之后做一些事
            $scope.caseGroups = data;
        }).error(function(data, status, headers, config) {
            //处理错误
        });

}]);

tris.controller('userCaseController', ['$scope', function(scope){

    debugger;

    scope.usercases = [
        {
            "req_name": "【自优化】offer全景展示页面",
            "case_name": "测试新建 DSL Main Offer 正常流",
            "owner": "zhoufan",
            "exec_info": "UPC_CRM_001",
            "priority": "high",
            "description": "描述信息"
        },
        {
            "req_name": "【自优化】offer全景展示页面",
            "case_name": "测试发布 Offer 到 E2E 环境",
            "owner": "tianhj",
            "exec_info": "UPC_CRM_002",
            "priority": "high",
            "description": "描述信息"
        },
        {
            "req_name": "【自优化】offer全景展示页面",
            "case_name": "测试删除 Offer 功能",
            "owner": "hanll",
            "exec_info": "UPC_CRM_003",
            "priority": "high",
            "description": "描述信息"
        },
        {
            "req_name": "【自优化】offer全景展示页面",
            "case_name": "测试销售品关联产品功能",
            "owner": "hanll",
            "exec_info": "UPC_CRM_004",
            "priority": "high",
            "description": "描述信息"
        },
        {
            "req_name": "【自优化】offer全景展示页面",
            "case_name": "测试新建 DSL Main Offer 正常流",
            "owner": "zhoufan",
            "exec_info": "UPC_CRM_001",
            "priority": "high",
            "description": "描述信息"
        },
        {
            "req_name": "【自优化】offer全景展示页面",
            "case_name": "测试新建 DSL Main Offer 正常流",
            "owner": "zhoufan",
            "exec_info": "UPC_CRM_001",
            "priority": "high",
            "description": "描述信息"
        }
    ];


}]);

tris.controller('homeController', function($scope, $http, $rootScope){

    debugger;

    $('#wrapper').toggleClass('sidebar-hide');

    $rootScope.login_result = true;

    //TODO call restful API
    $scope.schedules =
    [
        {
            "case_version": "UPC Offer Case Set",
            "execute_time": "06:45"
        },
        {
            "case_version": "UPC Service Case Set",
            "execute_time": "23:05"
        }
    ];


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

        userInfo: function(user) {

            var signInfo = {};
            var userInfo = {};
            userInfo.userId = user.account;
            userInfo.password = user.password;
            signInfo.appId = "tris-web";//tris_server defines app-id for each tris_client
            signInfo.infoType = 2;//content_type:application/json
            signInfo.reqInfo = userInfo;
            return signInfo;
        },

        isRemember: function (user) {
            return user.remenber;
        },

        sign: function(signInfo) {
            //申明异步等待
            var deferred = promise.defer();
            http.post('/api/sign', signInfo)
                .success(function(data, status, headers, config){
                    debugger;
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
        templateUrl: '/partials/upc/login.html',
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
tris.config(function($routeProvider, localStorageServiceProvider, $httpProvider) {

    //$httpProvider.interceptors.push('UserInterceptor');
    //$httpProvider.interceptors.push('Login');

    $routeProvider
        .when('/asd', {

        })
        .when('/', {
            templateUrl: '/partials/upc/login.html',
            controller: 'loginController'
        })
        .when('/dashboard', {
            templateUrl: '/partials/dashboard.html',
            controller: 'dashboardController'
        })
        .when('/user_case', {
            templateUrl: '/partials/user_case.html',
            controller: 'userCaseController'
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
        })
        .when('/upc_execute', {
            templateUrl: '/partials/upc/execute.html',
            controller: 'upcExecuteController'
        })
        .when('/case_set', {
            templateUrl: '/partials/case_set.html'
        });

    localStorageServiceProvider
        .setPrefix('tris')
        .setStorageType('localStorage')
        .setStorageCookie(40, '/');

    // use the HTML5 History API
    //$locationProvider.html5Mode(true);
});





