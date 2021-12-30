/*!
 * Pick exiting avatar: getting json list of avatar URLs
 * and sending chosen avatar String url to server as ajax post parameter
 *
 * Date: 2013-12-15
 */

$(document).ready(function() {
$("#edit_profile").addClass("active");
$(".avatar").mouseenter(function(){
    $("#change").slideToggle("fast");
});
$(".avatar").mouseleave(function(){
    $("#change").slideToggle("fast");
});
$("#change").on("click", function() {
    if($(this).hasClass("selected")) {
        deselect();
    } else {
        $(this).addClass("selected");
        $.getJSON("/portal/profile/getAvatarsUrl")
         .done(function(list) {
            $(".pop").find("img").remove();
            if(list.length == 0) $("#noavatars").text("Нет фотографий, загрузите.");
            for (var i=0;i<list.length;i++){
                 var href = $("<img>").attr("src", list[i]);
                 href.appendTo( ".pop" );
            }
            $(".pop").find("img").css({width:"128px",height:"128px"});
        })
        .fail(function() {
            alert( "Ошибка при загрузке аватарок" );
        });
        $(".pop").slideFadeToggle();
        $(".pop").on("click", "img", function(){
             $(".avatar").find("img").remove();
             var selected=$(this).clone();
             selected.css({width:"180px",height:"180px"}).appendTo( ".avatar" );
             deselect();
             var url = selected.attr("src");
             $.post( "/portal/profile/updateAvatarUrl", { url: url} )
             .fail(function() {
                 alert( "Ошибка при смене аватарки" );
             });
        });
    }
    return false;
});

$("#close").on("click", function() {
    deselect();
    return false;
});
});

function deselect() {
    $(".pop").slideFadeToggle(function() {
        $(".avatar").removeClass("selected");
    });
}
$.fn.slideFadeToggle = function(easing, callback) {
    return this.animate({ opacity: 'toggle', height: 'toggle' }, "fast", easing, callback);
};