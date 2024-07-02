package hu.elte;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Judge<T1, T2> {
	
	private Function<T1, Stream<T2>> toStream;
	private Predicate<T2> isValid;
	private ToIntFunction<T2> toValue;
	

	public Judge(Function<T1, Stream<T2>> toStream,
			Predicate<T2> isValid, 
			ToIntFunction<T2> toValue){
		this.toStream = toStream;
		this.isValid = isValid;
		this.toValue = toValue;
	}
	
	private int judgeParticipant(T1 t1) {
		return toStream.apply(t1)
				.filter(elem -> isValid.test(elem))
				.mapToInt(elem -> toValue.applyAsInt(elem))
				.sum();
	}
	
	public T1 findBestParticipant(List<T1> list) {
		Comparator<T1> t1Comparator = 
				Comparator.comparing(t1 -> { return judgeParticipant(t1); });
		Optional<T1> elem = list.stream().max(t1Comparator);
		if(elem.isEmpty()) {
			throw new NoSuchElementException();
		}
		return elem.get();
	}
}
