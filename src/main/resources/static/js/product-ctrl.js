app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function () {
        // Load products
        $http.get("/api/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        });

        // Load categories
        $http.get("/api/categories").then(resp => {
            $scope.cates = resp.data;
        });
    };
    $scope.initialize();

    // Display on form
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $scope.form.imageUrl = "/static/images/" + item.image;
        $(".nav-tabs a:eq(0)").tab('show');
    };

    // Add new product
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post("/api/products", item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate);
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Product added successfully!");
        }).catch(error => {
            alert("Error adding product!");
            console.log("Error", error);
        });
    };

    // Update product
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/api/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items[index] = item;
            $scope.reset();
            alert("Product updated successfully!");
        }).catch(error => {
            alert("Error updating product!");
            console.log("Error", error);
        });
    };

    // Delete product
    $scope.delete = function (item) {
        $http.delete(`/api/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Product deleted successfully!");
        }).catch(error => {
            alert("Error deleting product!");
            console.log("Error", error);
        });
    };

    // Upload image
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/api/upload/images', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
            $scope.form.imageUrl = resp.data.url;
        }).catch(error => {
            alert("Error uploading image");
            console.log("Error", error);
        });
    };

    // Reset form
    $scope.reset = function () {
        $scope.form = {};
    };

    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    };
});
