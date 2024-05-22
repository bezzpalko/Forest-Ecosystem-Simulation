import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class EcosystemSimulationTest {
    private EcosystemSimulation simulation;
    private Wolf wolf;
    private Deer deer;
    private Bird bird;
    private Drought drought;
    private Rain rain;
    private Storm storm;

    @BeforeEach
    public void setUp() {
        simulation = new EcosystemSimulation(100, 100);
        simulation.addAnimal(new Wolf(100, 100, new Point(0, 0), "carnivore"));
        simulation.addAnimal(new Deer(100, 100, new Point(10, 10), "herbivore"));
        simulation.addAnimal(new Bird(100, 100, new Point(20, 20), "omnivore"));
    }

    @Test
    public void testAddAnimal() {
        simulation.addAnimal(wolf);
        assertTrue(simulation.getAnimals().contains(wolf));
    }

    @Test
    public void testRemoveAnimal() {
        simulation.addAnimal(wolf);
        simulation.removeAnimal(wolf);
        assertFalse(simulation.getAnimals().contains(wolf));
    }

    @Test
    public void testSimulate() {
        simulation.addAnimal(wolf);
        simulation.addWeatherCondition(drought);
        simulation.simulate();
        assertEquals(95, wolf.getHealth());  // Wolf health decreases by 5 due to drought
    }
    @Test
    public void testSaveSimulationDataToCSV() {
        String filename = "test_simulation_data.csv";
        simulation.saveSimulationDataToCSV(filename);

        File file = new File(filename);
        assertTrue(file.exists(), "CSV file should be created");

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            assertFalse(lines.isEmpty(), "CSV file should not be empty");
            assertEquals("Animal,Energy,Health,Diet,Position,Wingspan/IsPackLeader/HasAntlers", lines.get(0), "CSV header should be correct");
        } catch (IOException e) {
            fail("IOException should not occur");
        }

        file.delete();  // Clean up
    }
}

