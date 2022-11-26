$(document).ready(function () {

    $('.basket-button').on('click', function () {
        let id = $(this).data('id');
        let name = $(this).data('name');
        $("#basketCarName").text(name)
        $("#basket-add").data('id', id);
    });

    $('#basket-add').on('click', function () {
        let url = $(this).data('url');
        let id = $(this).data('id');
        let quantity = $("#quantity").val();

        $.ajax({
            url: url,
            type: "POST",
            data: {
                'id': id,
                'quantity': quantity
            },
            success: function (result) {
                console.log(result);
            },
            error: function (result) {
                console.log(result);
            }
        });

    });

    $('.basket-remove').on('click', function () {
        let id = $(this).data('id');
        $.ajax({
            url: "http://localhost:8080/car_shop_war_exploded/basket?" + $.param({"id": id}),
            type: "DELETE",
            success: function (result) {
                console.log(result);
                window.location.reload();
            },
            error: function (result) {
                console.log(result);
            }
        });
    });

    $('.basket-q-less').on('click', function () {
        let id = $(this).data('id');
        let operation = 0;
        $.ajax({
            url: "http://localhost:8080/car_shop_war_exploded/basket?" + $.param({"id": id, "operation": operation}),
            type: "PUT",
            success: function (result) {
                console.log(result);
                window.location.reload();
            },
            error: function (result) {
                console.log(result);
            }
        });
    });

    $('.basket-q-more').on('click', function () {
        let id = $(this).data('id');
        let operation = 1;
        $.ajax({
            url: "http://localhost:8080/car_shop_war_exploded/basket?" + $.param({"id": id, "operation": operation}),
            type: "PUT",
            success: function (result) {
                console.log(result);
                window.location.reload();
            },
            error: function (result) {
                console.log(result);
            }
        });
    });
});