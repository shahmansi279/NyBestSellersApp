
package project.com.nybestsellerbooksapp.svc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GBookDataItems implements Parcelable
{

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    public final static Parcelable.Creator<GBookDataItems> CREATOR = new Creator<GBookDataItems>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GBookDataItems createFromParcel(Parcel in) {
            return new GBookDataItems(in);
        }

        public GBookDataItems[] newArray(int size) {
            return (new GBookDataItems[size]);
        }

    }
    ;

    protected GBookDataItems(Parcel in) {
        this.kind = ((String) in.readValue((String.class.getClassLoader())));
        this.totalItems = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.items, (project.com.nybestsellerbooksapp.svc.model.Item.class.getClassLoader()));
    }

    public GBookDataItems() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kind);
        dest.writeValue(totalItems);
        dest.writeList(items);
    }

    public int describeContents() {
        return  0;
    }

}
