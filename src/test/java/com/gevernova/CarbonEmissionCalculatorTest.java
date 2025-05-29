package com.gevernova;

import com.gevernova.carbonemissioncalculator.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

class CarbonEmissionCalculatorTest {
    private HybridPlant testPlant;
    private SolarUnit solarUnit;
    private GasUnit gasUnit;
    private WindUnit windUnit;

    @BeforeEach
    void setUp() {
        testPlant = new HybridPlant("Test Plant");

        solarUnit = new SolarUnit("Test Solar", 5000, 18.5);
        gasUnit = new GasUnit("Test Gas", 10000, 45.0);
        windUnit = new WindUnit("Test Wind", 4000, 120.0);

        testPlant.addPowerUnit(solarUnit);
        testPlant.addPowerUnit(gasUnit);
        testPlant.addPowerUnit(windUnit);
    }

    @Test
    @DisplayName("Test PowerUnit creation and basic properties")
    void testPowerUnitCreation() {
        assertEquals("Test Solar", solarUnit.getName());
        assertEquals(5000, solarUnit.getCapacity(), 0.001);
        assertEquals(FuelType.SOLAR, solarUnit.getFuelType());
        assertEquals(0, solarUnit.getProduction(), 0.001);
    }

    @Test
    @DisplayName("Test SolarUnit specific properties")
    void testSolarUnitDetails() {
        assertEquals("Solar Unit (Efficiency: 18.5%)", solarUnit.getUnitDetails());
    }

    @Test
    @DisplayName("Test GasUnit specific properties")
    void testGasUnitDetails() {
        assertEquals("Gas Unit (Combustion Efficiency: 45.0%)", gasUnit.getUnitDetails());
    }

    @Test
    @DisplayName("Test WindUnit specific properties")
    void testWindUnitDetails() {
        assertEquals("Wind Unit (Turbine Diameter: 120.0 m)", windUnit.getUnitDetails());
    }

    @Test
    @DisplayName("Test setting valid production values")
    void testSetValidProduction() throws NegativeEmissionException {
        solarUnit.setProduction(1000);
        assertEquals(1000, solarUnit.getProduction(), 0.001);
    }

    @Test
    @DisplayName("Test setting negative production throws exception")
    void testSetNegativeProduction() {
        assertThrows(NegativeEmissionException.class, () -> {
            gasUnit.setProduction(-100);
        });
    }

    @Test
    @DisplayName("Test emission calculation for solar unit")
    void testSolarEmissionCalculation() throws NegativeEmissionException {
        solarUnit.setProduction(1000);
        assertEquals(0, solarUnit.calculateEmissions(), 0.001);
    }

    @Test
    @DisplayName("Test emission calculation for gas unit")
    void testGasEmissionCalculation() throws NegativeEmissionException {
        gasUnit.setProduction(1000);
        assertEquals(1000 * FuelType.NATURAL_GAS.getEmissionFactor(),
                gasUnit.calculateEmissions(), 0.001);
    }

    @Test
    @DisplayName("Test total emissions calculation")
    void testTotalEmissions() throws NegativeEmissionException {
        solarUnit.setProduction(1000);
        gasUnit.setProduction(2000);
        windUnit.setProduction(3000);

        double expected = 0 + // solar
                2000 * FuelType.NATURAL_GAS.getEmissionFactor() + // gas
                0;    // wind

        assertEquals(expected, testPlant.calculateTotalEmissions(), 0.001);
    }

    @Test
    @DisplayName("Test emissions by type calculation")
    void testEmissionsByType() throws NegativeEmissionException {
        solarUnit.setProduction(1000);
        gasUnit.setProduction(2000);
        windUnit.setProduction(3000);

        Map<FuelType, Double> emissions = testPlant.calculateEmissionsByType();

        assertEquals(0, emissions.get(FuelType.SOLAR), 0.001);
        assertEquals(2000 * FuelType.NATURAL_GAS.getEmissionFactor(),
                emissions.get(FuelType.NATURAL_GAS), 0.001);
        assertEquals(0, emissions.get(FuelType.WIND), 0.001);
    }

    @Test
    @DisplayName("Test getting units by type")
    void testGetUnitsByType() {
        List<SolarUnit> solarUnits = testPlant.getUnitsOfType(SolarUnit.class);
        assertEquals(1, solarUnits.size());
        assertEquals(solarUnit, solarUnits.get(0));
    }

    @Test
    @DisplayName("Test empty plant emissions")
    void testEmptyPlantEmissions() throws NegativeEmissionException {
        HybridPlant emptyPlant = new HybridPlant("Empty");
        assertEquals(0, emptyPlant.calculateTotalEmissions(), 0.001);
    }

    @Test
    @DisplayName("Test report generation")
    void testReportGeneration() throws NegativeEmissionException {
        solarUnit.setProduction(1000);
        gasUnit.setProduction(2000);
        windUnit.setProduction(3000);

        String report = testPlant.generateEmissionReport();

        assertTrue(report.contains("Emission Report for Test Plant"));
        assertTrue(report.contains("Test Solar"));
        assertTrue(report.contains("Test Gas"));
        assertTrue(report.contains("Test Wind"));
        assertTrue(report.contains("Total Emissions"));
    }

    @Test
    @DisplayName("Test report file creation")
    void testReportFileCreation() throws NegativeEmissionException, IOException {
        String testFileName = "test_report.txt";


            // Clean up if file exists from previous test
            Files.deleteIfExists(Paths.get(testFileName));

            solarUnit.setProduction(1000);
            testPlant.saveEmissionReport(testFileName);

            assertTrue(Files.exists(Paths.get(testFileName)));
            assertTrue(Files.size(Paths.get(testFileName)) > 0);

            // Clean up
            Files.deleteIfExists(Paths.get(testFileName));
        }


        @Test
    @DisplayName("Test fuel type emission factors")
    void testFuelTypeEmissionFactors() {
        assertEquals(0.5, FuelType.NATURAL_GAS.getEmissionFactor(), 0.001);
        assertEquals(0.8, FuelType.DIESEL.getEmissionFactor(), 0.001);
        assertEquals(0.0, FuelType.SOLAR.getEmissionFactor(), 0.001);
    }

    @Test
    @DisplayName("Test hybrid plant name")
    void testHybridPlantName() {
        assertEquals("Test Plant", testPlant.getName());
    }

}
