<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="<c:url value="/resources/JS/functions/imagesvalid.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/JS/validuserphoto.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/JS/gallery/gallery.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/JS/gallery/jquery.elastislide.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/gallery/elastislide.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/gallery/gallerystyle.css"/>">

<script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">
    <div class="rg-image-wrapper">
        {{if itemsCount > 1}}
        <div class="rg-image-nav">
            <a href="#" class="rg-image-nav-prev">Previous Image</a>
            <a href="#" class="rg-image-nav-next">Next Image</a>
        </div>
        {{/if}}
        <div class="rg-image"></div>
        <div class="rg-loading"></div>
        <div class="rg-caption-wrapper">
            <div class="rg-caption" style="display:none;">
                <p id="photo_tags"></p>
                <p id="photo_description"></p>
            </div>
        </div>
    </div>
</script>
<script>
   function takePhotoId() {
       var str = $('div.rg-image img').attr("src").substr(-41);
       str = str.replace('/','');
       $('#photoId1').val(str);
       $('#photoId2').val(str);
   }
</script>

<form class="center_button" id="sendPhoto" method="POST" enctype="multipart/form-data" action="<c:url value="/myPhoto/save"/>">
   <input required="required" accept="image/x-png, image/gif, image/jpeg, image/jpg"
      type="file" name="newPhotos[]" id="file-picker" class="btn file-input-button" multiple
      value="Загрузить изображения"/>
   <input type="submit" id="submit" class="btn" value="Ok">
   <div id="info"></div>
   <p class="text-error">${uploadError}</p>
</form>
<div id="rg-gallery" class="rg-gallery">
    <div class="rg-thumbs">
        <!-- Elastislide Carousel Thumbnail Viewer -->
        <div class="es-carousel-wrapper">
            <div class="es-nav">
                <span class="es-nav-prev">Previous</span>
                <span class="es-nav-next">Next</span>
            </div>
            <div class="es-carousel">
                <ul>
                    <c:forEach items="${photoList}" var="photos">
                    <li><a href="#"><img src="<c:url value="/photo/${photos.userId}/miniphotos/${photos.photoId}"/>" data-large="<c:url value="/photo/${photos.userId}/photos/${photos.photoId}"/>" alt="image"
                        data-tags="<c:url value="${photos.tags}"/>" data-description="${photos.description}" /></a></li>
                    </c:forEach>
                </ul>
                <p class="text-center text-info" id="nophotos"></p>
            </div>
        </div>
        <!-- End Elastislide Carousel Thumbnail Viewer -->
    </div><!-- rg-thumbs -->
</div><!-- rg-gallery -->
<form:form method="post" action="/portal/myPhoto/deletePhoto" modelAttribute="updatePhotoDTO">
 <div id="edit_photo_buttons" class="btn-group center_button">
   <a id="desc_edit" class="btn btn-inverse">
     <i class="icon-edit icon-white"></i>
     <span>Редактировать</span>
   </a>
   <a class="btn btn-inverse">
     <i class="icon-download-alt icon-white"></i>
     <span>Загрузить оригинал</span>
   </a>
   <form:input type="hidden" path="photoId" id="photoId1" value="" />
   <button class="btn btn-inverse" type="submit" onclick="takePhotoId()">
      <i class="icon-trash icon-white"></i>
      <span>Удалить</span>
   </button>
 </div>
</form:form>
<div id="edit_photo">
   <form:form method="post" action="/portal/myPhoto/updatePhoto" modelAttribute="updatePhotoDTO" class="form-horizontal">
      <dl class="dl-horizontal">
          <dt>Теги:</dt>
          <dd><form:textarea id="edit_photo_area1" path="tags" /></dd>
      </dl>
      <dl class="dl-horizontal">
          <dt>Описание:</dt>
          <dd><form:textarea id="edit_photo_area2" path="description" /></dd>
      </dl>
      <div class="btn-group center_button">
         <a id="clear_fields" class="btn btn-inverse">
           <i class="icon-remove icon-white"></i>
           <span>Очистить</span>
         </a>
         <button class="btn btn-inverse" type="submit" onclick="takePhotoId()">
           <i class="icon-hand-right icon-white"></i>
           <span>Сохранить</span>
         </button>
      </div>
      <form:input type="hidden" path="photoId" id="photoId2" value="" />
   </form:form>
</div>



