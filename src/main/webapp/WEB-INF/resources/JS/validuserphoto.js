$(document).ready(function () {
    $("#photos").addClass("active");
    $("#setfilecolor").css({'color': '#778899'});

    $('#submit').prop('disabled', true);
    $('#file-picker').click(function () {
        $('#info').html('');
    });
    imagesvalid('#file-picker', '#submit', '#info', 10000000, 100);
});