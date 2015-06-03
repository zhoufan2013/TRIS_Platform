/**
 * Created by zhoufan on 15/6/2.
 *
 * Based on Angular JS
 */
/**
 *
    var tris = angular.module('tris', ['ngRoute']);

     tris.controller('homeController', function($scope){
        $scope.message = "zhoufan";
    });


     tris.config(function($routeProvider, $locationProvider){

        $routeProvider.
            when('/', {
                templateUrl: 'html/index.html'

            })
            when('/upc_execute', {
                templateUrl: '/upc/execute.html',
                controller: 'upcExecuteController'
            })

        // use the HTML5 History API
        $locationProvider.html5Mode(true);
    });

     tris.controller('upcExecuteController', function($scope){
        $scope.message = "zhoufan";
    });
 *
 *
 *
 */

var tris = angular.module('tris', ['ngRoute']);

tris.controller('homeController', function($scope, $http){
    $scope.message = "zhoufan";

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
            debugger;
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

//Single Page 路由配置
tris.config(function($routeProvider){

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
        .when('/upc', {
            templateUrl: '/partials/upc/login.html'
        });

    // use the HTML5 History API
    //$locationProvider.html5Mode(true);
});




