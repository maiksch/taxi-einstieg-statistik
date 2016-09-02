package edu.tuc.taxieinstiegstatistik.datenbank;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import java.util.Date;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class DatenbankAdapterTest {

	@Test
	public void testCoordinateQuery() {
		DatenbankAdapter datenbankAdapter = DatenbankAdapter.getInstance();

		List<SimpleEntry<String, Date>> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");

		for (SimpleEntry<String, Date> list : objects)
			System.out.println(list.getKey() + "  " + list.getValue());

		Assert.assertTrue(!objects.isEmpty());
	}

}