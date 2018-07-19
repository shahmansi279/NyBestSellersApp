package project.com.nybestsellerbooksapp.model;

/**
 * Created by Mansi on 1/22/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BSHistoryList {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<BSHistoryBookItem> results  = null;



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

    public List<BSHistoryBookItem> getResults() {
        return results;
    }

    public void setResults(List<BSHistoryBookItem> results) {
        this.results = results;
    }


    public BSHistoryList(){

}
    public class BSHistoryBookItem {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("contributor")
        @Expose
        private String contributor;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("contributor_note")
        @Expose
        private String contributorNote;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("age_group")
        @Expose
        private String ageGroup;
        @SerializedName("publisher")
        @Expose
        private String publisher;
        @SerializedName("isbns")
        @Expose
        private List<BSBookItemIsbn> isbns = null;
        @SerializedName("ranks_history")
        @Expose
        private List<BSRanksHistory> ranksHistory = null;
        @SerializedName("reviews")
        @Expose
        private List<BSBookReview> reviews = null;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContributor() {
            return contributor;
        }

        public void setContributor(String contributor) {
            this.contributor = contributor;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContributorNote() {
            return contributorNote;
        }

        public void setContributorNote(String contributorNote) {
            this.contributorNote = contributorNote;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getAgeGroup() {
            return ageGroup;
        }

        public void setAgeGroup(String ageGroup) {
            this.ageGroup = ageGroup;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public List<BSBookItemIsbn> getIsbns() {
            return isbns;
        }

        public void setIsbns(List<BSBookItemIsbn> isbns) {
            this.isbns = isbns;
        }

        public List<BSRanksHistory> getRanksHistory() {
            return ranksHistory;
        }

        public void setRanksHistory(List<BSRanksHistory> ranksHistory) {
            this.ranksHistory = ranksHistory;
        }

        public List<BSBookReview> getReviews() {
            return reviews;
        }

        public void setReviews(List<BSBookReview> reviews) {
            this.reviews = reviews;
        }

    }

}