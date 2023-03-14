package example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

public class TriangleTest {
	List<Triangle> triangles;

	public TriangleTest() throws TriangleException {
		triangles = Arrays.asList(new Triangle(2, 2, 2), new Triangle(3, 2, 2), new Triangle(3, 4, 5));
	}

	@Test
	@DisplayName("Should be an equilateral triangle")
	public void shouldBeAnEquilateralTriangle() throws Exception {

		assertEquals(TriangleKind.EQUILATERAL, triangles.get(0).getKind());
	}

	@Test
	@DisplayName("Should be an isosceles triangle")
	public void shouldBeAnIsoscelesTriangle() throws Exception {
		assertEquals(TriangleKind.ISOSCELES, triangles.get(1).getKind());
	}

	@Test
    @DisplayName("Should be a scalene triangle")
    public void shouldBeAnScaleneTriangle() throws Exception { 
        assertEquals(TriangleKind.SCALENE, triangles.get(2).getKind()); 
    }

	@Test
	@DisplayName("Should throw an exception for illegal value")
	public void shouldThrowExceptionForIllegalValue() throws Exception {
		assertThrows(TriangleException.class, () -> new Triangle(-3, 4, 5));
		assertThrows(TriangleException.class, () -> new Triangle(3, -4, 5));
		assertThrows(TriangleException.class, () -> new Triangle(3, 4, -5));
	}

	@Test
	@DisplayName("Should throw an exception for all sides be equal zero")
	public void shouldThrowExceptionAllZeroSides() throws Exception {
		assertThrows(TriangleException.class, () -> new Triangle(0, 0, 0));
	}

	@Test
	@DisplayName("Should throw an exception for violates triangle inequality")
	public void shouldThrowExceptionViolatesTriangleInequality() throws Exception {
		assertThrows(TriangleException.class, () -> new Triangle(2, 1, 1));
		assertThrows(TriangleException.class, () -> new Triangle(1, 2, 1));
		assertThrows(TriangleException.class, () -> new Triangle(1, 1, 2));
	}

	@RepeatedTest(value = 3, name = "test number of unique {currentRepetition} side")
	@DisplayName("Should throw an exception for Invalid number of unique sides")
	public void shouldThrowExceptionNumberOfUniqueSides(RepetitionInfo repetitionInfo) throws Exception {
		int index = repetitionInfo.getCurrentRepetition();
		assertEquals(index, triangles.get(index - 1).getNumberOfUniqueSides());
	}
}