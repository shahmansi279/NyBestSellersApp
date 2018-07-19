package project.com.nybestsellerbooksapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BSBookList{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("results")
    @Expose
    private List<BSBookItem> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public List<BSBookItem> getResults() {
        return results;
    }

    public void setResults(List<BSBookItem> results) {
        this.results = results;
    }


    public static class BSBookItem implements Parcelable {

        @SerializedName("list_name")
        @Expose
        private String listName;
        @SerializedName("display_name")
        @Expose
        private String displayName;
        @SerializedName("bestsellers_date")
        @Expose
        private String bestsellersDate;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;
        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("rank_last_week")
        @Expose
        private Integer rankLastWeek;
        @SerializedName("weeks_on_list")
        @Expose
        private Integer weeksOnList;
        @SerializedName("asterisk")
        @Expose
        private Integer asterisk;
        @SerializedName("dagger")
        @Expose
        private Integer dagger;
        @SerializedName("amazon_product_url")
        @Expose
        private String amazonProductUrl;
        @SerializedName("isbns")
        @Expose
        private List<BSBookItemIsbn> isbns = null;
        @SerializedName("book_details")
        @Expose
        private List<BSBookDetailItem> bookDetails = null;
        @SerializedName("reviews")
        @Expose
        private List<BSBookReview> reviews = null;


        public String getListName() {
            return listName;
        }

        public void setListName(String listName) {
            this.listName = listName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getBestsellersDate() {
            return bestsellersDate;
        }

        public void setBestsellersDate(String bestsellersDate) {
            this.bestsellersDate = bestsellersDate;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        public Integer getRankLastWeek() {
            return rankLastWeek;
        }

        public void setRankLastWeek(Integer rankLastWeek) {
            this.rankLastWeek = rankLastWeek;
        }

        public Integer getWeeksOnList() {
            return weeksOnList;
        }

        public void setWeeksOnList(Integer weeksOnList) {
            this.weeksOnList = weeksOnList;
        }

        public Integer getAsterisk() {
            return asterisk;
        }

        public void setAsterisk(Integer asterisk) {
            this.asterisk = asterisk;
        }

        public Integer getDagger() {
            return dagger;
        }

        public void setDagger(Integer dagger) {
            this.dagger = dagger;
        }

        public String getAmazonProductUrl() {
            return amazonProductUrl;
        }

        public void setAmazonProductUrl(String amazonProductUrl) {
            this.amazonProductUrl = amazonProductUrl;
        }

        public List<BSBookItemIsbn> getIsbns() {
            return isbns;
        }

        public void setIsbns(List<BSBookItemIsbn> isbns) {
            this.isbns = isbns;
        }

        public List<BSBookDetailItem> getBookDetails() {
            return bookDetails;
        }

        public void setBookDetails(List<BSBookDetailItem> bookDetails) {
            this.bookDetails = bookDetails;
        }

        public List<BSBookReview> getReviews() {
            return reviews;
        }

        public void setReviews(List<BSBookReview> reviews) {
            this.reviews = reviews;
        }

        public BSBookItem(Parcel source) {


            this.amazonProductUrl=
                    source.readString();

            this.bookDetails = new ArrayList<BSBookDetailItem>();
            source.readTypedList(this.bookDetails, BSBookDetailItem.CREATOR);


                    //source.readParcelable(BSBookDetailItem.class.getClassLoader());


        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

            dest.writeString(amazonProductUrl);
            dest.writeTypedList(bookDetails);
        }

        public static final Creator<BSBookItem> CREATOR = new Creator<BSBookItem>() {

            @Override
            public BSBookItem[] newArray(int size) {
                return new BSBookItem[size];
            }

            @Override
            public BSBookItem createFromParcel(Parcel source) {
                return new BSBookItem(source);
            }
        };
    }
}