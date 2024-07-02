package hu.elte;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FishingTest {
	
	@Test
	public void testFishSpeciesIsProtected() {
		assertAll(
				() -> assertFalse(FishSpecies.BREAM.isProtected()),
				() -> assertTrue(FishSpecies.CARP.isProtected()),
				() -> assertTrue(FishSpecies.PIKE.isProtected()),
				() -> assertFalse(FishSpecies.RUDD.isProtected())
				);
	}
	
	@Test
	public void testFishSpeciesCanBeKept() {
		assertAll(
				() -> assertTrue(FishSpecies.BREAM.canBeKept(1)),
				() -> assertTrue(FishSpecies.CARP.canBeKept(30)),
				() -> assertFalse(FishSpecies.CARP.canBeKept(29)),
				() -> assertTrue(FishSpecies.PIKE.canBeKept(40)),
				() -> assertFalse(FishSpecies.PIKE.canBeKept(39)),
				() -> assertTrue(FishSpecies.RUDD.canBeKept(1))
				);
	}
	
	@Test
	public void testFishSpeciesGetPoints() {
		assertAll(
				() -> assertEquals(2, FishSpecies.BREAM.getPoints()),
				() -> assertEquals(5, FishSpecies.CARP.getPoints()),
				() -> assertEquals(10, FishSpecies.PIKE.getPoints()),
				() -> assertEquals(1, FishSpecies.RUDD.getPoints())
				);
	}
	
	@Test
	public void pointJudgeIgnoresNotValidFish() {
		FisherMan fisherman = new FisherMan("János");
		FisherMan fisherman2 = new FisherMan("Marcell");
		
		addBelowMinSizeFish(fisherman);
		addAboveSizeFish(fisherman2);

		assertEquals(fisherman2, FishingJudges.POINT_JUDGE.findBestParticipant(Arrays.asList(fisherman, fisherman2)));
	}
	
	private void addBelowMinSizeFish(FisherMan fisherman) {
		fisherman.addCatch(new Fish(FishSpecies.CARP, 20));
		fisherman.addCatch(new Fish(FishSpecies.CARP, 29));
		fisherman.addCatch(new Fish(FishSpecies.PIKE, 2));
		fisherman.addCatch(new Fish(FishSpecies.PIKE, 39));
	}
	
	private void addAboveSizeFish(FisherMan fisherman) {
		fisherman.addCatch(new Fish(FishSpecies.BREAM, 20));
		fisherman.addCatch(new Fish(FishSpecies.CARP, 35));
	}
	
	@Test
	public void pointJudgeWinnerWithLessFish() {
		FisherMan fisherman = new FisherMan("Józsi");
		FisherMan fisherman2 = new FisherMan("Ferenc");
		
		addAValuableFish(fisherman);
		addLotsOfWorthlessFish(fisherman2);

		assertEquals(fisherman, FishingJudges.POINT_JUDGE.findBestParticipant(Arrays.asList(fisherman, fisherman2)));
	}
	
	private void addAValuableFish(FisherMan fisherman) {
		fisherman.addCatch(new Fish(FishSpecies.PIKE, 50));
	}
	
	private void addLotsOfWorthlessFish(FisherMan fisherman) {
		fisherman.addCatch(new Fish(FishSpecies.RUDD, 2));
		fisherman.addCatch(new Fish(FishSpecies.RUDD, 10));
		fisherman.addCatch(new Fish(FishSpecies.RUDD, 20));
		fisherman.addCatch(new Fish(FishSpecies.BREAM, 2));
	}
	
	@Test
	public void countJudgeChoosesWinner() {
		FisherMan fisherman = new FisherMan("Albert");
		FisherMan fisherman2 = new FisherMan("János");
		FisherMan fisherman3 = new FisherMan("Tamás");
		
		addAValuableFish(fisherman);
		addLotsOfWorthlessFish(fisherman2);
		fisherman2.addCatch(new Fish(FishSpecies.RUDD, 2));
		addLotsOfWorthlessFish(fisherman3);

		assertEquals(fisherman2, FishingJudges.COUNT_JUDGE.findBestParticipant(Arrays.asList(fisherman, fisherman2, fisherman3)));
	}
	
	@Test
	public void JudgesThrowExceptionWhenNoWinner() {
		assertAll(
				() -> {assertThrows
					    (
							NoSuchElementException.class, 
							() -> FishingJudges.POINT_JUDGE.findBestParticipant(new ArrayList<FisherMan>())
					    );
					},
				() -> {assertThrows
				        (
						    NoSuchElementException.class, 
					    	() -> FishingJudges.COUNT_JUDGE.findBestParticipant(new ArrayList<FisherMan>())
				        );
				    }
			    );
	}
	
}
