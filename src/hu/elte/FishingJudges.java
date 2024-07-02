package hu.elte;

public class FishingJudges {

	static final Judge<FisherMan, Fish> POINT_JUDGE = new Judge<FisherMan, Fish>(
			(fisherman) -> fisherman.streamCatches(),
			(fish) -> fish.isValid(),
			(fish) -> fish.getSpecies().getPoints()
			);
	static final Judge<FisherMan, Fish> COUNT_JUDGE = new Judge<FisherMan, Fish>(
			(fisherman) -> fisherman.streamCatches(),
			(fish) -> true,
			(fish) -> 1
			);
}
