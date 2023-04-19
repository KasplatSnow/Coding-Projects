/**
 * The MinMax class implements AI and defines an AI that uses the Minimax algorithm 
 * to figure out what moves to make.  This is a very complex class.
 * 
 * The makeMove(Board b) method takes a board, generates possible boards based on the 
 * possible moves of the AI, and has those boards evaluated by the evaluatePosition function.
 * The board that scores the highest becomes the next move, and the doMove(Board b, Move moveToMake)
 * method is invoked, which performs that move on the board.
 * 
 * The evaluatePosition function is recursive and generates a game tree which it searches to figure
 * out how advantageous a board it is.  It uses the MinMax algorithm with alpha-beta pruning to 
 * rate moves, and when a node at the depth specified by the constant DEPTH is reached,
 * the evaluate function is called to determine the ultimate worth of that board. This is
 * then propagated up through the game tree to determine how advantageous a move is.  
 * 
 * Note that all AI players are black
 */

import java.util.*;
import java.util.zip.Inflater;

import javax.annotation.processing.Generated;

import java.awt.*;
import java.lang.invoke.ConstantBootstraps;
	
public class MinMax implements AI {
	private static final int DEPTH = 2;
	//Keeps track of the turns on the board currently being expanded
	private int numTurns;
	private static final int INF = 99999999;
	/**
	 * The makeMove(Board b) method takes a board as a parameter and generates all the possible AI moves
	 * (note again that the AI is always black). It then calls the evaluatePosition function on each
	 * possible board to figure out the best move.  
	 * 
	 * 
	 * @return a string describing the move that was made
	 * 
	 * The string is a text description of the move, made by doMove
	 */
	@Override
	public String makeMove(Board b) {
		Move bestMove; //keeps track of the best possible move AI has available
		int bestMoveScore; //score of that best move
		// TODO Auto-generated method stub
		ArrayList<Board> possibleBoards = generatePossibleBoardsAndMoves(b, true); //keeps track of the possible boards (boards with the possible moves made on them)
		ArrayList<Move> moves  = generatePossibleBoardsAndMoves(b, false); //keeps track of all possible moves 

		//initializes bestMove to the first move in the moves ArrayList.
		bestMove = moves.get(0);
		//determines the scoring of the initialized best move
		bestMoveScore = evaluatePosition(possibleBoards.get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, true);
		
		//call evaluateposition on each move
		//keep track of the move with the best score
		if(numTurns>0){
			for(int i = 1; i<possibleBoards.size(); i++){
				System.out.println("Evaluating move: " + moves.get(i).toString());
				/*
				 * calls evaluatePosition on each possible board and if the score is higher than previous,
				 * reset the bestMove
				 */
				int j = evaluatePosition(possibleBoards.get(i), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, true);
				if(j >= bestMoveScore){
					bestMove = moves.get(i);
					bestMoveScore = j;
				}
	
			}
		}
		else{
			//Randomly picks a move from the legal options in moves ArrayList on the first turn
			Random generator = new Random();
			int index = generator.nextInt(moves.size());
			bestMove = moves.get(index);
		}
		//prints out determined bestMove
		System.out.println(bestMove.toString());
		//Turn counter increased so the moves after turn 0, are branching and not a random pick
		numTurns++;
		return doMove(b, bestMove); //doMove performs the move on the original board and returns a string of that move

		
	}

	public ArrayList generatePossibleBoardsAndMoves(Board b, boolean boardRequest) {
		//generate all legal moves
	
				ArrayList<Board> possibleBoards = new ArrayList<Board>(); //keeps track of the possible boards (boards with the possible moves made on them)
				ArrayList<Move> moves  = new ArrayList<Move>(); //keeps track of all possible moves 
				
				/*
				 * iterates through board, generates all possible moves and saves them in moves
				 */
				//1st and 2nd for-loop create variables to check each square of the board
				for(int i = 0; i<8; i++){
					for(int j=0; j<8; j++){
						//checks if square on the board has a piece
						if(b.hasPiece(i,j)){
							//calls getSquare to determine which piece on that square of (i,j) and assigns it to Piece piece
							Piece piece = b.getSquare(i,j);
							//if piece color that of computer(black) then true
							if(piece.getColor() == true){
								//Next 2 for-loops loop through the squares/positions on the board that piece can possibly legally move to
								for(int k=0; k<8; k++){
									for(int l=0; l<8; l++){
										//checks legality of move?
										if(piece.checkLegalMove(new Point(k*62,l*62),b)){ //k and l and multiplied by 62 because checkLegalMove takes the pixel positions as parameters
											//creates a new move that holds the new y, l and the new x, k; and this info is then recorded an object move
											Move move = new Move(i,j,k,l,piece);
											//its added to the ArrayList moves
											moves.add(move);
											//copies board so new next move can be created
											Board newBoard = new Board(b); //calls the copy constructor of the board class
											doMove(newBoard, move); //performs move on the new board
											possibleBoards.add(newBoard);
										}
									}
								}
							}
						}
					}
				}
				if(boardRequest == true) {
					return possibleBoards; 
				}
				return moves;
	}
	
	/**
	 *  The doMove(Board b, Move moveToMake) performs the Move moveToMake on the board provided in the 
	 *  parameters. It returns a string describing the move that was made.  
	 *  
	 *  Plays pieces on possibleBoards in possibleBoardsAndMoves
	 *  Plays the output of best move on the original board in makeMove
	 *  
	 * @param b
	 * @param moveToMake
	 * @return string describing what kind of move was made
	 */
	public String doMove(Board b, Move moveToMake){
		final String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"}; //used to print the row as a letter instead of a number
		Piece pieceToMove = moveToMake.getPiece();
		
		
		
		
		
		/*
		 * Check if castling took place
		 */
		if(pieceToMove.getType().equals("King")){
			if(pieceToMove.getColor() == false){//case that it is white king
				if(b.hasPiece(7, 7)){
					if(b.getSquare(7,7).getType().equals("Rook")){
						if(moveToMake.getOldX() == 4 && moveToMake.getOldY() == 7 && moveToMake.getNewX() == 6 && moveToMake.getNewY() ==7){
							//if conditions for castling are correct, moves the correct pieces
							b.clearSquare(moveToMake.getOldX(), moveToMake.getOldY());
							b.setSquare(moveToMake.getNewX(), moveToMake.getNewY(), pieceToMove);
							pieceToMove.setLocation(moveToMake.getNewX(), moveToMake.getNewY());
							Piece rook = b.getSquare(7, 7);
							b.setSquare(5,7,rook);
							rook.setLocation(5, 7);
							b.clearSquare(7, 7);
							String text = "White castles. \n";
							return text;
						}
					}
				}
			}else if(pieceToMove.getColor() == true){ //case that it is black king
				if(b.hasPiece(7, 0)){
					if(b.getSquare(7, 0).getType().equals("Rook")){
						if(moveToMake.getOldX() == 4 && moveToMake.getOldY() == 0 && moveToMake.getNewX() == 6 && moveToMake.getNewY() ==0){
							//if conditions for castling are correct, moves the correct pieces
							b.clearSquare(moveToMake.getOldX(), moveToMake.getOldY());
							b.setSquare(moveToMake.getNewX(), moveToMake.getNewY(), pieceToMove);
							pieceToMove.setLocation(moveToMake.getNewX(), moveToMake.getNewY());
							Piece rook = b.getSquare(7, 0);
							b.setSquare(5, 0, rook);
							rook.setLocation(5, 0);
							b.clearSquare(7, 0);
							String text = "Black castles. \n";
							return text;
						}
					}
				}
			}
		}
		
		
		
		
		//clear square and reset the new square with the piece to be moved
		b.clearSquare(moveToMake.getOldX(), moveToMake.getOldY());
		b.setSquare(moveToMake.getNewX(), moveToMake.getNewY(), pieceToMove);
		pieceToMove.setLocation(moveToMake.getNewX(), moveToMake.getNewY());
		
		/*
		 * Figures out what text to send back
		 */
		String text = pieceToMove.getType() + " was moved to: " + columns[moveToMake.getNewX()] +  (moveToMake.getNewY()+1) + "\n";
		return text;
	}
	
	/**
	 * The evaluatePosition function takes a board, initial alpha, initial beta, depth, and color as parameters
	 * and computes a number that describes how advantageous for the AI a particular board is.  The function is 
	 * recursive, and every time it evaluates itself it decreases the depth by 1.  When the depth reaches 0, the
	 * function returns the result of running the evaluate function on the board.  If the depth is not 0, the function
	 * generates all possible moves from that position for the color specified, and then runs evaluatePosition for 
	 * each of the boards generated by each possible move. 
	 * 
	 * Tests the current given board's future moves, via creating a tree of future node(moves) which each branch a board,
	 * and will repeat this growth until the non-zero depth has reached 0. 
	 * Current the depth is 1, so only second layer of possible board positions will be made, essentially giving only the next best move.
	 * Depth 2 will go beyond the next move and determine the future board with more opportunistic values.
	 * 
	 *
	 * @param b
	 * @param alpha
	 * @param beta 
	 * @param depth
	 * @param color
	 * @return an int giving a score of how good a particular board is, with higher numbers corresponding to better boards for the AI
	 */
	
	/*
	 * My attempt
	 * 
	 */
	
	
	public int evaluatePosition(Board b, int alpha, int beta, int depth, boolean color) {
		if(depth == 0) {
			return evaluate(b);
		}
		
		//determines max for black
		if(color = true) {
			int max = -INF;
			ArrayList<Board> childBoards = generatePossibleBoardsAndMoves(b, true);
			for(int i = 0; i < childBoards.size(); i++) {
				int evaluated = evaluatePosition(childBoards.get(i), alpha, beta, depth - 1, false);
				max = Math.max(max, evaluated);
				alpha = Math.max(alpha, evaluated);
				if(beta <= alpha) {
					break;
				}
			}
			return max;
		}
		
		//determines min for white
		if(color = false) {
			int min = INF;
			ArrayList<Board> childBoards = generatePossibleBoardsAndMoves(b, true);
			for(int i = 0; i < childBoards.size(); i++) {
				int evaluated = evaluatePosition(childBoards.get(i), alpha, beta, depth - 1, true);
				min = Math.min(min, evaluated);
				beta = Math.min(beta, evaluated);
				if(beta <= alpha) {
					break;
				}
			}
			return min;
		}
		
		return 0;
		
	}
	/*FROM HERE
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * TO HERE
	 */
	
	/**
	 * The evaluate(Board b) function is an evaluation function that returns a number based on
	 * how advantageous a board is for the maximizing, black in this case, player. This function
	 * simply iterates through the whole board and gives a weighted number to each piece on the board,
	 * kings naturally yielding the highest number, queens the second, and so on.  The total white score
	 * is subtracted from the total black score to give a full picture of how advantageous the board is 
	 * for a black player.  
	 * @param b
	 * @return int that represents how advantageous a board is
	 */
	public int evaluate(Board b){
		int whitescore = 0;
		int blackscore = 0;

		/*
		 * Iterates through entire board.   
		 */
		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.hasPiece(i, j)){
					if(b.getSquare(i, j).getColor() == false){ //case that piece is white
						if(b.getSquare(i,j).getType() == "Queen"){
							whitescore += 9;
						}else if(b.getSquare(i,j).getType() == "Rook"){
							whitescore += 5;
						}else if(b.getSquare(i,j).getType() == "Knight" || b.getSquare(i,j).getType() == "Bishop"){
							whitescore += 3;
						}else if(b.getSquare(i,j).getType() == "Pawn"){
							whitescore += 1;
						}else if(b.getSquare(i,j).getType() == "King"){
							whitescore += 10000000;
						}
					}else if(b.getSquare(i,j).getColor() == true){ //case that piece is black
						if(b.getSquare(i,j).getType() == "Queen"){
							blackscore += 9;
						}else if(b.getSquare(i,j).getType() == "Rook"){
							blackscore += 5;
						}else if(b.getSquare(i,j).getType() == "Knight" || b.getSquare(i,j).getType() == "Bishop"){
							blackscore += 3;
						}else if(b.getSquare(i,j).getType() == "Pawn"){
							blackscore += 1;
						}else if(b.getSquare(i,j).getType() == "King"){
							blackscore += 10000000;
						}
					}
				}
			}
		}
		return blackscore-whitescore; //returns blackscore-whitescore, black player tries to maximize, white player tries to minimize
	}

}
