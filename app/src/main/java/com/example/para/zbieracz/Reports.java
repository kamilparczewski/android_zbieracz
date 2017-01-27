package com.example.para.zbieracz;



import com.orm.SugarRecord;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reports extends SugarRecord implements Serializable {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String firstname= "";
    private String lastname= "";
    private Date date = new Date();
    private String imageUrl;

    public Reports() {

    }

    public Reports(String firstname, String lastname, Date date, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.date = date;
        this.imageUrl = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return firstname + " " + lastname + " " + dateFormat.format(date);
    }


}
