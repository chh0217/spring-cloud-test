package ch.hello;

/**
 * @author chenhang
 * @date 2018/7/12 下午7:03
 * @description
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
