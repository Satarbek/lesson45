package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.ResponseCodes;
import kz.attractor.java.server.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Lesson45Server extends Lesson44Server {
    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);

        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/profile", this::profileGet);
        registerPost("/profile", this::profilePost);
        registerGet("/register", this::registerGet);
        registerPost("/register", this::registerPost);

    }

    private void loginPost(HttpExchange exchange) {
        redirect303(exchange, "/login");
    }

    private void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void registerGet(HttpExchange exchange) {
        Path path = makeFilePath("register.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void registerPost(HttpExchange exchange) {
        redirect303(exchange, "/regfail");
    }

    private void profileGet(HttpExchange exchange) {
        Path path = makeFilePath("profile.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void profilePost(HttpExchange exchange) {
//        redirect303(exchange, "/profile");
        String cType = getContentType(exchange);
        String raw = getBody(exchange);

        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");

        String data = String.format("<p>Необработанные данные: <b>%s</b></p>" +
                "<p>Content-Type: <b>%s</b></p>" +
                "<p>После обработки: <b>%s</b></p>", raw, cType, parsed);

        try{
            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

////    private void checkPost(HttpExchange exchange){
////        String cType = getContentType(exchange);
////        String raw = getBody(exchange);
////
////        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
////
////        String data = String.format("<p>Необработанные данные: <b>%s</b></p>" +
////                "<p>Content-Type: <b>%s</b></p>" +
////                "<p>После обработки: <b>%s</b></p>", raw, cType, parsed);
////
////        try{
////            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data.getBytes());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }


}
