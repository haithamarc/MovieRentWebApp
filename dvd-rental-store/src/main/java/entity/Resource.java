package entity;

public class Resource {

    String name;
    String method;
    String url;

    public Resource() {
    }

    public Resource(String name, String method, String url) {
        this.name = name;
        this.method = method;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return  "{" +
                " name=" + name +
                " method=" + method +
                " url=" + url +
                "}";
    }
}
