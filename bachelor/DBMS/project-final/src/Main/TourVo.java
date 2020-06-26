package Main;

/**
 * Created by pmw90 on 2015-11-26.
 */
public class TourVo {
    private String adrres;
    private String image;
    private String title;
    private String contentID;
    private String overview;
    private String homepage;
    private String tel;

    public TourVo() {
    }

    public TourVo(String adrres, String image, String title, String contentID, String overview, String homepage, String tel) {
        this.adrres = adrres;
        this.image = image;
        this.title = title;
        this.contentID = contentID;
        this.overview = overview;
        this.homepage = homepage;
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public TourVo(String title, String contentID) {
        this.title = title;
        this.contentID = contentID;
    }

    public String getAdrres() {
        return adrres;
    }

    public void setAdrres(String adrres) {
        this.adrres = adrres;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}