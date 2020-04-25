package cn.com.frodo.design.pattern.structual.flyweight.demo;

public class ChesspieceFactory {
    static final Chesspiece WHITE = new ChesspieceFlyweight("白");
    static final Chesspiece BLACK = new ChesspieceFlyweight("黑");

    public static Chesspiece getChesspiece(String color) {
        if (color.equals("白")) {
            return WHITE;
        } else if (color.equals("黑")) {
            return BLACK;
        }
        return null;
    }
}
