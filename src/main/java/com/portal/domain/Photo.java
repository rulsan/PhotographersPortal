package com.portal.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Object Photo
 *
 * @author ruslan =^.^=
 */
@Entity
@Table(name = "photo")
public class Photo implements Serializable {

    @Id
    @Column(name = "photo_id")
    private String photoId;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "tags")
    private String tags;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }      

    public Date getUploadDate() {
        if (uploadDate != null) {
            return new Date(uploadDate.getTime());
        } else {
            return null;
        }
    }
    
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    @PrePersist
    public void onCreatePhoto() {
        setUploadDate(new Date());
    }
}
