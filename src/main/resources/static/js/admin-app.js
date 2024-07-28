var app = angular.module("admin-app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/admin/product/index",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "/admin/authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .otherwise({
            template: "<h1 class='text-center'>FPT Administration</h1>"
        });
});
