$(document).ready(function() {

    $('.sort-param').on('change', function () {
        updateQueryStringParameter($(this).attr('name'), $(this).val());
    });

    function updateQueryStringParameter(key, value) {
        if ('URLSearchParams' in window) {
            var searchParams = new URLSearchParams(window.location.search);
            searchParams.set(key, value);
            searchParams.set("page", "1");
            window.location.search = searchParams.toString();
        }
    }
});