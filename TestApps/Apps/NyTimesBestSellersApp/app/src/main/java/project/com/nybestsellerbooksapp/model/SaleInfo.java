
package project.com.nybestsellerbooksapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SaleInfo implements Parcelable
{

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("saleability")
    @Expose
    private String saleability;
    @SerializedName("isEbook")
    @Expose
    private Boolean isEbook;
    @SerializedName("listPrice")
    @Expose
    private ListPrice listPrice;
    @SerializedName("retailPrice")
    @Expose
    private RetailPrice retailPrice;
    @SerializedName("buyLink")
    @Expose
    private String buyLink;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;
    public final static Parcelable.Creator<SaleInfo> CREATOR = new Creator<SaleInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SaleInfo createFromParcel(Parcel in) {
            return new SaleInfo(in);
        }

        public SaleInfo[] newArray(int size) {
            return (new SaleInfo[size]);
        }

    }
    ;

    protected SaleInfo(Parcel in) {
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.saleability = ((String) in.readValue((String.class.getClassLoader())));
        this.isEbook = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.listPrice = ((ListPrice) in.readValue((ListPrice.class.getClassLoader())));
        this.retailPrice = ((RetailPrice) in.readValue((RetailPrice.class.getClassLoader())));
        this.buyLink = ((String) in.readValue((String.class.getClassLoader())));
        this.offers = new ArrayList<>();
        in.readList(this.offers, (project.com.nybestsellerbooksapp.model.Offer.class.getClassLoader()));
    }

    public SaleInfo() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public Boolean getIsEbook() {
        return isEbook;
    }

    public void setIsEbook(Boolean isEbook) {
        this.isEbook = isEbook;
    }

    public ListPrice getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice listPrice) {
        this.listPrice = listPrice;
    }

    public RetailPrice getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(country);
        dest.writeValue(saleability);
        dest.writeValue(isEbook);
        dest.writeValue(listPrice);
        dest.writeValue(retailPrice);
        dest.writeValue(buyLink);
        dest.writeList(offers);
    }

    public int describeContents() {
        return  0;
    }

}
