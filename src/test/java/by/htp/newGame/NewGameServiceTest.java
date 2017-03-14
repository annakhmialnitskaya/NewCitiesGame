package by.htp.newGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.newGame.bean.NewGame;
import by.htp.newGame.service.NewGameService;
import by.htp.newGame.service.impl.NewGameServiceImpl;

public class NewGameServiceTest {

	private static NewGameService service;
	private static NewGame game;

	@BeforeClass
	public static void setUpClass() {
		service = new NewGameServiceImpl();
		game = new NewGame();
		game.setLastLetterLastWord('А');
	}

	@Before
	public void setUp() {
		Set<String> gameSet = new HashSet<>();
		gameSet.add("Минск");
		gameSet.add("Москва");
		game.setGameSet(gameSet);
	}

	@Test
	public void checkUniquenessTest() {
		assertFalse("This element should be in the set already!", service.checkUniqueness("Минск", game));
		assertTrue("This element should not be in the set!", service.checkUniqueness("Уфа", game));
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
