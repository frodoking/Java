package com.frodo.knowledge.vertx;

import com.frodo.Test;
import com.google.gson.JsonObject;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VertxSeverTest implements Test
{
    private static final Logger LOGGER = LoggerFactory.getLogger(VertxSeverTest.class);

    @Override public void doTest()
    {
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.Log4jLogDelegateFactory");

        VertxOptions options = new VertxOptions();
        Vertx vertx = Vertx.vertx(options);
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        // Create a clustered session store using defaults
        SessionStore store = ClusteredSessionStore.create(vertx);
        SessionHandler sessionHandler = SessionHandler.create(store);

        router.route("/some/1").handler(routingContext -> {
            Cookie cookie = routingContext.getCookie("mycookie");
            if (cookie != null)
            {
                String cookieValue = cookie.getValue();
                LOGGER.info(cookieValue);
            }

            // Add a cookie - this will get written back in the response automatically
            routingContext.addCookie(Cookie.cookie("testCKey", "testCValue"));
            HttpServerResponse response = routingContext.response();

            // enable chunked responses because we will be adding data as
            // we execute over other handlers. This is only required once and
            // only if several handlers do output.
            response.setChunked(true);

            response.write("route1\n");

            // Call the next matching route after a 5 second delay
            routingContext.vertx().setTimer(1000, tid -> routingContext.next());
        }).handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route2\n");

            // Call the next matching route after a 5 second delay
            routingContext.vertx().setTimer(1000, tid -> routingContext.next());
        }).handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route3");

            Cookie cookie = routingContext.getCookie("testCKey");
            if (cookie != null)
            {
                String cookieValue = cookie.getValue();
                LOGGER.info("{}: {}", "testCKey", cookieValue);
            }

            // Now end the response
            routingContext.response().end();
        });

        router.get("/some/2").handler(sessionHandler).handler(ctx -> {
            ctx.request().setExpectMultipart(true);
            ctx.next();
        }).blockingHandler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.write("some 2");
            Session session = ctx.session();

            // Put some data from the session
            session.put("foo", "bar");

            // Retrieve some data from a session
            int age = session.get("age");

            // Remove some data from a session
            JsonObject obj = session.remove("myobj");
            ctx.response().end();
        });
        server.requestHandler(router).listen(8080, ar -> {
            LOGGER.info("start status: {}", ar.succeeded());
            if (!ar.succeeded())
            {
                ar.cause().printStackTrace();
            }
        });
    }
}
