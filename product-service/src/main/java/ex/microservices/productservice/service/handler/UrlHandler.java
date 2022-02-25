package ex.microservices.productservice.service.handler;

public class UrlHandler {
    public static String handle(String url, String params, CallsTypes type) {
        //Simple
        switch (type) {
            case ID: return url + "?id=" + params;
            case SKU: return url + "/" + params;
            default: throw new RuntimeException("Can not resolve type of call");
        }
    }
}
