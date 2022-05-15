package cn.dreamchase.android.first.recyclerview;

/**
 * -StaggeredGridLayoutManager
 */
public class ItemData {

    private String content;

    private int height;

    public ItemData(String content,int height) {
        this.content = content;
        this.height = height;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
