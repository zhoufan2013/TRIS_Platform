/**
 * Created by zhoufan on 15/6/2.
 *
 * Based on Angular JS
 */

var tris = angular.module('tris', ['ngRoute']);


tris.controller('menuController', function($scope){

    //control Veris UPC Menu
    $scope.upc_menu_control = false;

    //control Veris CRM Menu
    $scope.crm_menu_control = false;

    $scope.$on("upc_menu_control",
        function (event, msg) {
            console.log("upc_menu_control", msg);
            $scope.upc_menu_control = msg;
        });
});

tris.controller('loginController', function($scope, $location){

    $scope.user = {};
    $scope.upc_login_result = false;

    //登录校验监听事件
    $scope.signin = function(){
        var account = $scope.user.account;
        var password = $scope.user.password;

        //Mock verify user info
        if (password == '123') {
            // if not successful, bind errors to error variables
            //$scope.errorName = data.errors.name;

            //$scope.upc_menu_control = true;

            $scope.$emit("upc_menu_control", true);


            $scope.upc_login_result = true;

            $location.path('/upc_home');
            $location.replace();

        } else {
            // if successful, bind success message to message
            $scope.errorName = '';
        }
    }

});

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

tris.controller('homeController', function($scope, $http){

    $scope.$on("upc_menu_control",
        function (event, msg) {
            console.log("parent", msg);
            $scope.$broadcast("upc_menu_control", msg);
        });

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
        .when('/upc_detail', {
            templateUrl: '/partials/upc/detail.html'
        })
        .when('/upc', {
            templateUrl: '/partials/upc/login.html'
        })
        .when('/upc_home', {
            templateUrl: '/partials/upc/upc_home.html'
        });




    // use the HTML5 History API
    //$locationProvider.html5Mode(true);
});





