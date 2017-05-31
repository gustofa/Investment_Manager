package groupone.java.investment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IndicatorList {

		public static List<Indicator> indicatorsList = new ArrayList<Indicator>();
	
		public static Indicator findIndicator(String name) {
		    Optional<Indicator> indicator = indicatorsList.stream()
		        .filter(p -> p.getName().equals(name))
		        .findFirst();
		    return indicator.isPresent() ? indicator.get() : null;
		}		
}