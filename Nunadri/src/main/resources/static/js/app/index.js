var main = {
    init: function () {
        var _this = this;
        $('#btn-seve').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var data = {
            title: $('#noticeTitle').val(),
            author: $('#author').val(),
            content: $('#noticeContent').val()
        };

        $.ajax({
            type: 'POST',
            url: 'view/',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();