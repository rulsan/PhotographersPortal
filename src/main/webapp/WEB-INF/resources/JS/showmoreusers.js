/*!
 * Search users by login and show more users on scroll down
 *
 * Date: 2013-12-21
 */

$(document).ready(function() {
$("#otherprofiles").addClass("active");
var page = 0;
var done = false;
var processing = false;
var login = "";
$("#sfield").change(function(){
 $("#noSearchedUsers").text("");
 done = false;
 page = 0;
 login = $("#sfield").val();
 $("#listofusers").find(".listuser").remove();
 searchUsers(login, page);
});
$(window).scroll(function(){
if(done || processing) return false;
if($(document).height() == $(window).scrollTop()+$(window).height()){
    page++;
    processing = true;
    searchUsers(login, page);
    processing = false;
  }
});
function searchUsers(login, page) {
  $.getJSON( "/portal/profile/searchUsers", { searchLogin: login, page: page } )
     .done(function( foundList ) {
       if(foundList.length < 25) done = true;
       if(foundList.length == 0 && $(".listuser").length == 0) $("#noSearchedUsers").text("Ничего не найдено");
       for (var i=0;i<foundList.length;i++){
           var data =  {user: foundList[i]};
           $( "#listTemplate" ).tmpl( data ).appendTo( "#listofusers" );
       }
     })
     .fail(function() {
         alert( "Ошибка при обновлении списка" );
     });
}
});


