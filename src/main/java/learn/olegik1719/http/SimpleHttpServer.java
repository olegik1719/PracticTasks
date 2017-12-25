package learn.olegik1719.http;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8765), 0);

        //HttpContext context = server.createContext("/", new EchoHandler());
        HttpContext context = server.createContext("/", SimpleHttpServer::handle);
        context.setAuthenticator(new Auth());

        server.setExecutor(null);
        server.start();
    }

    private static void handle(HttpExchange exchange) throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.append("<h1>URI: ").append(exchange.getRequestURI()).append("</h1>");

        Headers headers = exchange.getRequestHeaders();
        for (String header : headers.keySet()) {
            builder.append("<p>").append(header).append("=")
                    .append(headers.getFirst(header)).append("</p>");
        }

        byte[] bytes = builder.toString().getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    static class Auth extends Authenticator {
        @Override
        public Result authenticate(HttpExchange httpExchange) {
            if ("/forbidden".equals(httpExchange.getRequestURI().toString()))
                return new Failure(403);
            else
                return new Success(new HttpPrincipal("c0nst", "realm"));
        }
    }
}