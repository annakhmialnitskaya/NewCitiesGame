package by.htp.newGame;

public enum NewGameResult {

	IN_PROGRESS {
		@Override
		public String toString() {
			return "The game is in progress";
		}
	},
	USER_WINS {
		@Override
		public String toString() {
			return "The user wins!";
		}
	},
	COMP_WINS {
		@Override
		public String toString() {
			return "The computer wins!";
		}
	}
}
