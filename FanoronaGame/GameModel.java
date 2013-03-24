public class GameModel implements ActionListener {
	
	int width, height;

	public enum PieceState {
		BLACK, WHITE, EMPTY
	}

	PieceState[][] container; 

	public GameModel(int x, int y) {
		width = x;
		height = y;
	}
	
	public movePiece(pair<int, int> source)
}