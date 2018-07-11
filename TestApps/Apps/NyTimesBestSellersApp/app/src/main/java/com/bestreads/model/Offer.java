
package com.bestreads.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer implements Parcelable
{

    @SerializedName("finskyOfferType")
    @Expose
    private Integer finskyOfferType;
    @SerializedName("listPrice")
    @Expose
    private ListPrice_ listPrice;
    @SerializedName("retailPrice")
    @Expose
    private RetailPrice_ retailPrice;
    @SerializedName("giftable")
    @Expose
    private Boolean giftable;
    public final static Parcelable.Creator<Offer> CREATOR = new Creator<Offer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        public Offer[] newArray(int size) {
            return (new Offer[size]);
        }

    }
    ;

    protected Offer(Parcel in) {
        this.finskyOfferType = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.listPrice = ((ListPrice_) in.readValue((ListPrice_.class.getClassLoader())));
        this.retailPrice = ((RetailPrice_) in.readValue((RetailPrice_.class.getClassLoader())));
        this.giftable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public Offer() {
    }

    public Integer getFinskyOfferType() {
        return finskyOfferType;
    }

    public void setFinskyOfferType(Integer finskyOfferType) {
        this.finskyOfferType = finskyOfferType;
    }

    public ListPrice_ getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice_ listPrice) {
        this.listPrice = listPrice;
    }

    public RetailPrice_ getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice_ retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Boolean getGiftable() {
        return giftable;
    }

    public void setGiftable(Boolean giftable) {
        this.giftable = giftable;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(finskyOfferType);
        dest.writeValue(listPrice);
        dest.writeValue(retailPrice);
        dest.writeValue(giftable);
    }

    public int describeContents() {
        return  0;
    }

}
