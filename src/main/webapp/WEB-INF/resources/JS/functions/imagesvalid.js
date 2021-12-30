function imagesvalid(input, submit, info, filemaxsize, fileamount) {
    $(input).change(function () {
        var error = '';
        if ($(input)[0].files.length > fileamount) {
            error = error + ' Допускаемое количество загружаемых фотографий - ' + fileamount;
        } else {
            jQuery.each($(input)[0].files, function (i, file) {
                if (file.size > filemaxsize) {
                    error = error + ' Файл ' + file.name + ' превышает допустимый размер.' + '<br>';
                } else if (file.type != 'image/png' && file.type != 'image/jpg' && !file.type != 'image/gif' && file.type != 'image/jpeg') {
                    error = error + ' Файл ' + file.name + ' не корректного формата.' + '<br>';
                }
            });
        }
        if (error != '') {
            $(info).html(error);
            $(submit).prop('disabled', true);
        } else {
            $(submit).prop('disabled', false);
        }


    });
}





