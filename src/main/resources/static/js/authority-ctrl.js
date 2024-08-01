app.controller("authority-ctrl", function ($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function () {
        // load all roles
        $http.get("/api/roles").then(resp => {
            $scope.roles = resp.data;
        });

        // load staffs and directors (administrators)
        $http.get("/api/accounts?admin=false").then(resp => {
            $scope.admins = resp.data;
        });

        // load authorities of staffs and directors
        $http.get("/api/authorities?admin=false").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        });
    };

    $scope.authority_of = function (acc, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
        }
    };

    $scope.authority_changed = function (acc, role) {
        var authority = $scope.authority_of(acc, role);
        if (authority) { // already has authority => revoke authority (delete)
            $scope.revoke_authority(authority);
        } else { // does not have authority => grant authority (add new)
            authority = { account: acc, role: role };
            $scope.grant_authority(authority);
        }
    };

    // Add new authority
    $scope.grant_authority = function (authority) {
        $http.post(`/api/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data);
            alert("Authority granted successfully");
        }).catch(error => {
            alert("Failed to grant authority");
            console.log("Error", error);
        });
    };

    // Delete authority
    $scope.revoke_authority = function (authority) {
        $http.delete(`/api/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            alert("Authority revoked successfully");
        }).catch(error => {
            alert("Failed to revoke authority");
            console.log("Error", error);
        });
    };

    $scope.initialize();

    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page * this.size;
            return $scope.admins.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.admins.length / this.size);
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
