package by.htp.newGame;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.newGame.bean.NewGame;
import by.htp.newGame.service.NewGameService;
import by.htp.newGame.service.impl.NewGameServiceImpl;

public class NewGameServiceTestNG {
	private static NewGameService service;
	private static NewGame game;

	@BeforeClass
	public void setUpClass() {
		service = new NewGameServiceImpl();
		game = new NewGame();
		game.setLastLetterLastWord('А');
	}

	@BeforeMethod
	public void setUp() {
		Set<String> gameSet = new HashSet<>();
		gameSet.add("Минск");
		gameSet.add("Москва");
		game.setGameSet(gameSet);
	}

	@Test
	public void checkUniquenessTest() {
		assertFalse(service.checkUniqueness("Минск", game), "This element should be in the set already!");
		assertTrue(service.checkUniqueness("Уфа", game), "This element should not be in the set!");
	}

	@Test
	public void inputWordCorrectTest() {
		assertFalse(service.inputWordCorrect('М', game));
		assertTrue(service.inputWordCorrect('А', game));
		game.setGameSet(new HashSet<>());
		assertTrue(service.inputWordCorrect('V', game));
	}

	@Test
	public void getFirstCharTest() {
		assertEquals(new Character('Р'), service.getFirstChar("Рим"));
		assertNotEquals(new Character('F'), service.getFirstChar("Рим"));
	}

	@Test
	public void getLastCharTest() {
		assertEquals(new Character('М'), service.getLastChar("Рим"));
		assertEquals(new Character('Н'), service.getLastChar("Афины"));
		assertEquals(new Character('И'), service.getLastChar("Алтай"));
		assertEquals(new Character('М'), service.getLastChar("Римь"));
		assertEquals(new Character('М'), service.getLastChar("Римъ"));
	}
}
