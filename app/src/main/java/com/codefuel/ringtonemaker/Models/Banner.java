package com.codefuel.ringtonemaker.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banner {

@SerializedName("id")
@Expose
private String id;
@SerializedName("date_time")
@Expose
private String dateTime;
@SerializedName("image")
@Expose
private String image;
@SerializedName("status")
@Expose
private String status;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getDateTime() {
return dateTime;
}

public void setDateTime(String dateTime) {
this.dateTime = dateTime;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}