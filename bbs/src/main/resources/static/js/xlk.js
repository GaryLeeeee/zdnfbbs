

$(document).ready(function () {
            $(".navgation input").each(function () {
                var this_div = $(".navgation div");
                var _inx = $(".navgation input").index(this);
                $(this).click(
                function () { this_div.eq(_inx).slideToggle(); },
                function () { this_div.eq(_inx).slideToggle(); }
           );
            });
        });