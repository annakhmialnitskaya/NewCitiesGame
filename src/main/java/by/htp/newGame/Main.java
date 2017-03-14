package by.htp.newGame;

import by.htp.newGame.bean.NewGame;
import by.htp.newGame.service.NewGameService;
import by.htp.newGame.service.NewGameServiceException;
import by.htp.newGame.service.impl.NewGameServiceImpl;

public class Main {
	public static void main(String[] args) {
		NewGameService service = new NewGameServiceImpl();
		NewGame game = null;
		try {
			game = service.initializationGame();
		} catch (NewGameServiceException e) {
			System.out.println(e.getMessage());
		}

		NewGameResult result = NewGameResult.IN_PROGRESS;
		do {
			System.out.println("Введите название города:");
			String cityNameUserInput = service.readCityNameUserInput();
			Character firstLetterCityName = service.getFirstChar(cityNameUserInput);
			if ("Q".equals(cityNameUserInput)) {
				result = NewGameResult.COMP_WINS;
			} else {
				if (service.inputWordCorrect(firstLetterCityName, game)) {
					if (service.checkUniqueness(cityNameUserInput, game)) {
						game.getGameSet().add(cityNameUserInput);
						game.setLastLetterLastWord(service.getLastChar(cityNameUserInput));
						String compCity = service.returnCityNameByLastLetterFromMap(game);
						if (compCity != null) {
							game.getGameSet().add(compCity);
							game.setLastLetterLastWord(service.getLastChar(compCity));
							System.out.println(compCity);
						} else {
							result = NewGameResult.USER_WINS;
						}
					} else {
						System.out.println("Слово не уникально!");
					}
				} else {
					System.out.println("Слово не подходит!");
				}
			}
		} while (result.equals(NewGameResult.IN_PROGRESS));
		System.out.println(result);
	}
}
