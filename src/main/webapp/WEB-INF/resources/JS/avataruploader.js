$(document).ready(function () {
    $('#submit').prop('disabled', true);
    $('#file-picker').click(function () {
        $('#info').html('');
    });
    imagesvalid('#file-picker', '#submit', '#info', 1000000, 1);
});