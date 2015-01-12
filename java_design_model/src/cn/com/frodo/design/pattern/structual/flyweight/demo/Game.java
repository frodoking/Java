package cn.com.frodo.design.pattern.structual.flyweight.demo;

public class Game {

	public static void main(String[] args) {
		Chesspiece white = ChesspieceFactory.getChesspiece("黑");
		white.put(1, 1);
		Chesspiece black = ChesspieceFactory.getChesspiece("白");
		black.put(2, 2);
		
		white.put(3, 2);
		black.put(6, 8);
	}
}
