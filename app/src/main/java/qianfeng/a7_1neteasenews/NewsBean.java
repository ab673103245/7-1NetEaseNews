package qianfeng.a7_1neteasenews;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class NewsBean {

    private String title;
    private String imgsrc;
    private String[] imgextra;
    private int type;
//    private String[] titles;

    public NewsBean(String title, String imgsrc, String[] imgextra, int type) {
        this.title = title;
        this.imgsrc = imgsrc;
        this.imgextra = imgextra;
        this.type = type;
    }

//    public String[] getTitles() {
//        return titles;
//    }
//
//    public void setTitles(String[] titles) {
//        this.titles = titles;
//    }

//    public NewsBean(String title, String imgsrc, String[] imgextra, int type, String[] titles) {
//        this.title = title;
//        this.imgsrc = imgsrc;
//        this.imgextra = imgextra;
//        this.type = type;
////        this.titles = titles;
//    }

    public NewsBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String[] getImgextra() {
        return imgextra;
    }

    public void setImgextra(String[] imgextra) {
        this.imgextra = imgextra;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
