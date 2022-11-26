$(document).ready(function () {

    $(".small a").click(function (e) {
        if ($(".big img").attr('src') !== $(this).attr('href')) {
            $(".big img").hide().attr('src', $(this).attr('href')).fadeIn(1000);
        }
        e.preventDefault();
    });

    $(".small a img").click(function() {
        $(".small a img").fadeTo(500, 1).css({
            'border': 'none'
        });
        $(this).fadeTo(500, 0.6).css({
            'border': '1px dotted black'
        });
    });
});